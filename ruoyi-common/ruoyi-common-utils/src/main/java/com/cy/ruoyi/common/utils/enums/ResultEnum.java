package com.cy.ruoyi.common.utils.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 返回信息ENnum
 */
@Getter
@RequiredArgsConstructor
public enum ResultEnum {

    /** 返回前端处理异常信息 */
    ERROR(2,"error"),
    /** 处理成功返回值 */
    SUCCESS(0,"success"),
    /** 处理失败返回值 */
    FAIL(1,"fail"),
    ;

    /** 属性值 */
    public final Integer code;
    /** 属性备注 */
    public final String desc;

}
