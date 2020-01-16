package com.cy.ruoyi.common.log.feign.fallback;

import com.cy.ruoyi.common.log.DTO.SysLogininforDTO;
import com.cy.ruoyi.common.log.DTO.SysOperLogDTO;
import com.cy.ruoyi.common.log.feign.RemoteLogService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RemoteLogFallbackFactory implements FallbackFactory<RemoteLogService>
{
    @Override
    public RemoteLogService create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new RemoteLogService()
        {
            @Override
            public void insertOperlog(SysOperLogDTO operLog)
            {
            }

            @Override
            public void insertLoginlog(SysLogininforDTO logininfor)
            {
            }
        };
    }
}
