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

package com.cy.ruoyi.soul.admin.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * this is application authority from by web front.
 *
 * @author xiaoyu(Myth)
 */
@Data
public class AppAuthDTO implements Serializable {

    /**
     * primary key.
     */
    private String id;

    /**
     * application key.
     */
    private String appKey;

    /**
     * encryption secret.
     */
    private String appSecret;

    private String userId;

    private String phone;

    private String extInfo;

    /**
     * whether enabled.
     */
    private Boolean enabled;

    private List<AuthParamDTO> authParamDTOList;
}
