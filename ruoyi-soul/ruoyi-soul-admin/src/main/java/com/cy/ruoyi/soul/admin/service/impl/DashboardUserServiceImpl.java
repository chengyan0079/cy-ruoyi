/*
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.cy.ruoyi.soul.admin.service.impl;

import com.cy.ruoyi.soul.admin.dto.DashboardUserDTO;
import com.cy.ruoyi.soul.admin.entity.DashboardUserDO;
import com.cy.ruoyi.soul.admin.mapper.DashboardUserMapper;
import com.cy.ruoyi.soul.admin.page.CommonPager;
import com.cy.ruoyi.soul.admin.page.PageParameter;
import com.cy.ruoyi.soul.admin.query.DashboardUserQuery;
import com.cy.ruoyi.soul.admin.service.DashboardUserService;
import com.cy.ruoyi.soul.admin.vo.DashboardUserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DashboardUserServiceImpl.
 *
 * @author jiangxiaofeng(Nicholas)
 */
@Service("dashboardUserService")
public class DashboardUserServiceImpl implements DashboardUserService {

    private final DashboardUserMapper dashboardUserMapper;

    @Autowired(required = false)
    public DashboardUserServiceImpl(final DashboardUserMapper dashboardUserMapper) {
        this.dashboardUserMapper = dashboardUserMapper;
    }

    /**
     * create or update dashboard user.
     *
     * @param dashboardUserDTO {@linkplain DashboardUserDTO}
     * @return rows
     */
    @Override
    public int createOrUpdate(final DashboardUserDTO dashboardUserDTO) {
        DashboardUserDO dashboardUserDO = DashboardUserDO.buildDashboardUserDO(dashboardUserDTO);
        if (StringUtils.isEmpty(dashboardUserDTO.getId())) {
            return dashboardUserMapper.insertSelective(dashboardUserDO);
        } else {
            return dashboardUserMapper.updateSelective(dashboardUserDO);
        }
    }

    /**
     * delete dashboard users.
     *
     * @param ids primary key.
     * @return rows
     */
    @Override
    public int delete(final List<String> ids) {
        int dashboardUserCount = 0;
        for (String id : ids) {
            dashboardUserCount += dashboardUserMapper.delete(id);
        }
        return dashboardUserCount;
    }

    /**
     * find dashboard user by id.
     *
     * @param id primary key..
     * @return {@linkplain DashboardUserVO}
     */
    @Override
    public DashboardUserVO findById(final String id) {
        return DashboardUserVO.buildDashboardUserVO(dashboardUserMapper.selectById(id));
    }

    /**
     * find dashboard user by query.
     *
     * @param userName user name
     * @param password user password
     * @return {@linkplain DashboardUserVO}
     */
    @Override
    public DashboardUserVO findByQuery(final String userName, final String password) {
        return DashboardUserVO.buildDashboardUserVO(dashboardUserMapper.findByQuery(userName, password));
    }

    /**
     * find page of dashboard user by query.
     *
     * @param dashboardUserQuery {@linkplain DashboardUserQuery}
     * @return {@linkplain CommonPager}
     */
    @Override
    public CommonPager<DashboardUserVO> listByPage(final DashboardUserQuery dashboardUserQuery) {
        PageParameter pageParameter = dashboardUserQuery.getPageParameter();
        return new CommonPager<>(
                new PageParameter(pageParameter.getCurrentPage(), pageParameter.getPageSize(), dashboardUserMapper.countByQuery(dashboardUserQuery)),
                dashboardUserMapper.selectByQuery(dashboardUserQuery).stream()
                        .map(DashboardUserVO::buildDashboardUserVO)
                        .collect(Collectors.toList()));
    }
}
