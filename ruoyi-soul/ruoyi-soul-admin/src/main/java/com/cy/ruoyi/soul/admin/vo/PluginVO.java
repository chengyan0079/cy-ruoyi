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

import com.cy.ruoyi.soul.admin.entity.PluginDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.soul.common.enums.PluginEnum;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

/**
 * this is plugin view to web front.
 *
 * @author jiangxiaofeng(Nicholas)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PluginVO implements Serializable {

    /**
     * primary key.
     */
    private String id;

    /**
     * plugin code.
     */
    private Integer code;

    /**
     * plugin role.
     */
    private Integer role;

    /**
     * plugin name.
     */
    private String name;

    /**
     * plugin config.
     */
    private String config;

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
     * build pluginVO.
     *
     * @param pluginDO {@linkplain PluginDO}
     * @return {@linkplain PluginVO}
     */
    public static PluginVO buildPluginVO(final PluginDO pluginDO) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        PluginEnum pluginEnum = PluginEnum.getPluginEnumByName(pluginDO.getName());
        return new PluginVO(pluginDO.getId(), pluginEnum == null ? null : pluginEnum.getCode(),
                pluginDO.getRole(), pluginDO.getName(), pluginDO.getConfig(), pluginDO.getEnabled(),
                dateTimeFormatter.format(pluginDO.getDateCreated().toLocalDateTime()),
                dateTimeFormatter.format(pluginDO.getDateUpdated().toLocalDateTime()));
    }
}
