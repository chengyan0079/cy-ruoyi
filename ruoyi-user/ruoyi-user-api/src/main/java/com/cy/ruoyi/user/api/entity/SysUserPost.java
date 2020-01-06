package com.cy.ruoyi.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.ruoyi.common.core.basic.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户和岗位关联 sys_user_post
 * 
 * @author ruoyi
 */
@Data
@TableName("Sys_User_Post")
public class SysUserPost extends BaseEntity implements Serializable
{
    /** 用户ID */
    @TableId(value="user_Id")
    private Long userId;
    
    /** 岗位ID */
    @TableId(value="post_Id")
    private Long postId;

}
