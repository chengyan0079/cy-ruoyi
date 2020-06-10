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

import com.cy.ruoyi.soul.admin.entity.SelectorDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.soul.common.enums.MatchModeEnum;
import org.dromara.soul.common.enums.SelectorTypeEnum;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * this is selector view to web front.
 *
 * @author jiangxiaofeng(Nicholas)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectorVO implements Serializable {

    /**
     * primary key.
     */
    private String id;

    /**
     * plugin id.
     */
    private String pluginId;

    /**
     * selector name.
     */
    private String name;

    /**
     * match mode code.
     */
    private Integer matchMode;

    /**
     * match mode name.
     */
    private String matchModeName;

    /**
     * selector type code.
     */
    private Integer type;

    /**
     * selector type name.
     */
    private String typeName;

    /**
     * sort type.
     */
    private Integer sort;

    /**
     * whether enabled.
     */
    private Boolean enabled;

    /**
     * whether loged.
     */
    private Boolean loged;

    /**
     * whether continued.
     */
    private Boolean continued;

    private String handle;

    /**
     * selector conditions.
     */
    private List<SelectorConditionVO> selectorConditions;

    /**
     * created time.
     */
    private String dateCreated;

    /**
     * updated time.
     */
    private String dateUpdated;

    /**
     * build selectorVO.
     *
     * @param selectorDO {@linkplain SelectorDO}
     * @return {@linkplain SelectorVO}
     */
    public static SelectorVO buildSelectorVO(final SelectorDO selectorDO) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return new SelectorVO(selectorDO.getId(), selectorDO.getPluginId(), selectorDO.getName(), selectorDO.getMatchMode(), MatchModeEnum.getMatchModeByCode(selectorDO.getMatchMode()),
                selectorDO.getType(), SelectorTypeEnum.getSelectorTypeByCode(selectorDO.getType()), selectorDO.getSort(),
                selectorDO.getEnabled(), selectorDO.getLoged(), selectorDO.getContinued(), selectorDO.getHandle(),null,
                dateTimeFormatter.format(selectorDO.getDateCreated().toLocalDateTime()),
                dateTimeFormatter.format(selectorDO.getDateUpdated().toLocalDateTime()));
    }

    /**
     * build selectorVO.
     *
     * @param selectorDO         {@linkplain SelectorDO}
     * @param selectorConditions {@linkplain List}
     * @return {@linkplain SelectorVO}
     */
    public static SelectorVO buildSelectorVO(final SelectorDO selectorDO, final List<SelectorConditionVO> selectorConditions) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return new SelectorVO(selectorDO.getId(), selectorDO.getPluginId(), selectorDO.getName(), selectorDO.getMatchMode(), MatchModeEnum.getMatchModeByCode(selectorDO.getMatchMode()),
                selectorDO.getType(), SelectorTypeEnum.getSelectorTypeByCode(selectorDO.getType()), selectorDO.getSort(),
                selectorDO.getEnabled(), selectorDO.getLoged(), selectorDO.getContinued(), selectorDO.getHandle(),selectorConditions,
                dateTimeFormatter.format(selectorDO.getDateCreated().toLocalDateTime()),
                dateTimeFormatter.format(selectorDO.getDateUpdated().toLocalDateTime()));
    }
}
