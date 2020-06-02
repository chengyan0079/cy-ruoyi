package com.cy.ruoyi.common.core.exception;

import com.cy.ruoyi.common.utils.enums.TradeErrorEnum;
import lombok.Data;

/**
 * 业务异常
 */
@Data
public class BusinessException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    protected String message;

    protected String code = TradeErrorEnum.SYSTEM_DEFAULT_ERROR.code;

    public BusinessException(String message)
    {
        this.message = message;
    }

    public BusinessException(String message, Throwable e)
    {
        super(message, e);
        this.message = message;
    }
    public BusinessException(TradeErrorEnum errorEnum)
    {
        super(errorEnum.msg);
        this.message = errorEnum.msg;
        this.code = errorEnum.code;
    }

    public BusinessException(TradeErrorEnum errorEnum, Throwable e)
    {
        super(errorEnum.msg, e);
        this.message = errorEnum.msg;
        this.code = errorEnum.code;
    }

}
