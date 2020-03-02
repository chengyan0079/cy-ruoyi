package com.cy.ruoyi.common.utils.enums;


/**
 * 交易错误枚举
 */
public enum TradeErrorEnum {
    //系统错误信息
//    CBP000001("","CBP000001","用户未登陆或登陆过期,请重新登陆！"),
//
//    //字段非空校验
//    CBP010001("id","CBP010001","主键[id]不能为空!"),
    ;

    public final Integer code;
    public final String msg;
    public final String column;

    TradeErrorEnum(String column, Integer mCode, String mMsg){
        this.column=column;
        code = mCode;
        msg = mMsg;
    }

    /**
     * 根据CODE值获取异常状态内容
     * @param mCode
     * @return
     */
    public static String getByCode(Integer mCode){
        for(TradeErrorEnum tradeErrorEnum: TradeErrorEnum.values()){
            if(mCode.equals(tradeErrorEnum.code) ){
                return tradeErrorEnum.msg;
            }
        }
        return "";
    }

}
