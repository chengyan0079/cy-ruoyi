package com.cy.ruoyi.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cy.ruoyi.common.core.basic.entity.BaseEntity;
import com.cy.ruoyi.common.utils.enums.OnlineStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 当前在线会话 sys_user_online
 */
@Data
@TableName("Sys_User_Online")
public class SysUserOnline extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    /** 用户会话id */
    @TableId(value="session_Id" , type= IdType.AUTO)
    private String sessionId;

    /** 部门名称 */
    private String deptName;

    /** 登录名称 */
    private String loginName;

    /** 登录IP地址 */
    private String ipaddr;

    /** 登录地址 */
    private String loginLocation;

    /** 浏览器类型 */
    private String browser;

    /** 操作系统 */
    private String os;

    /** session创建时间 */
    private Date startTimestamp;

    /** session最后访问时间 */
    private Date lastAccessTime;

    /** 超时时间，单位为分钟 */
    private Long expireTime;

    /** 在线状态 */
    private OnlineStatus status = OnlineStatus.on_line;

}
