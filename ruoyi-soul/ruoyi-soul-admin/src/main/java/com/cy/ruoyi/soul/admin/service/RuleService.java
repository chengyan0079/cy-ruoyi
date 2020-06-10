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

import com.cy.ruoyi.soul.admin.dto.RuleDTO;
import com.cy.ruoyi.soul.admin.page.CommonPager;
import com.cy.ruoyi.soul.admin.query.RuleQuery;
import com.cy.ruoyi.soul.admin.vo.RuleVO;
import org.dromara.soul.common.dto.RuleData;

import java.util.List;

/**
 * this is rule service.
 *
 * @author jiangxiaofeng(Nicholas)
 */
public interface RuleService {

    /**
     * Register string.
     *
     * @param ruleDTO the rule dto
     * @return the string
     */
    String register(RuleDTO ruleDTO);

    /**
     * create or update rule.
     *
     * @param ruleDTO {@linkplain RuleDTO}
     * @return rows int
     */
    int createOrUpdate(RuleDTO ruleDTO);

    /**
     * delete rules.
     *
     * @param ids primary key.
     * @return rows int
     */
    int delete(List<String> ids);

    /**
     * find rule by id.
     *
     * @param id primary key.
     * @return {@linkplain RuleVO}
     */
    RuleVO findById(String id);

    /**
     * find page of rule by query.
     *
     * @param ruleQuery {@linkplain RuleQuery}
     * @return {@linkplain CommonPager}
     */
    CommonPager<RuleVO> listByPage(RuleQuery ruleQuery);

    /**
     * List all list.
     *
     * @return the list
     */
    List<RuleData> listAll();

    /**
     * Find by selector id list.
     *
     * @param selectorId the selector id
     * @return the list
     */
    List<RuleData> findBySelectorId(String selectorId);

}
