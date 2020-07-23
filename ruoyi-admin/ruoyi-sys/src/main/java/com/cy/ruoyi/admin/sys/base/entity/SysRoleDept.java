package com.cy.ruoyi.admin.sys.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.ruoyi.common.core.basic.entity.BaseDO;
import lombok.Data;

/**
 * 角色和部门关联 sys_role_dept
 */
@Data
@TableName("Sys_Role_Dept")
public class SysRoleDept extends BaseDO
{
    /** 角色ID */
    @TableId(value="role_Id")
    private Long roleId;
    
    /** 部门ID */
    @TableId(value="dept_Id")
    private Long deptId;

}
