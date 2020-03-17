package com.cy.ruoyi.user.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.user.api.entity.SysDept;
import com.cy.ruoyi.user.api.service.ISysDeptService;
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

    @Reference(validation = "true", version = "${dubbo.provider.ISysDeptService.version}")
    private ISysDeptService sysDeptService;

    /**
     * 查询部门
     */
    @GetMapping("get/{deptId}")
    @ApiOperation(value = "查询部门")
    public SysDept get(@PathVariable("deptId") Long deptId)
    {
        return sysDeptService.getById(deptId);
    }

    /**
     * 查询部门列表
     */
    @GetMapping("list")
    @ApiOperation(value = "查询部门列表")
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
    public R addSave(@RequestBody SysDept sysDept)
    {
        return toAjax(sysDeptService.insertDept(sysDept));
    }

    /**
     * 修改保存部门
     */
    @PostMapping("update")
    @ApiOperation(value = "修改保存部门")
    public R editSave(@RequestBody SysDept sysDept)
    {
        return toAjax(sysDeptService.updateDept(sysDept));
    }

    /**
     * 删除部门
     */
    @PostMapping("remove/{deptId}")
    @ApiOperation(value = "删除部门")
    public R remove(@PathVariable("deptId") Long deptId)
    {
        return toAjax(sysDeptService.deleteDeptById(deptId));
    }

    /**
     * 加载角色部门（数据权限）列表树
     */
    @GetMapping("/role/{roleId}")
    @ApiOperation(value = "加载角色部门（数据权限）列表树")
    public Set<String> deptTreeData(@PathVariable("roleId" )Long roleId)
    {
        if (null == roleId || roleId <= 0){
            return null;
        }
        return sysDeptService.roleDeptIds(roleId);
    }
}
