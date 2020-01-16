package com.cy.ruoyi.common.log.listen;

import com.cy.ruoyi.common.log.DTO.SysLogininforDTO;
import com.cy.ruoyi.common.log.DTO.SysOperLogDTO;
import com.cy.ruoyi.common.log.event.SysLogininforEvent;
import com.cy.ruoyi.common.log.event.SysOperLogEvent;
import com.cy.ruoyi.common.log.feign.RemoteLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * 异步监听日志事件
 */
@Slf4j
@AllArgsConstructor
public class LogListener
{
    private final RemoteLogService remoteLogService;

    @Async
    @Order
    @EventListener(SysOperLogEvent.class)
    public void listenOperLog(SysOperLogEvent event)
    {
        SysOperLogDTO sysOperLog = (SysOperLogDTO) event.getSource();
        remoteLogService.insertOperlog(sysOperLog);
        log.info("远程操作日志记录成功：{}", sysOperLog);
    }

    @Async
    @Order
    @EventListener(SysLogininforEvent.class)
    public void listenLoginifor(SysLogininforEvent event)
    {
        SysLogininforDTO sysLogininfor = (SysLogininforDTO) event.getSource();
        remoteLogService.insertLoginlog(sysLogininfor);
        log.info("远程访问日志记录成功：{}", sysLogininfor);
    }
}