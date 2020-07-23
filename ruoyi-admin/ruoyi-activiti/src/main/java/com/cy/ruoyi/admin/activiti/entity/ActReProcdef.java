package com.cy.ruoyi.admin.activiti.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 */
@Data
@Accessors(chain = true)
@TableName("act_re_procdef")
public class ActReProcdef implements Serializable
{
    /** id */
    @TableId(value="ID_", type= IdType.AUTO)
    private String  id;

    /** 流程名称（该编号就是流程文件process元素的name属性值） */
    @TableField("NAME_")
    private String  name;

    /** 流程编号(该编号就是流程文件process元素的id属性值) */
    @TableField("KEY_")
    private String  key;

    /** 版本号 */
    @TableField("REV_")
    private String  rev;

    /** 流程命名空间（该编号就是流程文件targetNamespace的属性值） */
    @TableField("CATEGORY_")
    private String  category;

    /** 流程版本号（由程序控制，新增即为1，修改后依次加1来完成的） */
    @TableField("VERSION_")
    private String  version;

    /** 部署编号 部署表ID*/
    @TableField("DEPLOYMENT_ID_")
    private String  deploymentId;

    /** 资源文件名称 流程bpmn文件名称*/
    @TableField("RESOURCE_NAME_")
    private String  resourceName;

    /** 图片资源文件名称 png流程图片名称*/
    @TableField("DGRM_RESOURCE_NAME_")
    private String  dgrmResourceName;

    /** 描述信息 */
    @TableField("DESCRIPTION_")
    private String  description;

    /** 是否从key启动 start节点是否存在formKey 0否  1是 */
    @TableField("HAS_START_FORM_KEY_")
    private Boolean hasStartFormKey;

    /** 是否挂起 1激活 2挂起 */
    @TableField("SUSPENSION_STATE_")
    private Integer  suspensionState;
}
