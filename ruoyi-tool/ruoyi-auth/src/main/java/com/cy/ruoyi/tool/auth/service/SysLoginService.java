package com.cy.ruoyi.tool.auth.service;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.auth.DTO.SysUserDTO;
import com.cy.ruoyi.common.auth.constants.UserConstants;
import com.cy.ruoyi.common.auth.enums.UserStatus;
import com.cy.ruoyi.common.core.exception.RuoyiException;
import com.cy.ruoyi.common.core.util.ServletUtils;
import com.cy.ruoyi.common.log.publish.PublishFactory;
import com.cy.ruoyi.common.utils.constants.Constants;
import com.cy.ruoyi.common.utils.util.*;
import com.cy.ruoyi.tool.auth.feign.RemoteUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysLoginService
{

    private static final Log log = LogFactory.get();

    @Autowired
    private RemoteUserService userService;

    /**
     * 登录
     */
    public SysUserDTO login(String username, String password)
    {
        log.info("进入登陆请求。。。");
        // 验证码校验
//         if
//         (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
//         {
//         AsyncManager.me().execute(AsyncFactory.recordLogininfor(username,
//         Constants.LOGIN_FAIL,
//         MessageUtils.message("user.jcaptcha.error")));
//         throw new CaptchaException();
//         }

        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password))
        {
            PublishFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null"));
            throw new RuoyiException(MessageUtils.message("not.null"));
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            PublishFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.password.not.match"));
            throw new RuoyiException(MessageUtils.message("user.password.not.match"));
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            PublishFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.password.not.match"));
            throw new RuoyiException(MessageUtils.message("user.password.not.match"));
        }
        // 查询用户信息
        SysUserDTO user = userService.selectSysUserByUsername(username);
//         if (user == null && maybeMobilePhoneNumber(username))
//         {
//         user = userService.selectUserByPhoneNumber(username);
//         }
//         if (user == null && maybeEmail(username))
//         {
//         user = userService.selectUserByEmail(username);
//         }
        if (RegexUtil.isNull(user))
        {
            PublishFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.not.exists"));
            throw new RuoyiException(MessageUtils.message("user.not.exists"));
        }
        if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            PublishFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.password.delete"));
            throw new RuoyiException(MessageUtils.message("user.password.delete"));
        }
        if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            PublishFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.blocked", user.getRemark()));
            throw new RuoyiException(MessageUtils.message("user.blocked"));
        }
        if (!PasswordUtil.matches(user.getLoginName(), user.getPassword(), user.getSalt(), password))
        {
            throw new RuoyiException("密码错误！");
        }
        PublishFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success"));
        recordLoginInfo(user);
        return user;
    }

//     private boolean maybeEmail(String username)
//     {
//     if (!username.matches(UserConstants.EMAIL_PATTERN))
//     {
//     return false;
//     }
//     return true;
//     }
//
//     private boolean maybeMobilePhoneNumber(String username)
//     {
//     if (!username.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN))
//     {
//     return false;
//     }
//     return true;
//     }

    /**
     * 记录登录信息
     */
    private void recordLoginInfo(SysUserDTO user)
    {
        user.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserLoginRecord(user);
    }

    public void logout(String loginName)
    {
        PublishFactory.recordLogininfor(loginName, Constants.LOGOUT, MessageUtils.message("user.logout.success"));
    }
}