package com.cy.ruoyi.common.log.event;

import com.cy.ruoyi.common.log.DTO.SysOperLogDTO;
import org.springframework.context.ApplicationEvent;

/**
 * 系统日志事件
 */
public class SysOperLogEvent extends ApplicationEvent
{
    //
    private static final long serialVersionUID = 8905017895058642111L;

    public SysOperLogEvent(SysOperLogDTO source)
    {
        super(source);
    }
}