package com.cy.ruoyi.user.api.entity;

import com.cy.ruoyi.common.utils.enums.OnlineStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 当前在线会话 sys_user_online
 */
@Data
public class SysUserOnline implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long userId;

    /** 用户会话id */
    private String token;

    /** 部门名称 */
    private String deptName;

    /** 登录名称 */
    private String loginName;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String loginTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

//    /** 在线状态 */
//    private OnlineStatus status = OnlineStatus.on_line;

}
