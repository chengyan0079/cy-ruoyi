package com.cy.ruoyi.admin.sys.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.ruoyi.common.core.basic.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志记录表 oper_log
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("Sys_Oper_Log")
public class SysOperLog extends BaseDO implements Serializable
{
    //
    private static final long serialVersionUID = -5556121284445360558L;

    /** 日志主键 */
    @TableId(value="oper_Id", type= IdType.AUTO)
    private Long operId;

    /** 操作模块 */
    private String title;

    /** 业务类型（0其它 1新增 2修改 3删除） */
    private Integer  businessType;

    /** 请求方法 */
    private String method;

    /** 请求方式 */
    private String requestMethod;

    /** 操作类别（0其它 1后台用户 2手机端用户） */
    private Integer operatorType;

    /** 操作人员 */
    private String operName;

    /** 部门名称 */
    private String deptName;

    /** 请求url */
    private String operUrl;

    /** 操作地址 */
    private String operIp;

    /** 操作地点 */
    private String operLocation;

    /** 请求参数 */
    private String operParam;

    /** 操作状态（0正常 1异常） */
    private Integer status;

    /** 错误消息 */
    private String errorMsg;

    /** 操作时间 */
    private Date operTime;
}
