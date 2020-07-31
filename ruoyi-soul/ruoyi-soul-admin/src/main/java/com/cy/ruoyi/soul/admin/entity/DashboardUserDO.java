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

package com.cy.ruoyi.soul.admin.entity;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import com.cy.ruoyi.soul.admin.dto.DashboardUserDTO;
import org.dromara.soul.common.utils.UUIDUtils;

import java.sql.Timestamp;

/**
 * DashboardUserDO.
 *
 * @author jiangxiaofeng(Nicholas)
 */
@Data
public class DashboardUserDO extends BaseDO {

    /**
     * user name.
     */
    private String userName;

    /**
     * user password.
     */
    private String password;

    /**
     * dashboard role.
     */
    private Integer role;

    /**
     * whether enabled.
     */
    private Boolean enabled;

    /**
     * build dashboardUserDO.
     *
     * @param dashboardUserDTO {@linkplain DashboardUserDTO}
     * @return {@linkplain DashboardUserDO}
     */
    public static DashboardUserDO buildDashboardUserDO(final DashboardUserDTO dashboardUserDTO) {
        if (dashboardUserDTO != null) {
            DashboardUserDO dashboardUserDO = new DashboardUserDO();
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            if (StringUtils.isEmpty(dashboardUserDTO.getId())) {
                dashboardUserDO.setId(UUIDUtils.getInstance().generateShortUuid());
                dashboardUserDO.setEnabled(true);
                dashboardUserDO.setDateCreated(currentTime);
            } else {
                dashboardUserDO.setId(dashboardUserDTO.getId());
                dashboardUserDO.setEnabled(dashboardUserDTO.getEnabled());
            }
            dashboardUserDO.setUserName(dashboardUserDTO.getUserName());
            dashboardUserDO.setPassword(dashboardUserDTO.getPassword());
            dashboardUserDO.setRole(dashboardUserDTO.getRole());
            dashboardUserDO.setDateUpdated(currentTime);
            return dashboardUserDO;
        }
        return null;
    }
}
