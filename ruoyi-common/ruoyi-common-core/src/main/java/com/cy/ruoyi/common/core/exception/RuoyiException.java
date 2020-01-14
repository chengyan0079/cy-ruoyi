package com.cy.ruoyi.common.core.exception;

import lombok.Data;

/**
 * ruoyi异常类
 * @author zmr
 * @author lucas
 */
@Data
public class RuoyiException extends RuntimeException
{
    //
    private static final long serialVersionUID = 3640068073161175965L;

    private String            msg;

    private int               code             = 500;

    public RuoyiException(String msg)
    {
        super(msg);
        this.msg = msg;
    }

    public RuoyiException(String msg, Throwable e)
    {
        super(msg, e);
        this.msg = msg;
    }

    public RuoyiException(String msg, int code)
    {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public RuoyiException(String msg, int code, Throwable e)
    {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}