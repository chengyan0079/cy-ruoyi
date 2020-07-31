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

package com.cy.ruoyi.soul.admin.controller;

import com.cy.ruoyi.soul.admin.dto.SelectorDTO;
import com.cy.ruoyi.soul.admin.page.CommonPager;
import com.cy.ruoyi.soul.admin.page.PageParameter;
import com.cy.ruoyi.soul.admin.query.SelectorQuery;
import com.cy.ruoyi.soul.admin.result.SoulAdminResult;
import com.cy.ruoyi.soul.admin.service.SelectorService;
import com.cy.ruoyi.soul.admin.vo.SelectorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * this is selector controller.
 *
 * @author jiangxiaofeng(Nicholas)
 */
@RestController
@RequestMapping("/selector")
public class SelectorController {

    private final SelectorService selectorService;

    @Autowired(required = false)
    public SelectorController(final SelectorService selectorService) {
        this.selectorService = selectorService;
    }

    /**
     * query Selectors.
     *
     * @param pluginId    plugin id.
     * @param currentPage current page.
     * @param pageSize    page size.
     * @return {@linkplain SoulAdminResult}
     */
    @GetMapping("")
    public SoulAdminResult querySelectors(final String pluginId, final Integer currentPage, final Integer pageSize) {
        CommonPager<SelectorVO> commonPager = selectorService.listByPage(new SelectorQuery(pluginId, new PageParameter(currentPage, pageSize)));
        return SoulAdminResult.success("query selectors success", commonPager);
    }

    /**
     * detail selector.
     *
     * @param id selector id.
     * @return {@linkplain SoulAdminResult}
     */
    @GetMapping("/{id}")
    public SoulAdminResult detailSelector(@PathVariable("id") final String id) {
        SelectorVO selectorVO = selectorService.findById(id);
        return SoulAdminResult.success("detail selector success", selectorVO);
    }

    /**
     * create selector.
     *
     * @param selectorDTO selector.
     * @return {@linkplain SoulAdminResult}
     */
    @PostMapping("")
    public SoulAdminResult createSelector(@RequestBody final SelectorDTO selectorDTO) {
        Integer createCount = selectorService.createOrUpdate(selectorDTO);
        return SoulAdminResult.success("create selector success", createCount);
    }

    /**
     * update Selector.
     *
     * @param id          primary key.
     * @param selectorDTO selector.
     * @return {@linkplain SoulAdminResult}
     */
    @PutMapping("/{id}")
    public SoulAdminResult updateSelector(@PathVariable("id") final String id, @RequestBody final SelectorDTO selectorDTO) {
        Objects.requireNonNull(selectorDTO);
        selectorDTO.setId(id);
        Integer updateCount = selectorService.createOrUpdate(selectorDTO);
        return SoulAdminResult.success("update selector success", updateCount);
    }

    /**
     * delete Selectors.
     *
     * @param ids primary key.
     * @return {@linkplain SoulAdminResult}
     */
    @DeleteMapping("/batch")
    public SoulAdminResult deleteSelector(@RequestBody final List<String> ids) {
        Integer deleteCount = selectorService.delete(ids);
        return SoulAdminResult.success("delete selectors success", deleteCount);
    }
}
