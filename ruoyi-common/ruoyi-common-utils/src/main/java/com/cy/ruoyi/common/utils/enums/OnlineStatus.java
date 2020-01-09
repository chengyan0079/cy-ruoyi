package com.cy.ruoyi.common.utils.enums;

/**
 * 用户会话
 * 
 * @author ruoyi
 */
public enum OnlineStatus
{
    /** 用户状态 */
    on_line("在线"), off_line("离线");

    private final String info;

    OnlineStatus(String info)
    {
        this.info = info;
    }

    public String getInfo()
    {
        return info;
    }
}
