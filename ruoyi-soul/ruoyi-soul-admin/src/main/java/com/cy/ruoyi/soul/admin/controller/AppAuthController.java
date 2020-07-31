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

package com.cy.ruoyi.soul.admin.controller;

import org.apache.commons.lang3.StringUtils;
import com.cy.ruoyi.soul.admin.dto.AppAuthDTO;
import com.cy.ruoyi.soul.admin.dto.AuthApplyDTO;
import com.cy.ruoyi.soul.admin.dto.AuthPathWarpDTO;
import com.cy.ruoyi.soul.admin.dto.BatchCommonDTO;
import com.cy.ruoyi.soul.admin.page.CommonPager;
import com.cy.ruoyi.soul.admin.page.PageParameter;
import com.cy.ruoyi.soul.admin.query.AppAuthQuery;
import com.cy.ruoyi.soul.admin.query.AppAuthQueryDTO;
import com.cy.ruoyi.soul.admin.result.SoulAdminResult;
import com.cy.ruoyi.soul.admin.service.AppAuthService;
import com.cy.ruoyi.soul.admin.vo.AppAuthVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * this is application authority controller.
 *
 * @author jiangxiaofeng(Nicholas)
 */
@RestController
@RequestMapping("/appAuth")
public class AppAuthController {

    private final AppAuthService appAuthService;

    /**
     * Instantiates a new App auth controller.
     *
     * @param appAuthService the app auth service
     */
    @Autowired(required = false)
    public AppAuthController(final AppAuthService appAuthService) {
        this.appAuthService = appAuthService;
    }

    /**
     * Apply soul result.
     *
     * @param authApplyDTO the auth apply dto
     * @return the soul result
     */
    @PostMapping("/apply")
    public SoulAdminResult apply(@RequestBody final AuthApplyDTO authApplyDTO) {
        if (StringUtils.isNoneBlank(authApplyDTO.getAppKey())) {
            return appAuthService.applyUpdate(authApplyDTO);
        } else {
            return appAuthService.applyCreate(authApplyDTO);
        }
    }

    /**
     * Update sk soul result.
     *
     * @param appKey    the app key
     * @param appSecret the app secret
     * @return the soul result
     */
    @GetMapping("/updateSk")
    public SoulAdminResult updateSk(@RequestParam("appKey") final String appKey, @RequestParam("appSecret") final String appSecret) {
        return appAuthService.updateAppSecretByAppKey(appKey, appSecret);
    }


    /**
     * Find page by query soul result.
     *
     * @param dto the dto
     * @return the soul result
     */
    @PostMapping("/findPageByQuery")
    public SoulAdminResult findPageByQuery(@RequestBody final AppAuthQueryDTO dto) {
        AppAuthQuery query = new AppAuthQuery();
        query.setPhone(dto.getPhone());
        query.setAppKey(dto.getAppKey());
        query.setPageParameter(new PageParameter(dto.getCurrentPage(), dto.getPageSize()));
        CommonPager<AppAuthVO> commonPager = appAuthService.listByPage(query);
        return SoulAdminResult.success("query application authorities success", commonPager);
    }

    /**
     * Detail soul result.
     *
     * @param id the id
     * @return the soul result
     */
    @GetMapping("/detail")
    public SoulAdminResult detail(@RequestParam("id") final String id) {
        return SoulAdminResult.success("detail application authority success", appAuthService.findById(id));
    }

    /**
     * Update detail soul result.
     *
     * @param appAuthDTO the app auth dto
     * @return the soul result
     */
    @PostMapping("/updateDetail")
    public SoulAdminResult updateDetail(@RequestBody final AppAuthDTO appAuthDTO) {
        return appAuthService.updateDetail(appAuthDTO);
    }

    /**
     * Detail path soul result.
     *
     * @param id the id
     * @return the soul result
     */
    @GetMapping("/detailPath")
    public SoulAdminResult detailPath(@RequestParam("id") final String id) {
        return SoulAdminResult.success("detailPath application success", appAuthService.detailPath(id));
    }


    /**
     * Update detail path soul result.
     *
     * @param authPathWarpDTO the auth path warp dto
     * @return the soul result
     */
    @PostMapping("/updateDetailPath")
    public SoulAdminResult updateDetailPath(@RequestBody final AuthPathWarpDTO authPathWarpDTO) {
        return appAuthService.updateDetailPath(authPathWarpDTO);
    }


    /**
     * delete application authorities.
     *
     * @param ids primary key.
     * @return {@linkplain SoulAdminResult}
     */
    @PostMapping("/batchDelete")
    public SoulAdminResult batchDelete(@RequestBody final List<String> ids) {
        Integer deleteCount = appAuthService.delete(ids);
        return SoulAdminResult.success("delete application authorities success", deleteCount);
    }

    /**
     * Batch enabled soul result.
     *
     * @param batchCommonDTO the batch common dto
     * @return the soul result
     */
    @PostMapping("/batchEnabled")
    public SoulAdminResult batchEnabled(@RequestBody final BatchCommonDTO batchCommonDTO) {
        final String result = appAuthService.enabled(batchCommonDTO.getIds(), batchCommonDTO.getEnabled());
        if (StringUtils.isNoneBlank(result)) {
            return SoulAdminResult.error(result);
        }
        return SoulAdminResult.success("enable success");
    }

    /**
     * Sync data soul result.
     *
     * @return the soul result
     */
    @PostMapping("/syncData")
    public SoulAdminResult syncData() {
        return appAuthService.syncData();
    }

}
