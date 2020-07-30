package com.cy.ruoyi.common.auth.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 用户状态
 */
@Getter
@RequiredArgsConstructor
public enum UserStatus
{
    OK("0", "正常"), DISABLE("1", "停用"), DELETED("2", "删除");

    private final String code;
    private final String info;

}
