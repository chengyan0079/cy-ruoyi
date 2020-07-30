package com.cy.ruoyi.common.utils.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *  微服务模块包名
 */
@Getter
@RequiredArgsConstructor
public enum ServiceNameEnums
{
    USER_APP("user", "用户服务"),

    AUTH_APP("auth","授权服务"),

    GEN_APP("gen","代码生成"),
    ;
    public final String code;
    public final String msg;

}
