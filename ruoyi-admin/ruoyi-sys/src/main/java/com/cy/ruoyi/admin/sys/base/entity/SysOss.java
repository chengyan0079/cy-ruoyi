package com.cy.ruoyi.admin.sys.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.ruoyi.common.core.basic.entity.BaseDO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件上传
 */
@Data
@TableName("Sys_Oss")
public class SysOss extends BaseDO implements Serializable
{
    //
    private static final long serialVersionUID = 1356257283938225230L;

    @TableId(value="id", type= IdType.AUTO)
    private Long  id;

    /** 文件名 */
    private String fileName;

    /** 文件后缀 */
    private String fileSuffix;

    /** URL地址 */
    private String url;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /** 上传者 */
    private String createBy;

    /** 服务商 */
    private Integer service;

    /** 用于表格行内编辑*/
    @TableField(exist = false)
    private Boolean editable;
}
