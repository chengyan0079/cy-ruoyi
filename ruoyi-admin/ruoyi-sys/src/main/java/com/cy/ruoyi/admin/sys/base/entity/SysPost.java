package com.cy.ruoyi.admin.sys.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.ruoyi.common.core.basic.entity.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * 岗位表 sys_post
 */
@Data
@TableName("Sys_Post")
public class SysPost extends BaseDO implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 岗位序号 */
    @TableId(value="post_Id", type= IdType.AUTO)
    private Long postId;

    /** 岗位编码 */
    private String postCode;

    /** 岗位名称 */
    private String postName;

    /** 岗位排序 */
    private String postSort;

    /** 状态（0正常 1停用） */
    private String status;

    /** 用户是否存在此岗位标识 默认不存在 */
    private boolean flag = false;
}
