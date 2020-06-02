package com.cy.ruoyi.common.utils.enums;

/**
 * 返回信息ENnum
 */
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

    ResultEnum(Integer mValue, String mDesc){
        code = mValue;
        desc = mDesc;
    }

}
