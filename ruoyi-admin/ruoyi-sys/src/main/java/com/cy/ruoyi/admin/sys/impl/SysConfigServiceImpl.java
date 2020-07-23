package com.cy.ruoyi.admin.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.ruoyi.admin.sys.base.entity.SysConfig;
import com.cy.ruoyi.admin.sys.base.mapper.SysConfigMapper;
import com.cy.ruoyi.admin.sys.base.service.ISysConfigService;
import com.cy.ruoyi.common.auth.constants.UserConstants;
import com.cy.ruoyi.common.sql.page.PageDomain;
import com.cy.ruoyi.common.sql.page.PageUtils;
import com.cy.ruoyi.common.sql.page.Query;
import com.cy.ruoyi.common.utils.text.Convert;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import com.cy.ruoyi.common.utils.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 参数配置 服务层实现
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.ISysConfigService.version}")
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService
{
    @Autowired
    private SysConfigMapper configMapper;

    /**
     * 查询参数配置信息
     * 
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
    @Override
    public SysConfig selectConfigById(Long configId)
    {
        SysConfig config = new SysConfig();
        config.setConfigId(configId);
        return configMapper.selectConfig(config);
    }

    /**
     * 根据键名查询参数配置信息
     * 
     * @param configKey 参数key
     * @return 参数键值
     */
    @Override
    public String selectConfigByKey(String configKey)
    {
        SysConfig config = new SysConfig();
        config.setConfigKey(configKey);
        SysConfig retConfig = configMapper.selectConfig(config);
        return StringUtils.isNotNull(retConfig) ? retConfig.getConfigValue() : "";
    }

    /**
     * 查询参数配置列表
     * 
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    @Override
    public List<SysConfig> selectConfigList(SysConfig config)
    {
        return configMapper.selectConfigList(config);
    }

    /**
     * 新增参数配置
     * 
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int insertConfig(SysConfig config)
    {
        return configMapper.insertConfig(config);
    }

    /**
     * 修改参数配置
     * 
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int updateConfig(SysConfig config)
    {
        return configMapper.updateConfig(config);
    }

    /**
     * 批量删除参数配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigByIds(String ids)
    {
        return configMapper.deleteConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 校验参数键名是否唯一
     * 
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public String checkConfigKeyUnique(SysConfig config)
    {
        Long configId = StringUtils.isNull(config.getConfigId()) ? -1L : config.getConfigId();
        SysConfig info = configMapper.checkConfigKeyUnique(config.getConfigKey());
        if (StringUtils.isNotNull(info) && info.getConfigId().longValue() != configId.longValue())
        {
            return UserConstants.CONFIG_KEY_NOT_UNIQUE;
        }
        return UserConstants.CONFIG_KEY_UNIQUE;
    }

    /* (non-Javadoc)
     * @see com.ruoyi.system.service.ISysConfigService#updateValueByKey(java.lang.String, java.lang.String)
     */
    @Override
    public int updateValueByKey(String key, String configValue)
    {
        SysConfig info = configMapper.checkConfigKeyUnique(key);
        if (StringUtils.isNotNull(info))
        {
            info.setConfigValue(configValue);
            return updateConfig(info);
        }
        return 0;
    }

    /**
     * 根据条件分页查询用户列表
     */
    @Override
    public PageUtils selectConfigList(PageDomain pageDomain, SysConfig config)
    {
        if (RegexUtil.isNull(config)) {
            config = new SysConfig();
        }
        IPage<SysConfig> page = configMapper.selectConfigList(new Query<SysConfig>(pageDomain).getPage(), config);
        return new PageUtils(page);
    }
}
