/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * Contributor license agreements.See the NOTICE file distributed with
 * This work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * he License.You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.cy.ruoyi.soul.admin.service.impl;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import com.cy.ruoyi.soul.admin.dto.*;
import com.cy.ruoyi.soul.admin.entity.MetaDataDO;
import com.cy.ruoyi.soul.admin.entity.RuleConditionDO;
import com.cy.ruoyi.soul.admin.entity.RuleDO;
import com.cy.ruoyi.soul.admin.entity.SelectorDO;
import com.cy.ruoyi.soul.admin.listener.DataChangedEvent;
import com.cy.ruoyi.soul.admin.mapper.MetaDataMapper;
import com.cy.ruoyi.soul.admin.mapper.RuleConditionMapper;
import com.cy.ruoyi.soul.admin.mapper.RuleMapper;
import com.cy.ruoyi.soul.admin.page.CommonPager;
import com.cy.ruoyi.soul.admin.page.PageParameter;
import com.cy.ruoyi.soul.admin.query.MetaDataQuery;
import com.cy.ruoyi.soul.admin.query.RuleConditionQuery;
import com.cy.ruoyi.soul.admin.service.MetaDataService;
import com.cy.ruoyi.soul.admin.service.RuleService;
import com.cy.ruoyi.soul.admin.service.SelectorService;
import com.cy.ruoyi.soul.admin.transfer.ConditionTransfer;
import com.cy.ruoyi.soul.admin.transfer.MetaDataTransfer;
import com.cy.ruoyi.soul.admin.vo.MetaDataVO;
import org.dromara.soul.common.constant.AdminConstants;
import org.dromara.soul.common.dto.ConditionData;
import org.dromara.soul.common.dto.MetaData;
import org.dromara.soul.common.dto.convert.rule.DivideRuleHandle;
import org.dromara.soul.common.dto.convert.rule.DubboRuleHandle;
import org.dromara.soul.common.dto.convert.rule.SpringCloudRuleHandle;
import org.dromara.soul.common.enums.*;
import org.dromara.soul.common.utils.JsonUtils;
import org.dromara.soul.common.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Meta data service.
 *
 * @author xiaoyu
 */
