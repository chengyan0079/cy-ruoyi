package com.cy.ruoyi.user.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.auth.annotation.HasPermissions;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.log.annotation.OperLog;
import com.cy.ruoyi.common.log.enums.BusinessType;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.user.api.entity.SysRole;
import com.cy.ruoyi.user.api.service.ISysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 角色 提供者
 */
@RestController
@RequestMapping("role")
@Api(value = "SysRoleController",description = "角色")
public class SysRoleController extends BaseController
{

    private static final Log log = LogFactory.get();

    @Reference(validation = "true", version = "${dubbo.provider.ISysRoleService.version}")
    private ISysRoleService sysRoleService;

    /**
     * 查询角色
     */
    @GetMapping("get/{roleId}")
    public SysRole get(@PathVariable("roleId") Long roleId)
    {
        return sysRoleService.selectRoleById(roleId);
    }

    /**
     * 查询角色列表
     */
    @GetMapping("list")
    @ApiOperation(value = "查询角色列表")
    public R list(SysRole sysRole)
    {
        PageDomain pageDomain = getPageInfo();
        log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
        PageUtils page = sysRoleService.selectRoleList(pageDomain, sysRole);
        return R.ok(page);
    }

    @GetMapping("all")
    @ApiOperation(value = "查询所有角色")
    public R all()
    {
        return result(sysRoleService.selectRoleList(new SysRole()));
    }

    /**
     * 新增保存角色
     */
    @PostMapping("save")
    @OperLog(title = "角色管理", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增保存角色")
    public R addSave(@RequestBody SysRole sysRole)
    {
        return toAjax(sysRoleService.insertRole(sysRole));
    }

    /**
     * 修改保存角色
     */
    @OperLog(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("update")
    @ApiOperation(value = "修改保存角色")
    public R editSave(@RequestBody SysRole sysRole)
    {
        return toAjax(sysRoleService.updateRole(sysRole));
    }

    /**
     * 修改保存角色
     */
    @OperLog(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("status")
    @ApiOperation(value = "修改保存角色")
    public R status(@RequestBody SysRole sysRole)
    {
        return toAjax(sysRoleService.changeStatus(sysRole));
    }

    /**
     * 保存角色分配数据权限
     */
    @HasPermissions("system:role:edit")
    @OperLog(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/authDataScope")
    @ApiOperation(value = "保存角色分配数据权限")
    public R authDataScopeSave(@RequestBody SysRole role)
    {
        role.setUpdateBy(getLoginName());
        if (sysRoleService.authDataScope(role) > 0)
        {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除角色
     * @throws Exception
     */
    @OperLog(title = "角色管理", businessType = BusinessType.DELETE)
    @PostMapping("remove")
    @ApiOperation(value = "删除角色")
    public R remove(String ids) throws Exception
    {
        return toAjax(sysRoleService.deleteRoleByIds(ids));
    }
}
