package com.cy.ruoyi.common.utils.util;

import java.io.File;

/**
 * <p>Title: </p>
 * <p>Description: 高频方法集合类</p>
 */
public class ToolUtil
{
    /**
     * 获取临时目录
     */
    public static String getTempPath()
    {
        return System.getProperty("java.io.tmpdir");
    }

    /**
     * 获取当前项目工作目录
     * @return
     */
    public static String getUserDir()
    {
        return System.getProperty("user.dir");
    }

    /**
     * 获取临时下载目录
     */
    public static String getDownloadPath()
    {
        return getTempPath() + File.separator + "download" + File.separator;
    }
}
