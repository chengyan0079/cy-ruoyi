package com.cy.ruoyi.admin.sys.base.service;

import com.cy.ruoyi.admin.sys.base.entity.SysUserOnline;
import com.cy.ruoyi.common.auth.DTO.LoginUserDTO;
/**
 * 在线用户 服务层
 */
public interface ISysUserOnlineService
{
    /**
     * 通过登录地址查询信息
     *
     * @param ipaddr 登录地址
     * @param user 用户信息
     * @return 在线用户信息
     */
    SysUserOnline selectOnlineByIpaddr(String ipaddr, LoginUserDTO user);

    /**
     * 通过用户名称查询信息
     *
     * @param userName 用户名称
     * @param user 用户信息
     * @return 在线用户信息
     */
    SysUserOnline selectOnlineByUserName(String userName, LoginUserDTO user);

    /**
     * 通过登录地址/用户名称查询信息
     *
     * @param ipaddr 登录地址
     * @param userName 用户名称
     * @param user 用户信息
     * @return 在线用户信息
     */
    SysUserOnline selectOnlineByInfo(String ipaddr, String userName, LoginUserDTO user);

    /**
     * 设置在线用户信息
     *
     * @param user 用户信息
     * @return 在线用户
     */
    SysUserOnline loginUserToUserOnline(LoginUserDTO user);
}
