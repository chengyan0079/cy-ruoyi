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

package com.cy.ruoyi.soul.admin.mapper;

import com.cy.ruoyi.soul.admin.entity.SelectorConditionDO;
import com.cy.ruoyi.soul.admin.query.SelectorConditionQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * SelectorConditionMapper.
 *
 * @author jiangxiaofeng(Nicholas)
 */
@Mapper
public interface SelectorConditionMapper {

    /**
     * select selector condition by id.
     *
     * @param id primary key.
     * @return {@linkplain SelectorConditionDO}
     */
    SelectorConditionDO selectById(String id);

    /**
     * select selector condition by query.
     *
     * @param selectorConditionQuery {@linkplain SelectorConditionQuery}
     * @return {@linkplain List}
     */
    List<SelectorConditionDO> selectByQuery(SelectorConditionQuery selectorConditionQuery);

    /**
     * insert selector condition.
     *
     * @param selectorConditionDO {@linkplain SelectorConditionDO}
     * @return rows
     */
    int insert(SelectorConditionDO selectorConditionDO);

    /**
     * insert selective selector condition.
     *
     * @param selectorConditionDO {@linkplain SelectorConditionDO}
     * @return rows
     */
    int insertSelective(SelectorConditionDO selectorConditionDO);

    /**
     * update selector condition.
     *
     * @param selectorConditionDO {@linkplain SelectorConditionDO}
     * @return rows
     */
    int update(SelectorConditionDO selectorConditionDO);

    /**
     * update selective selector condition.
     *
     * @param selectorConditionDO {@linkplain SelectorConditionDO}
     * @return rows
     */
    int updateSelective(SelectorConditionDO selectorConditionDO);

    /**
     * delete selector condition by id.
     *
     * @param id primary key.
     * @return rows
     */
    int delete(String id);

    /**
     * delete selector condition by query.
     *
     * @param selectorConditionQuery {@linkplain SelectorConditionDO}
     * @return rows
     */
    int deleteByQuery(SelectorConditionQuery selectorConditionQuery);
}
