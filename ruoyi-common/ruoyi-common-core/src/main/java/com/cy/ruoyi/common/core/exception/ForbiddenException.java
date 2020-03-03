package com.cy.ruoyi.common.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>File：ForbiddenException.java</p>
 * <p>Title: 权限不足</p>
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN,reason="forbidden")
public class ForbiddenException extends RuntimeException
{
    //
    private static final long   serialVersionUID = -4552488542483342775L;

    private static final String DEFAULT_MSG      = "forbidden";

    public ForbiddenException(String msg)
    {
        super(msg);
    }

    public ForbiddenException()
    {
        super(DEFAULT_MSG);
    }
}
