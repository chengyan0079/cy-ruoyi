package com.cy.ruoyi.admin.sys.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.admin.sys.base.entity.SysLogininfor;
import com.cy.ruoyi.admin.sys.base.service.ISysLogininforService;
import com.cy.ruoyi.common.auth.annotation.HasPermissions;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.log.annotation.OperLog;
import com.cy.ruoyi.common.log.enums.BusinessType;
import com.cy.ruoyi.common.sql.page.PageDomain;
import com.cy.ruoyi.common.sql.page.PageUtils;
import com.cy.ruoyi.common.utils.util.R;
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
    private static final Log log = LogFactory.get();

    @Autowired
    private ISysLogininforService sysLogininforService;

    /**
     * 新增保存系统访问记录
     */
    @PostMapping("save")
    @ApiOperation(value = "新增保存系统访问记录")
    @SentinelResource("save")
    public void addSave(@RequestBody SysLogininfor sysLogininfor)
    {
        sysLogininforService.insertLogininfor(sysLogininfor);
    }

    /**
     * 查询系统访问记录列表
     */
    @GetMapping("list")
    @ApiOperation(value = "查询系统访问记录列表")
    @SentinelResource("list")
    public R list(SysLogininfor sysLogininfor)
    {
        PageDomain pageDomain = getPageInfo();
        log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
        PageUtils page = sysLogininforService.selectLogininforList(pageDomain, sysLogininfor);
        return R.ok(page);
    }

    /**
     * 删除系统访问记录
     */
    @OperLog(title = "访问日志", businessType = BusinessType.DELETE)
    @HasPermissions("monitor:loginlog:remove")
    @PostMapping("remove")
    @ApiOperation(value = "删除系统访问记录")
    @SentinelResource("remove")
    public R remove(String ids)
    {
        return toAjax(sysLogininforService.deleteLogininforByIds(ids));
    }

    @OperLog(title = "访问日志", businessType = BusinessType.CLEAN)
    @HasPermissions("monitor:loginlog:remove")
    @PostMapping("/clean")
    @ApiOperation(value = "清空访问记录")
    @SentinelResource("clean")
    public R clean()
    {
        sysLogininforService.cleanLogininfor();
        return R.ok();
    }

}
