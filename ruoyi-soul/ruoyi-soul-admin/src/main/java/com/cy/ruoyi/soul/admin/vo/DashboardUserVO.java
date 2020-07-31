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

package com.cy.ruoyi.soul.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.cy.ruoyi.soul.admin.entity.DashboardUserDO;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

/**
 * this is dashboard user view to web front.
 *
 * @author jiangxiaofeng(Nicholas)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardUserVO implements Serializable {

    /**
     * primary key.
     */
    private String id;

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
     * created time.
     */
    private String dateCreated;

    /**
     * updated time.
     */
    private String dateUpdated;

    /**
     * build dashboardUserVO.
     *
     * @param dashboardUserDO {@linkplain DashboardUserDO}
     * @return {@linkplain DashboardUserVO}
     */
    public static DashboardUserVO buildDashboardUserVO(final DashboardUserDO dashboardUserDO) {
        if (dashboardUserDO != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return new DashboardUserVO(dashboardUserDO.getId(), dashboardUserDO.getUserName(),
                    dashboardUserDO.getPassword(), dashboardUserDO.getRole(), dashboardUserDO.getEnabled(),
                    dateTimeFormatter.format(dashboardUserDO.getDateCreated().toLocalDateTime()),
                    dateTimeFormatter.format(dashboardUserDO.getDateUpdated().toLocalDateTime()));
        }
        return null;
    }
}
