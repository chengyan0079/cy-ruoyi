package com.cy.ruoyi.user.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.ruoyi.common.core.basic.entity.BaseDO;
import lombok.Data;

/**
 * 用户和角色关联 sys_user_role
 * 
 * @author ruoyi
 */
@Data
@TableName("Sys_User_Role")
public class SysUserRole extends BaseDO
{
    /** 用户ID */
    @TableId(value="user_Id")
    private Long userId;
    
    /** 角色ID */
    @TableId(value="role_Id")
    private Long roleId;

}
