package com.cy.ruoyi.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.ruoyi.common.core.basic.entity.BaseDO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统访问记录表 sys_logininfor
 */
@Data
@TableName("Sys_Logininfor")
public class SysLogininfor extends BaseDO implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    /** ID */
    @TableId(value="info_Id", type= IdType.AUTO)
    private Long infoId;

    /** 用户账号 */
    private String loginName;

    /** 登录状态 0成功 1失败 */
    private String status;

    /** 登录IP地址 */
    private String ipaddr;

    /** 登录地点 */
    private String loginLocation;

    /** 浏览器类型 */
    private String browser;

    /** 操作系统 */
    private String os;

    /** 提示消息 */
    private String msg;

    /** 访问时间 */
    private Date loginTime;

}