package com.cy.ruoyi.common.utils.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 用户会话
 */
@Getter
@RequiredArgsConstructor
public enum OnlineStatus
{
    /** 用户状态 */
    on_line("在线"), off_line("离线");

    private final String info;

}
