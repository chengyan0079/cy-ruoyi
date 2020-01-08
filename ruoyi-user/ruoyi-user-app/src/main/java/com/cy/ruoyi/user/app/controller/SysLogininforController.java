package com.cy.ruoyi.user.app.controller;

import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.user.api.entity.SysLogininfor;
import com.cy.ruoyi.user.api.service.ISysLogininforService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 系统访问记录 提供者
 */
@RestController
@RequestMapping("logininfor")
@Api(value = "SysLogininforController",description = "系统访问记录")
public class SysLogininforController extends BaseController
{
    @Reference(validation = "true", version = "${dubbo.provider.ISysLogininforService.version}")
    private ISysLogininforService sysLogininforService;

    /**
     * 新增保存系统访问记录
     */
    @PostMapping("save")
    @ApiOperation(value = "新增保存系统访问记录")
    public void addSave(@RequestBody SysLogininfor sysLogininfor)
    {
        sysLogininforService.save(sysLogininfor);
    }

}
