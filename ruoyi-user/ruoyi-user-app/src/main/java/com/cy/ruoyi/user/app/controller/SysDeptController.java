package com.cy.ruoyi.user.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.user.api.entity.SysDept;
import com.cy.ruoyi.user.api.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * 部门 提供者
 * 
 */
@RestController
@RequestMapping("dept")
public class SysDeptController extends BaseController
{
    private static final Log log = LogFactory.get();

    @Autowired
    private ISysDeptService sysDeptService;

//    /**
//     * 查询部门
//     */
//    @GetMapping("get/{deptId}")
//    public SysDept get(@PathVariable("deptId") Long deptId)
//    {
//        return sysDeptService.selectDeptById(deptId);
//    }

    /**
     * 查询部门列表
     */
    @GetMapping("list")
    public R list(SysDept sysDept)
    {
        PageDomain pageDomain = getPageInfo();
        log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
        PageUtils page = sysDeptService.selectDeptList(pageDomain, sysDept);
        return R.ok(page);
    }

//    /**
//     * 新增保存部门
//     */
//    @PostMapping("save")
//    public R addSave(@RequestBody SysDept sysDept)
//    {
//        return toAjax(sysDeptService.insertDept(sysDept));
//    }
//
//    /**
//     * 修改保存部门
//     */
//    @PostMapping("update")
//    public R editSave(@RequestBody SysDept sysDept)
//    {
//        return toAjax(sysDeptService.updateDept(sysDept));
//    }
//
//    /**
//     * 删除部门
//     */
//    @PostMapping("remove/{deptId}")
//    public R remove(@PathVariable("deptId") Long deptId)
//    {
//        return toAjax(sysDeptService.deleteDeptById(deptId));
//    }
//
//    /**
//     * 加载角色部门（数据权限）列表树
//     */
//    @GetMapping("/role/{roleId}")
//    public Set<String> deptTreeData(@PathVariable("roleId" )Long roleId)
//    {
//        if (null == roleId || roleId <= 0) return null;
//        return sysDeptService.roleDeptIds(roleId);
//    }
}
