package com.cy.ruoyi.user.api.utils;

import com.cy.ruoyi.common.utils.util.Md5Utils;
import com.cy.ruoyi.user.api.entity.SysUser;

public class PasswordUtil
{
    public static boolean matches(SysUser user, String newPassword)
    {
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }

    public static String encryptPassword(String username, String password, String salt)
    {
        return Md5Utils.hash(username + password + salt);
    }
}