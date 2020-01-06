package com.cy.ruoyi.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.ruoyi.common.core.basic.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色表 sys_role
 * 
 * @author ruoyi
 */
@Data
@TableName("Sys_Role")
public class SysRole extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 角色ID */
    @TableId(value="role_Id", type= IdType.AUTO)
    private Long roleId;

    /** 角色名称 */
    private String roleName;

    /** 角色权限 */
    private String roleKey;

    /** 角色排序 */
    private String roleSort;

    /** 数据范围（1：所有数据权限；2：自定数据权限） */
    private String dataScope;

    /** 角色状态（0正常 1停用） */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 用户是否存在此角色标识 默认不存在 */
    private boolean flag = false;

    /** 菜单组 */
    private List<Long> menuIds;

    /** 部门组（数据权限） */
    private Long[] deptIds;

}
