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

import com.cy.ruoyi.soul.admin.entity.PluginDO;
import com.cy.ruoyi.soul.admin.query.PluginQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * PluginMapper.
 *
 * @author jiangxiaofeng(Nicholas)
 */
@Mapper
public interface PluginMapper {

    /**
     * select plugin by id.
     *
     * @param id primary key.
     * @return {@linkplain PluginDO}
     */
    PluginDO selectById(String id);


    /**
     * Select by name plugin do.
     *
     * @param name the name
     * @return the plugin do
     */
    PluginDO selectByName(String name);

    /**
     * select plugin by query.
     *
     * @param pluginQuery {@linkplain PluginQuery}
     * @return {@linkplain List}
     */
    List<PluginDO> selectByQuery(PluginQuery pluginQuery);

    /**
     * select all {@linkplain PluginQuery}
     *
     * @return {@linkplain List}
     */
    List<PluginDO> selectAll();

    /**
     * count plugin by query.
     *
     * @param pluginQuery {@linkplain PluginQuery}
     * @return {@linkplain Integer}
     */
    Integer countByQuery(PluginQuery pluginQuery);

    /**
     * insert plugin.
     *
     * @param pluginDO {@linkplain PluginDO}
     * @return rows int
     */
    int insert(PluginDO pluginDO);

    /**
     * insert selective plugin.
     *
     * @param pluginDO {@linkplain PluginDO}
     * @return rows int
     */
    int insertSelective(PluginDO pluginDO);

    /**
     * update plugin.
     *
     * @param pluginDO {@linkplain PluginDO}
     * @return rows int
     */
    int update(PluginDO pluginDO);

    /**
     * Update enable int.
     *
     * @param pluginDO the plugin do
     * @return the int
     */
    int updateEnable(PluginDO pluginDO);

    /**
     * update selective plugin.
     *
     * @param pluginDO {@linkplain PluginDO}
     * @return rows int
     */
    int updateSelective(PluginDO pluginDO);

    /**
     * delete plugin.
     *
     * @param id primary key.
     * @return rows int
     */
    int delete(String id);
}
