package com.cy.ruoyi.tool.activiti.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.ruoyi.common.core.basic.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("act_ru_task")
public class ActRuTask implements Serializable
{

    private static final long serialVersionUID = 1L;

    @TableId(value="ID", type= IdType.AUTO)
    private String id;

    private Integer rev;

    private String executionId;

    private String procInstId;

    private String procDefId;

    private String name;

    private String parentTaskId;

    private String description;

    private String taskDefKey;

    private String owner;

    private String assignee;

    private String delegation;

    private Integer priority;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Date dueDate;

    private String category;

    private Integer suspensionState;

    private String tenantId;

    private String formKey;

}