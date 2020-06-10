/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * Contributor license agreements.See the NOTICE file distributed with
 * This work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * he License.You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.cy.ruoyi.soul.admin.mapper;

import com.cy.ruoyi.soul.admin.entity.MetaDataDO;
import com.cy.ruoyi.soul.admin.query.MetaDataQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * The interface Meta data mapper.
 *
 * @author xiaoyu(Myth)
 */
@Mapper
public interface MetaDataMapper {

    /**
     * Select by id meta data do.
     *
     * @param id the id
     * @return the meta data do
     */
    MetaDataDO selectById(String id);


    /**
     * Find all list.
     *
     * @return the list
     */
    List<MetaDataDO> findAll();


    /**
     * Find by path meta data do.
     *
     * @param path the path
     * @return the meta data do
     */
    MetaDataDO findByPath(String path);

    /**
     * Find by service name and method meta data do.
     *
     * @param serviceName the service name
     * @param methodName      the methodName
     * @return the meta data do
     */
    MetaDataDO findByServiceNameAndMethod(@Param("serviceName") String serviceName, @Param("methodName") String methodName);


    /**
     * Select by query list.
     *
     * @param metaDataQuery the meta data query
     * @return the list
     */
    List<MetaDataDO> selectByQuery(MetaDataQuery metaDataQuery);

    /**
     * Select all list.
     *
     * @return the list
     */
    List<MetaDataDO> selectAll();

    /**
     * Count by query integer.
     *
     * @param metaDataQuery the meta data query
     * @return the integer
     */
    Integer countByQuery(MetaDataQuery metaDataQuery);

    /**
     * Insert int.
     *
     * @param metaDataDO the meta data do
     * @return the int
     */
    int insert(MetaDataDO metaDataDO);


    /**
     * Update int.
     *
     * @param metaDataDO the meta data do
     * @return the int
     */
    int update(MetaDataDO metaDataDO);


    /**
     * Update enable int.
     *
     * @param metaDataDO the meta data do
     * @return the int
     */
    int updateEnable(MetaDataDO metaDataDO);

    /**
     * Delete int.
     *
     * @param id the id
     * @return the int
     */
    int delete(String id);
}
