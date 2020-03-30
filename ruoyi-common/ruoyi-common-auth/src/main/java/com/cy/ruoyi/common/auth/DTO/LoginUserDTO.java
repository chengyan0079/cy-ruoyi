package com.cy.ruoyi.common.auth.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录用户身份权限
 */
@Data
public class LoginUserDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long userId;
    /**
     * 用户唯一标识
     */
    private String token;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String loginTime;

    private String loginName;

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

    /**
     * 用户信息
     */
    private SysUserDTO user;

}
