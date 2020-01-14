package com.cy.ruoyi.user.app.controller;

import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.log.annotation.OperLog;
import com.cy.ruoyi.common.log.enums.BusinessType;
import com.cy.ruoyi.user.api.entity.SysMenu;
import com.cy.ruoyi.user.api.entity.SysUser;
import com.cy.ruoyi.user.api.service.ISysMenuService;
import com.cy.ruoyi.user.app.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 菜单权限 
 * 
 * @author zmr
 * @date 2019-05-20
 */
@RestController
@RequestMapping("menu")
public class SysMenuController extends BaseController
{
    @Autowired
    private ISysMenuService sysMenuService;

//    /**
//     * 查询菜单权限
//     */
//    @GetMapping("get/{menuId}")
//    public SysMenu get(@PathVariable("menuId") Long menuId)
//    {
//        return sysMenuService.selectMenuById(menuId);
//    }
//
//    @GetMapping("perms/{userId}")
//    public Set<String> perms(@PathVariable("userId") Long userId)
//    {
//        return sysMenuService.selectPermsByUserId(userId);
//    }

    /**
     * 查询菜单权限
     */
    @GetMapping("user")
    public List<SysMenu> user(@LoginUser SysUser sysUser)
    {
        return sysMenuService.selectMenusByUser(sysUser);
    }

//    /**
//     * 根据角色编号查询菜单编号（用于勾选）
//     * @param roleId
//     * @return
//     * @author zmr
//     */
//    @GetMapping("role/{roleId}")
//    public List<SysMenu> role(@PathVariable("roleId") Long roleId)
//    {
//        if (null == roleId || roleId <= 0) return null;
//        return sysMenuService.selectMenuIdsByRoleId(roleId);
//    }

//    /**
//     * 查询菜单权限列表
//     */
//    @HasPermissions("system:menu:view")
//    @GetMapping("list")
//    public R list(SysMenu sysMenu)
//    {
//        return result(sysMenuService.selectMenuList(sysMenu));
//    }
//
//    /**
//     * 新增保存菜单权限
//     */
//    @PostMapping("save")
//    @OperLog(title = "菜单管理", businessType = BusinessType.INSERT)
//    public R addSave(@RequestBody SysMenu sysMenu)
//    {
//        return toAjax(sysMenuService.insertMenu(sysMenu));
//    }
//
//    /**
//     * 修改保存菜单权限
//     */
//    @OperLog(title = "菜单管理", businessType = BusinessType.UPDATE)
//    @PostMapping("update")
//    public R editSave(@RequestBody SysMenu sysMenu)
//    {
//        return toAjax(sysMenuService.updateMenu(sysMenu));
//    }
//
//    /**
//     * 删除菜单权限
//     */
//    @OperLog(title = "菜单管理", businessType = BusinessType.DELETE)
//    @PostMapping("remove/{menuId}")
//    public R remove(@PathVariable("menuId") Long menuId)
//    {
//        return toAjax(sysMenuService.deleteMenuById(menuId));
//    }
}
