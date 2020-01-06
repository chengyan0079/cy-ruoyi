package com.cy.ruoyi.user.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色和菜单关联 sys_role_menu
 * 
 * @author ruoyi
 */
@Data
@TableName("Sys_Role_Menu")
public class SysRoleMenu
{
    /** 角色ID */
    @TableId(value="role_Id")
    private Long roleId;
    
    /** 菜单ID */
    @TableId(value="menu_Id")
    private Long menuId;

}
