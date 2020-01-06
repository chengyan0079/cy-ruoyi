package com.cy.ruoyi.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.ruoyi.common.core.basic.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色和部门关联 sys_role_dept
 * 
 * @author ruoyi
 */
@Data
@TableName("Sys_Role_Dept")
public class SysRoleDept extends BaseEntity
{
    /** 角色ID */
    @TableId(value="role_Id")
    private Long roleId;
    
    /** 部门ID */
    @TableId(value="dept_Id")
    private Long deptId;

}
