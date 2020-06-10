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

package com.cy.ruoyi.soul.admin.service;

import com.cy.ruoyi.soul.admin.dto.DashboardUserDTO;
import com.cy.ruoyi.soul.admin.page.CommonPager;
import com.cy.ruoyi.soul.admin.query.DashboardUserQuery;
import com.cy.ruoyi.soul.admin.vo.DashboardUserVO;

import java.util.List;

/**
 * this is dashboard user service.
 *
 * @author jiangxiaofeng(Nicholas)
 */
public interface DashboardUserService {

    /**
     * create or update dashboard user.
     *
     * @param dashboardUserDTO {@linkplain DashboardUserDTO}
     * @return rows
     */
    int createOrUpdate(DashboardUserDTO dashboardUserDTO);

    /**
     * delete dashboard users.
     *
     * @param ids primary key.
     * @return rows
     */
    int delete(List<String> ids);

    /**
     * find dashboard user by id.
     *
     * @param id primary key.
     * @return {@linkplain DashboardUserVO}
     */
    DashboardUserVO findById(String id);

    /**
     * find dashboard user by query.
     *
     * @param userName user name
     * @param password user password
     * @return {@linkplain DashboardUserVO}
     */
    DashboardUserVO findByQuery(String userName, String password);

    /**
     * find page of dashboard user by query.
     *
     * @param dashboardUserQuery {@linkplain DashboardUserQuery}
     * @return {@linkplain CommonPager}
     */
    CommonPager<DashboardUserVO> listByPage(DashboardUserQuery dashboardUserQuery);
}
