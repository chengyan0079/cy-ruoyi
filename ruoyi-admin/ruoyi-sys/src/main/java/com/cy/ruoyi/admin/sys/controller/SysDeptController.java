package com.cy.ruoyi.admin.sys.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.admin.sys.base.entity.SysDept;
import com.cy.ruoyi.admin.sys.base.service.ISysDeptService;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.sql.page.PageDomain;
import com.cy.ruoyi.common.sql.page.PageUtils;
import com.cy.ruoyi.common.utils.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * 部门 提供者
 * 
 */
@RestController
@RequestMapping("dept")
@Api(value = "SysDeptController",description = "部门")
public class SysDeptController extends BaseController
{
    private static final Log log = LogFactory.get();

    @Autowired
    private ISysDeptService sysDeptService;

    /**
     * 查询部门
     */
    @GetMapping("get/{deptId}")
    @ApiOperation(value = "查询部门")
    @SentinelResource("get/{deptId}")
    public SysDept get(@PathVariable("deptId") Long deptId)
    {
        return sysDeptService.getById(deptId);
    }

    /**
     * 查询部门列表
     */
    @GetMapping("list")
    @ApiOperation(value = "查询部门列表")
    @SentinelResource("list")
    public R list(SysDept sysDept)
    {
        PageDomain pageDomain = getPageInfo();
        log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
        PageUtils page = sysDeptService.selectDeptList(pageDomain, sysDept);
        return R.ok(page);
    }

    /**
     * 查询所有可用部门
     */
    @GetMapping("list/enable")
    @SentinelResource("list/enable")
    public R listEnable(SysDept sysDept)
    {
        sysDept.setStatus("0");
        PageDomain pageDomain = getPageInfo();
        log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
        PageUtils page = sysDeptService.selectDeptList(pageDomain, sysDept);
        return R.ok(page);
    }

    /**
     * 新增保存部门
     */
    @PostMapping("save")
    @ApiOperation(value = "新增保存部门")
    @SentinelResource("save")
    public R addSave(@RequestBody SysDept sysDept)
    {
        return toAjax(sysDeptService.insertDept(sysDept));
    }

    /**
     * 修改保存部门
     */
    @PostMapping("update")
    @ApiOperation(value = "修改保存部门")
    @SentinelResource("update")
    public R editSave(@RequestBody SysDept sysDept)
    {
        return toAjax(sysDeptService.updateDept(sysDept));
    }

    /**
     * 删除部门
     */
    @PostMapping("remove/{deptId}")
    @ApiOperation(value = "删除部门")
    @SentinelResource("remove/{deptId}")
    public R remove(@PathVariable("deptId") Long deptId)
    {
        return toAjax(sysDeptService.deleteDeptById(deptId));
    }

    /**
     * 加载角色部门（数据权限）列表树
     */
    @GetMapping("/role/{roleId}")
    @ApiOperation(value = "加载角色部门（数据权限）列表树")
    @SentinelResource("/role/{roleId}")
    public Set<String> deptTreeData(@PathVariable("roleId" )Long roleId)
    {
        if (null == roleId || roleId <= 0){
            return null;
        }
        return sysDeptService.roleDeptIds(roleId);
    }
}
