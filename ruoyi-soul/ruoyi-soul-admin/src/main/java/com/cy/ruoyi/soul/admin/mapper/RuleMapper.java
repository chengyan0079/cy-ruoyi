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

import com.cy.ruoyi.soul.admin.entity.RuleDO;
import com.cy.ruoyi.soul.admin.query.RuleQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * RuleMapper.
 *
 * @author jiangxiaofeng(Nicholas)
 */
@Mapper
public interface RuleMapper {

    /**
     * select rule by id.
     *
     * @param id primary key.
     * @return {@linkplain RuleDO}
     */
    RuleDO selectById(String id);

    /**
     * select rule by query.
     *
     * @param ruleQuery {@linkplain RuleQuery}
     * @return {@linkplain List}
     */
    List<RuleDO> selectByQuery(RuleQuery ruleQuery);

    /**
     * Find by selector id list.
     *
     * @param selectorId the selector id
     * @return the list
     */
    List<RuleDO> findBySelectorId(String selectorId);

    /**
     * select rule by name.
     *
     * @param name the name
     * @return rule do
     */
    RuleDO findByName(String name);

    /**
     * count rule by query.
     *
     * @param ruleQuery {@linkplain RuleQuery}
     * @return {@linkplain Integer}
     */
    Integer countByQuery(RuleQuery ruleQuery);

    /**
     * insert rule.
     *
     * @param ruleDO {@linkplain RuleDO}
     * @return rows int
     */
    int insert(RuleDO ruleDO);

    /**
     * insert selective rule.
     *
     * @param ruleDO {@linkplain RuleDO}
     * @return rows int
     */
    int insertSelective(RuleDO ruleDO);

    /**
     * update rule.
     *
     * @param ruleDO {@linkplain RuleDO}
     * @return rows int
     */
    int update(RuleDO ruleDO);

    /**
     * update selective rule.
     *
     * @param ruleDO {@linkplain RuleDO}
     * @return rows int
     */
    int updateSelective(RuleDO ruleDO);

    /**
     * delete rule.
     *
     * @param id primary key.
     * @return rows int
     */
    int delete(String id);

    /**
     * list all {@linkplain RuleDO}
     *
     * @return {@linkplain List}
     */
    List<RuleDO> selectAll();
}
