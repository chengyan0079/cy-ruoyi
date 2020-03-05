package com.cy.ruoyi.user.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.user.api.entity.SysOperLog;
import com.cy.ruoyi.user.api.service.ISysOperLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志记录 提供者
 * 
 */
@RestController
@RequestMapping("operLog")
@Api(value = "SysOperLogController",description = "操作日志记录")
public class SysOperLogController extends BaseController
{
    private static final Log log = LogFactory.get();

    @Reference(validation = "true", version = "${dubbo.provider.ISysOperLogService.version}")
    private ISysOperLogService sysOperLogService;

    /**
     * 新增保存操作日志记录
     */
    @PostMapping("save")
    @ApiOperation(value = "新增保存操作日志记录")
    public void addSave(@RequestBody SysOperLog sysOperLog)
    {
        sysOperLogService.insertOperlog(sysOperLog);
    }

}
