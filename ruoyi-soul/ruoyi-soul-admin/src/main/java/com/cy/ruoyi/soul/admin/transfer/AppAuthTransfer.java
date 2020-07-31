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

package com.cy.ruoyi.soul.admin.transfer;

import com.cy.ruoyi.soul.admin.dto.AppAuthDTO;
import com.cy.ruoyi.soul.admin.entity.AppAuthDO;
import com.cy.ruoyi.soul.admin.vo.AppAuthVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * The interface App auth transfer.
 *
 * @author xiaoyu(Myth)
 */
@Mapper
public interface AppAuthTransfer {

    /**
     * The constant INSTANCE.
     */
    AppAuthTransfer INSTANCE = Mappers.getMapper(AppAuthTransfer.class);

    /**
     * Map to entity app auth do.
     *
     * @param appAuthDTO the app auth dto
     * @return the app auth do
     */
    AppAuthDO mapToEntity(AppAuthDTO appAuthDTO);

    /**
     * Map to vo app auth vo.
     *
     * @param appAuthDO the app auth do
     * @return the app auth vo
     */
    AppAuthVO mapToVO(AppAuthDO appAuthDO);

}
