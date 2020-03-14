package com.cy.ruoyi.common.utils.util;

public class PasswordUtil
{
    public static boolean matches(String username, String password, String salt, String newPassword)
    {
        return password.equals(encryptPassword(username, newPassword, salt));
    }

    public static String encryptPassword(String username, String password, String salt)
    {
        return Md5Utils.hash(password + salt);
    }

    public static void main(String[] args) {
        Md5Utils.hash("admin123" + "111111");
        System.out.println(Md5Utils.hash("admin123" + "111111"));
    }

}