package com.cy.ruoyi.admin.sys.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.admin.sys.base.entity.SysMenu;
import com.cy.ruoyi.admin.sys.base.entity.SysUser;
import com.cy.ruoyi.admin.sys.base.service.ISysMenuService;
import com.cy.ruoyi.common.auth.annotation.HasPermissions;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.log.annotation.OperLog;
import com.cy.ruoyi.common.log.enums.BusinessType;
import com.cy.ruoyi.common.utils.annotation.LoginUser;
import com.cy.ruoyi.common.utils.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 菜单权限 
 * 
 */
@RestController
@RequestMapping("menu")
@Api(value = "SysMenuController",description = "菜单权限")
public class SysMenuController extends BaseController
{
    private static final Log log = LogFactory.get();

    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 查询菜单权限
     */
    @GetMapping("get/{menuId}")
    @ApiOperation(value = "查询菜单权限")
    @SentinelResource("get/{menuId}")
    public SysMenu get(@PathVariable("menuId") Long menuId)
    {
        return sysMenuService.selectMenuById(menuId);
    }

    @GetMapping("perms/{userId}")
    @ApiOperation(value = "perms/{userId}")
    @SentinelResource("perms/{userId}")
    public Set<String> perms(@PathVariable("userId") Long userId)
    {
        return sysMenuService.selectPermsByUserId(userId);
    }

    /**
     * 查询菜单权限
     */
    @GetMapping("user")
    @ApiOperation(value = "查询菜单权限")
    @SentinelResource("user")
    public List<SysMenu> user(@LoginUser SysUser sysUser)
    {
        return sysMenuService.selectMenusByUser(sysUser);
    }

    /**
     * 根据角色编号查询菜单编号（用于勾选）
     * @param roleId
     * @return
     * @author zmr
     */
    @GetMapping("role/{roleId}")
    @ApiOperation(value = "根据角色编号查询菜单编号（用于勾选）")
    @SentinelResource("role/{roleId}")
    public List<SysMenu> role(@PathVariable("roleId") Long roleId)
    {
        if (null == roleId || roleId <= 0) {
            return null;
        }
        return sysMenuService.selectMenuIdsByRoleId(roleId);
    }

    /**
     * 查询菜单权限列表
     */
    @HasPermissions("system:menu:view")
    @GetMapping("list")
    @ApiOperation(value = "查询菜单权限列表")
    @SentinelResource("list")
    public R list(SysMenu sysMenu)
    {
        return result(sysMenuService.selectMenuList(sysMenu));
    }

    /**
     * 新增保存菜单权限
     */
    @PostMapping("save")
    @OperLog(title = "菜单管理", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增保存菜单权限")
    @SentinelResource("save")
    public R addSave(@RequestBody SysMenu sysMenu)
    {
        return toAjax(sysMenuService.insertMenu(sysMenu));
    }

    /**
     * 修改保存菜单权限
     */
    @OperLog(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PostMapping("update")
    @ApiOperation(value = "修改保存菜单权限")
    @SentinelResource("update")
    public R editSave(@RequestBody SysMenu sysMenu)
    {
        return toAjax(sysMenuService.updateMenu(sysMenu));
    }

    /**
     * 删除菜单权限
     */
    @OperLog(title = "菜单管理", businessType = BusinessType.DELETE)
    @PostMapping("remove/{menuId}")
    @ApiOperation(value = "删除菜单权限")
    @SentinelResource("remove/{menuId}")
    public R remove(@PathVariable("menuId") Long menuId)
    {
        return toAjax(sysMenuService.deleteMenuById(menuId));
    }
}
