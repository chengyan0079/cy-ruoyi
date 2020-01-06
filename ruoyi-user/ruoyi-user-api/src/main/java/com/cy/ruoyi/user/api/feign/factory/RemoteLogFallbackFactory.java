package com.cy.ruoyi.user.api.feign.factory;

import com.cy.ruoyi.user.api.entity.SysLogininfor;
import com.cy.ruoyi.user.api.entity.SysOperLog;
import com.cy.ruoyi.user.api.feign.RemoteLogService;
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
            public void insertOperlog(SysOperLog operLog)
            {
            }

            @Override
            public void insertLoginlog(SysLogininfor logininfor)
            {
            }
        };
    }
}
