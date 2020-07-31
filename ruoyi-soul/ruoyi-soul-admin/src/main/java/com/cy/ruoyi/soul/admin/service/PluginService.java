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

import com.cy.ruoyi.soul.admin.dto.PluginDTO;
import com.cy.ruoyi.soul.admin.page.CommonPager;
import com.cy.ruoyi.soul.admin.query.PluginQuery;
import com.cy.ruoyi.soul.admin.vo.PluginVO;
import org.dromara.soul.common.dto.PluginData;

import java.util.List;

/**
 * this is plugin service.
 *
 * @author jiangxiaofeng(Nicholas)
 */
public interface PluginService {

    /**
     * Create or update string.
     *
     * @param pluginDTO the plugin dto
     * @return the string
     */
    String createOrUpdate(PluginDTO pluginDTO);

    /**
     * Delete string.
     *
     * @param ids the ids
     * @return the string
     */
    String delete(List<String> ids);

    /**
     * find plugin by id.
     *
     * @param id pk.
     * @return {@linkplain PluginVO}
     */
    PluginVO findById(String id);

    /**
     * find page of plugin by query.
     *
     * @param pluginQuery {@linkplain PluginQuery}
     * @return {@linkplain CommonPager}
     */
    CommonPager<PluginVO> listByPage(PluginQuery pluginQuery);


    /**
     * List all list.
     *
     * @return the list
     */
    List<PluginData> listAll();

    /**
     * Enabled string.
     *
     * @param ids     the ids
     * @param enabled the enable
     * @return the string
     */
    String enabled(List<String> ids, Boolean enabled);
}
