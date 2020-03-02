package com.cy.ruoyi.common.utils.enums;

/**
 * 返回信息ENnum
 * @author yanghongquan
 * @email 842592135@qq.com
 * @date 2018/10/31 15:15
 */
public enum ResultEnum {
    /** 返回参数属性名 */
//    DATA("data","返回参数属性值"),
    /** 返回前端处理异常信息 */
    ERROR(2,"error"),
    /** 分页查询返回属性名 */
//    PAGE("page","分页查询返回属性名"),
    /** 分页查询当页数量 */
//    LIMIT("limit","当页数量限制"),
    /** 处理成功返回值 */
    SUCCESS(0,"success"),
    /** 处理失败返回值 */
    FAIL(1,"fail"),
    /** 返回错误代码  0交易失败 1交易成功 2 部分失败（含义待添加,暂不用） */
//    CODE("code","返回错误代码"),
    /** 在交易错误情况下 具体错误信息 */
//    MSG("msg","错误信息")
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
