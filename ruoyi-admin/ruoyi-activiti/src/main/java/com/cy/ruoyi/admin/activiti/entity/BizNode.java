package com.cy.ruoyi.admin.activiti.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>File：BizNode.java</p>
 */
@Data
@Accessors(chain=true)
@TableName("biz_node")
public class BizNode implements Serializable
{
    private static final long serialVersionUID =1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id;

    /** 节点ID*/
    private String nodeId;

    /** 类型 1：角色 2：部门负责人 3：用户 4：所属部门负责人*/
    private Integer type;

    /** 类型对应负责人的值*/
    private Long auditor;
}