@Service("metaDataService")
public class MetaDataServiceImpl implements MetaDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetaDataServiceImpl.class);

    private final MetaDataMapper metaDataMapper;

    private final ApplicationEventPublisher eventPublisher;

    private final SelectorService selectorService;

    private final RuleService ruleService;

    private final RuleMapper ruleMapper;

    private final RuleConditionMapper ruleConditionMapper;

    /**
     * Instantiates a new Meta data service.
     *
     * @param metaDataMapper      the meta data mapper
     * @param eventPublisher      the event publisher
     * @param selectorService     the selector service
     * @param ruleService         the rule service
     * @param ruleMapper          the rule mapper
     * @param ruleConditionMapper the rule condition mapper
     */
    @Autowired(required = false)
    public MetaDataServiceImpl(final MetaDataMapper metaDataMapper,
                               final ApplicationEventPublisher eventPublisher,
                               final SelectorService selectorService,
                               final RuleService ruleService,
                               final RuleMapper ruleMapper,
                               final RuleConditionMapper ruleConditionMapper) {
        this.metaDataMapper = metaDataMapper;
        this.eventPublisher = eventPublisher;
        this.selectorService = selectorService;
        this.ruleService = ruleService;
        this.ruleMapper = ruleMapper;
        this.ruleConditionMapper = ruleConditionMapper;
    }

    @Override
    public String createOrUpdate(final MetaDataDTO metaDataDTO) {
        String msg = checkData(metaDataDTO);
        if (StringUtils.isNoneBlank(msg)) {
            return msg;
        }
        MetaDataDO metaDataDO = MetaDataTransfer.INSTANCE.mapToEntity(metaDataDTO);
        DataEventTypeEnum eventType;
        if (StringUtils.isEmpty(metaDataDTO.getId())) {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            metaDataDO.setId(UUIDUtils.getInstance().generateShortUuid());
            metaDataDO.setDateCreated(currentTime);
            metaDataDO.setDateUpdated(currentTime);
            metaDataMapper.insert(metaDataDO);
            eventType = DataEventTypeEnum.CREATE;
        } else {
            MetaDataDO m = metaDataMapper.selectById(metaDataDTO.getId());
            Optional.ofNullable(m).ifPresent(e -> metaDataDTO.setEnabled(e.getEnabled()));
            metaDataMapper.update(metaDataDO);
            eventType = DataEventTypeEnum.UPDATE;
        }
        // publish AppAuthData's event
        eventPublisher.publishEvent(new DataChangedEvent(ConfigGroupEnum.META_DATA, eventType,
                Collections.singletonList(MetaDataTransfer.INSTANCE.mapToData(metaDataDTO))));
        return StringUtils.EMPTY;
    }

    @Override
    @Transactional
    public String register(final MetaDataDTO metaDataDTO) {
        MetaDataDO byPath = metaDataMapper.findByPath(metaDataDTO.getPath());
        if (Objects.nonNull(byPath)
                && (!byPath.getMethodName().equals(metaDataDTO.getMethodName())
                || !byPath.getServiceName().equals(metaDataDTO.getServiceName()))) {
            return "you path already exist!";
        }
        final MetaDataDO exist = metaDataMapper.findByServiceNameAndMethod(metaDataDTO.getServiceName(), metaDataDTO.getMethodName());

        saveOrUpdateMetaData(exist, metaDataDTO);

        String selectorId = handlerSelector(metaDataDTO);

        handlerRule(selectorId, metaDataDTO, exist);

        return "success";
    }

    @Override
    @Transactional
    public int delete(final List<String> ids) {
        int count = 0;
        List<MetaData> metaDataList = Lists.newArrayList();
        for (String id : ids) {
            MetaDataDO metaDataDO = metaDataMapper.selectById(id);
            count += metaDataMapper.delete(id);
            // publish delete event
            metaDataList.add(MetaDataTransfer.INSTANCE.mapToData(metaDataDO));
        }
        eventPublisher.publishEvent(new DataChangedEvent(ConfigGroupEnum.META_DATA, DataEventTypeEnum.DELETE, metaDataList));
        return count;
    }

    @Override
    public String enabled(final List<String> ids, final Boolean enabled) {
        List<MetaData> metaDataList = Lists.newArrayList();
        for (String id : ids) {
            MetaDataDO metaDataDO = metaDataMapper.selectById(id);
            if (Objects.isNull(metaDataDO)) {
                return AdminConstants.ID_NOT_EXIST;
            }
            metaDataDO.setEnabled(enabled);
            metaDataMapper.updateEnable(metaDataDO);
            metaDataList.add(MetaDataTransfer.INSTANCE.mapToData(metaDataDO));
        }
        // publish change event.
        eventPublisher.publishEvent(new DataChangedEvent(ConfigGroupEnum.META_DATA, DataEventTypeEnum.UPDATE,
                metaDataList));
        return StringUtils.EMPTY;
    }

    @Override
    public void syncData() {
        List<MetaDataDO> all = metaDataMapper.findAll();
        if (CollectionUtils.isNotEmpty(all)) {
            eventPublisher.publishEvent(new DataChangedEvent(ConfigGroupEnum.META_DATA, DataEventTypeEnum.REFRESH,
                    MetaDataTransfer.INSTANCE.mapToDataAll(all)));
        }

    }

    @Override
    public MetaDataVO findById(final String id) {
        return Optional.ofNullable(MetaDataTransfer.INSTANCE.mapToVO(metaDataMapper.selectById(id))).orElse(new MetaDataVO());
    }

    @Override
    public CommonPager<MetaDataVO> listByPage(final MetaDataQuery metaDataQuery) {
        PageParameter pageParameter = metaDataQuery.getPageParameter();
        return new CommonPager<>(
                new PageParameter(pageParameter.getCurrentPage(), pageParameter.getPageSize(),
                        metaDataMapper.countByQuery(metaDataQuery)),
                metaDataMapper.selectByQuery(metaDataQuery)
                        .stream()
                        .map(MetaDataTransfer.INSTANCE::mapToVO)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<MetaDataVO> findAll() {
        return MetaDataTransfer.INSTANCE.mapToVOList(metaDataMapper.selectAll());
    }

    @Override
    public Map<String, List<MetaDataVO>> findAllGroup() {
        List<MetaDataVO> metaDataVOS = MetaDataTransfer.INSTANCE.mapToVOList(metaDataMapper.selectAll());
        return metaDataVOS.stream().collect(Collectors.groupingBy(MetaDataVO::getAppName));
    }

    @Override
    public List<MetaData> listAll() {
        return metaDataMapper.selectAll()
                .stream()
                .filter(Objects::nonNull)
                .map(MetaDataTransfer.INSTANCE::mapToData)
                .collect(Collectors.toList());
    }

    private String handlerSelector(final MetaDataDTO metaDataDTO) {
        String contextPath = buildContextPath(metaDataDTO);
        SelectorDO selectorDO = selectorService.findByName(contextPath);
        String selectorId;
        if (Objects.isNull(selectorDO)) {
            //需要新增
            selectorId = registerSelector(contextPath, metaDataDTO.getRpcType(), metaDataDTO.getAppName());
        } else {
            selectorId = selectorDO.getId();
        }
        return selectorId;
    }

    private void handlerRule(final String selectorId, final MetaDataDTO metaDataDTO, final MetaDataDO exist) {
        RuleDO existRule = ruleMapper.findByName(metaDataDTO.getPath());
        if (Objects.isNull(exist) || Objects.isNull(existRule)) {
            //需要新增
            registerRule(selectorId, metaDataDTO.getPath(), metaDataDTO.getRpcType());
        } else {
            //更新
            if (!exist.getPath().equals(metaDataDTO.getPath())) {
                RuleDO ruleDO = ruleMapper.findByName(exist.getPath());
                if (Objects.isNull(ruleDO)) {
                    return;
                }
                ruleDO.setName(metaDataDTO.getPath());
                ruleMapper.updateSelective(ruleDO);
                List<RuleConditionDO> ruleConditionDOS = ruleConditionMapper.selectByQuery(new RuleConditionQuery(ruleDO.getId()));
                if (CollectionUtils.isEmpty(ruleConditionDOS)) {
                    return;
                }
                List<ConditionData> conditionDataList = new ArrayList<>();
                for (RuleConditionDO ruleConditionDO : ruleConditionDOS) {
                    if (ruleConditionDO.getParamType().equals(ParamTypeEnum.URI.getName())) {
                        ruleConditionDO.setParamValue(metaDataDTO.getPath());
                        ruleConditionMapper.updateSelective(ruleConditionDO);
                    }
                    conditionDataList.add(ConditionTransfer.INSTANCE.mapToRuleDO(ruleConditionDO));
                }
                publishEvent(ruleDO, conditionDataList, metaDataDTO.getRpcType());
            }
        }
    }

    private String buildContextPath(final MetaDataDTO metaDataDTO) {
        String path = metaDataDTO.getPath();
        String[] splitList = StringUtils.split(path, "/");
        return "/" + splitList[0];
    }

    private String registerSelector(final String contextPath, final String rpcType, final String appName) {
        SelectorDTO selectorDTO = new SelectorDTO();
        selectorDTO.setName(contextPath);
        selectorDTO.setType(SelectorTypeEnum.CUSTOM_FLOW.getCode());
        selectorDTO.setMatchMode(MatchModeEnum.AND.getCode());
        selectorDTO.setEnabled(Boolean.TRUE);
        selectorDTO.setLoged(Boolean.TRUE);
        selectorDTO.setContinued(Boolean.TRUE);
        selectorDTO.setSort(1);
        if (RpcTypeEnum.DUBBO.getName().equals(rpcType)) {
            selectorDTO.setPluginId("6");
        } else if (RpcTypeEnum.SPRING_CLOUD.getName().equals(rpcType)) {
            selectorDTO.setPluginId("8");
            selectorDTO.setHandle(appName);
        } else {
            //is divide
            selectorDTO.setPluginId("5");
        }
        SelectorConditionDTO selectorConditionDTO = new SelectorConditionDTO();
        selectorConditionDTO.setParamType(ParamTypeEnum.URI.getName());
        selectorConditionDTO.setParamName("/");
        selectorConditionDTO.setOperator(OperatorEnum.MATCH.getAlias());
        selectorConditionDTO.setParamValue(contextPath + "/**");
        selectorDTO.setSelectorConditions(Collections.singletonList(selectorConditionDTO));
        return selectorService.register(selectorDTO);
    }

    private void saveOrUpdateMetaData(final MetaDataDO exist, final MetaDataDTO metaDataDTO) {
        DataEventTypeEnum eventType;
        MetaDataDO metaDataDO = MetaDataTransfer.INSTANCE.mapToEntity(metaDataDTO);
        if (Objects.isNull(exist)) {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            metaDataDO.setId(UUIDUtils.getInstance().generateShortUuid());
            metaDataDO.setDateCreated(currentTime);
            metaDataDO.setDateUpdated(currentTime);
            metaDataMapper.insert(metaDataDO);
            eventType = DataEventTypeEnum.CREATE;
        } else {
            metaDataDO.setId(exist.getId());
            metaDataMapper.update(metaDataDO);
            eventType = DataEventTypeEnum.UPDATE;
        }
        // publish AppAuthData's event
        eventPublisher.publishEvent(new DataChangedEvent(ConfigGroupEnum.META_DATA, eventType,
                Collections.singletonList(MetaDataTransfer.INSTANCE.mapToData(metaDataDTO))));
    }

    private void publishEvent(final RuleDO ruleDO, final List<ConditionData> conditionDataList, final String rpcType) {
        String pluginName;
        if (RpcTypeEnum.DUBBO.getName().equals(rpcType)) {
            pluginName = PluginEnum.DUBBO.getName();
        } else if (RpcTypeEnum.HTTP.getName().equals(rpcType)) {
            pluginName = PluginEnum.DIVIDE.getName();
        } else {
            pluginName = PluginEnum.SPRING_CLOUD.getName();
        }
        // publish change event.
        eventPublisher.publishEvent(new DataChangedEvent(ConfigGroupEnum.RULE, DataEventTypeEnum.UPDATE,
                Collections.singletonList(RuleDO.transFrom(ruleDO, pluginName, conditionDataList))));
    }

    private void registerRule(final String selectorId, final String path, final String rpcType) {
        RuleDTO ruleDTO = new RuleDTO();
        ruleDTO.setSelectorId(selectorId);
        ruleDTO.setName(path);
        ruleDTO.setMatchMode(MatchModeEnum.AND.getCode());
        ruleDTO.setEnabled(Boolean.TRUE);
        ruleDTO.setLoged(Boolean.TRUE);
        ruleDTO.setSort(1);
        RuleConditionDTO ruleConditionDTO = new RuleConditionDTO();
        ruleConditionDTO.setParamType(ParamTypeEnum.URI.getName());
        ruleConditionDTO.setParamName("/");
        if (path.indexOf("*") > 1) {
            ruleConditionDTO.setOperator(OperatorEnum.MATCH.getAlias());
        } else {
            ruleConditionDTO.setOperator(OperatorEnum.EQ.getAlias());
        }
        ruleConditionDTO.setParamValue(path);
        ruleDTO.setRuleConditions(Collections.singletonList(ruleConditionDTO));
        if (rpcType.equals(RpcTypeEnum.DUBBO.getName())) {
            DubboRuleHandle dubboRuleHandle = new DubboRuleHandle();
            dubboRuleHandle.setLoadBalance(LoadBalanceEnum.RANDOM.getName());
            dubboRuleHandle.setRetries(0);
            dubboRuleHandle.setTimeout(3000);
            ruleDTO.setHandle(JsonUtils.toJson(dubboRuleHandle));
        } else if (rpcType.equals(RpcTypeEnum.HTTP.getName())) {
            DivideRuleHandle divideRuleHandle = new DivideRuleHandle();
            divideRuleHandle.setLoadBalance(LoadBalanceEnum.RANDOM.getName());
            divideRuleHandle.setRetry(0);
            ruleDTO.setHandle(JsonUtils.toJson(divideRuleHandle));
        } else {
            SpringCloudRuleHandle springCloudRuleHandle = new SpringCloudRuleHandle();
            springCloudRuleHandle.setPath(path);
            ruleDTO.setHandle(JsonUtils.toJson(springCloudRuleHandle));
        }
        ruleService.register(ruleDTO);
    }

    private String checkData(final MetaDataDTO metaDataDTO) {
        Boolean success = checkParam(metaDataDTO);
        if (!success) {
            LOGGER.error("metaData create param is error,{}", metaDataDTO.toString());
            return AdminConstants.PARAMS_ERROR;
        }
        final MetaDataDO exist = metaDataMapper.findByPath(metaDataDTO.getPath());
        if (StringUtils.isBlank(metaDataDTO.getId())) {
            if (Objects.nonNull(exist)) {
                return AdminConstants.DATA_PATH_IS_EXIST;
            }
        } else {
            if (Objects.isNull(exist) || !exist.getId().equals(metaDataDTO.getId())) {
                return AdminConstants.DATA_PATH_IS_EXIST;
            }
        }
        return StringUtils.EMPTY;
    }

    private Boolean checkParam(final MetaDataDTO metaDataDTO) {
        return !StringUtils.isEmpty(metaDataDTO.getAppName())
                && !StringUtils.isEmpty(metaDataDTO.getPath())
                && !StringUtils.isEmpty(metaDataDTO.getRpcType())
                && !StringUtils.isEmpty(metaDataDTO.getServiceName())
                && !StringUtils.isEmpty(metaDataDTO.getMethodName());
    }

}
