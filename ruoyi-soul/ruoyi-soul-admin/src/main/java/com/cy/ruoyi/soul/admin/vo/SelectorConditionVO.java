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
import com.cy.ruoyi.soul.admin.entity.SelectorConditionDO;
import org.dromara.soul.common.enums.OperatorEnum;
import org.dromara.soul.common.enums.ParamTypeEnum;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

/**
 * this is selector condition view to web front.
 *
 * @author jiangxiaofeng(Nicholas)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectorConditionVO implements Serializable {

    /**
     * primary key.
     */
    private String id;

    /**
     * selector id.
     */
    private String selectorId;

    /**
     * parameter type code.
     */
    private String paramType;

    /**
     * parameter type name.
     */
    private String paramTypeName;

    /**
     * match operator code.
     */
    private String operator;

    /**
     * match operator name.
     */
    private String operatorName;

    /**
     * parameter name.
     */
    private String paramName;

    /**
     * parameter value.
     */
    private String paramValue;

    /**
     * created time.
     */
    private String dateCreated;

    /**
     * updated time.
     */
    private String dateUpdated;

    /**
     * build selectorConditionVO.
     *
     * @param selectorConditionDO {@linkplain SelectorConditionDO}
     * @return {@linkplain SelectorConditionVO}
     */
    public static SelectorConditionVO buildSelectorConditionVO(final SelectorConditionDO selectorConditionDO) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ParamTypeEnum paramTypeEnum = ParamTypeEnum.getParamTypeEnumByName(selectorConditionDO.getParamType());
        OperatorEnum operatorEnum = OperatorEnum.getOperatorEnumByAlias(selectorConditionDO.getOperator());
        return new SelectorConditionVO(selectorConditionDO.getId(), selectorConditionDO.getSelectorId(), selectorConditionDO.getParamType(),
                paramTypeEnum == null ? null : paramTypeEnum.name(), selectorConditionDO.getOperator(),
                operatorEnum == null ? null : operatorEnum.name(), selectorConditionDO.getParamName(), selectorConditionDO.getParamValue(),
                dateTimeFormatter.format(selectorConditionDO.getDateCreated().toLocalDateTime()),
                dateTimeFormatter.format(selectorConditionDO.getDateUpdated().toLocalDateTime()));
    }
}
