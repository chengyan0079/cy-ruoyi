/*
 * @(#)BizAudit.java 2020年1月9日 下午5:16:02
 * Copyright 2020 zmr, Inc. All rights reserved. 
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cy.ruoyi.tool.activiti.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.ruoyi.common.core.basic.entity.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: 审批记录</p>
 */
@Data
@Accessors(chain = true)
@TableName("biz_audit")
public class BizAudit implements Serializable
{
    @TableId(value="ID", type= IdType.AUTO)
    private Long     id;

    private String   taskId;

    private Integer  result;

    private String   comment;

    private String   procDefKey;

    private String   procName;

    private String   applyer;

    private String   auditor;

    private Long     auditorId;

    private Date     createTime;

    @TableField(exist = false)
    private String   procInstId;

    @TableField(exist = false)
    private Long     businessKey;

    @TableField(exist = false)
    private String[] taskIds;
}
