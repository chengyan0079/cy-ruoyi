package com.cy.ruoyi.user.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.ruoyi.common.core.basic.entity.Ztree;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.user.api.entity.SysMenu;
import com.cy.ruoyi.user.api.entity.SysOperLog;
import com.cy.ruoyi.user.api.entity.SysRole;
import com.cy.ruoyi.user.api.entity.SysUser;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 菜单 业务层
 * 
 */
public interface ISysMenuService extends IService<SysMenu>
{
    /**
     * 根据用户ID查询菜单
     * 
     * @param user 用户信息
     * @return 菜单列表
     */
    List<SysMenu> selectMenusByUser(SysUser user);

    /**
     * 查询系统菜单列表
     * 
     * @param menu 菜单信息
     * @return 菜单列表
     */
    List<SysMenu> selectMenuList(SysMenu menu);

    /**
     *  查询系统菜单列表分页
     * @param pageDomain
     * @param menu
     * @return
     */
    PageUtils selectMenuList(PageDomain pageDomain, SysMenu menu);

    /**
     * 查询菜单集合
     * 
     * @return 所有菜单信息
     */
    List<SysMenu> selectMenuAll();

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectPermsByUserId(Long userId);
    
    
    /**
     * 根据角色ID查询菜单ID
    * 
    * @param roleId 角色ID
    * @return 权限列表
    */
   List<SysMenu> selectMenuIdsByRoleId(Long roleId);

    /**
     * 根据角色ID查询菜单
     * 
     * @param role 角色对象
     * @return 菜单列表
     */
    List<Ztree> roleMenuTreeData(SysRole role);

    /**
     * 查询所有菜单信息
     * 
     * @return 菜单列表
     */
    List<Ztree> menuTreeData();

    /**
     * 查询系统所有权限
     * 
     * @return 权限列表
     */
    Map<String, String> selectPermsAll();

    /**
     * 删除菜单管理信息
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    int deleteMenuById(Long menuId);

    /**
     * 根据菜单ID查询信息
     * 
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    SysMenu selectMenuById(Long menuId);

    /**
     * 查询菜单数量
     * 
     * @param parentId 菜单父ID
     * @return 结果
     */
    int selectCountMenuByParentId(Long parentId);

    /**
     * 查询菜单使用数量
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    int selectCountRoleMenuByMenuId(Long menuId);

    /**
     * 新增保存菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    int insertMenu(SysMenu menu);

    /**
     * 修改保存菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    int updateMenu(SysMenu menu);

    /**
     * 校验菜单名称是否唯一
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    String checkMenuNameUnique(SysMenu menu);
}
