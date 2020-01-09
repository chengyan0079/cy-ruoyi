package com.cy.ruoyi.common.utils.enums;

/**
 *  微服务模块包名
 */
public enum ServiceNameEnums
{
    USER_APP("user", "用户服务"),

    AUTH_APP("auth","授权服务"),

    ;
    public final String code;
    public final String msg;

    ServiceNameEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
