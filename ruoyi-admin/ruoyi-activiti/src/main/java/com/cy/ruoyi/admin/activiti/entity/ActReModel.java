package com.cy.ruoyi.admin.activiti.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.ruoyi.common.core.basic.entity.BaseDO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 流程设计模型部署对象 ACT_RE_MODEL
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ACT_RE_MODEL")
public class ActReModel extends BaseDO implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** id */
    @TableId(value="ID", type= IdType.AUTO)
    private String id;

    /** 乐观锁 */
    private Long rev;

    /** 名称 */
    private String name;

    /** 模型标识 */
    private String key;

    /** 分类 */
    private String category;

    /** 最新修改时间 */
    private Date lastUpdateTime;

    /** 版本 */
    private Long version;

    /** 以json格式保存流程定义的信息 */
    private String metaInfo;

    /** 部署ID */
    private String deploymentId;

    /** 编辑源值ID */
    private String editorSourceValueId;

    /** 编辑源额外值ID */
    private String editorSourceExtraValueId;

    /** 租户 */
    private String tenantId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
