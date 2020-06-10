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

package com.cy.ruoyi.soul.admin.query;

import com.cy.ruoyi.soul.admin.page.PageParameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * this is application authority query.
 *
 * @author xiaoyu(Myth)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaDataQuery implements Serializable {

    /**
     * application key.
     */
    private String appName;

    /**
     * page parameter.
     */
    private PageParameter pageParameter;
}
