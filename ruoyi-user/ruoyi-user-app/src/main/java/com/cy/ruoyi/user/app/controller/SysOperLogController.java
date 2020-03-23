package com.cy.ruoyi.user.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.common.auth.annotation.HasPermissions;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.log.annotation.OperLog;
import com.cy.ruoyi.common.log.enums.BusinessType;
import com.cy.ruoyi.common.utils.util.ExcelUtil;
import com.cy.ruoyi.common.utils.util.R;
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
    @SentinelResource("save")
    public void addSave(@RequestBody SysOperLog sysOperLog)
    {
        sysOperLogService.insertOperlog(sysOperLog);
    }

    /**
     * 查询操作日志记录
     */
    @GetMapping("get/{operId}")
    @ApiOperation(value = "查询操作日志记录")
    @SentinelResource("get/{operId}")
    public SysOperLog get(@PathVariable("operId") Long operId)
    {
        return sysOperLogService.selectOperLogById(operId);
    }

    /**
     * 查询操作日志记录列表
     */
    @HasPermissions("monitor:operlog:list")
    @RequestMapping("list")
    @ApiOperation(value = "查询操作日志记录列表")
    @SentinelResource("list")
    public R list(SysOperLog sysOperLog)
    {
        PageDomain pageDomain = getPageInfo();
        log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
        PageUtils page = sysOperLogService.selectOperLogList(pageDomain, sysOperLog);
        return R.ok(page);
    }

    @OperLog(title = "操作日志", businessType = BusinessType.EXPORT)
    @HasPermissions("monitor:operlog:export")
    @PostMapping("/export")
    @ApiOperation(value = "导出操作日志记录列表")
    @SentinelResource("export")
    public R export(SysOperLog operLog)
    {
        List<SysOperLog> list = sysOperLogService.selectOperLogList(operLog);
        ExcelUtil<SysOperLog> util = new ExcelUtil<SysOperLog>(SysOperLog.class);
        return util.exportExcel(list, "操作日志");
    }

    /**
     * 删除操作日志记录
     */
    @HasPermissions("monitor:operlog:remove")
    @PostMapping("remove")
    @ApiOperation(value = "删除操作日志记录")
    @SentinelResource("remove")
    public R remove(String ids)
    {
        return toAjax(sysOperLogService.deleteOperLogByIds(ids));
    }

    @OperLog(title = "操作日志", businessType = BusinessType.CLEAN)
    @HasPermissions("monitor:operlog:remove")
    @PostMapping("/clean")
    @ApiOperation(value = "清空日志记录")
    @SentinelResource("clean")
    public R clean()
    {
        sysOperLogService.cleanOperLog();
        return R.ok();
    }

}
