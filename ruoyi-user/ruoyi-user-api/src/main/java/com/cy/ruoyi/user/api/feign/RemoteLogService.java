package com.cy.ruoyi.user.api.feign;

import com.cy.ruoyi.user.api.entity.SysLogininfor;
import com.cy.ruoyi.user.api.entity.SysOperLog;
import com.cy.ruoyi.user.api.feign.factory.RemoteLogFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 日志Feign服务层
 */
@FeignClient(name = "ruoyi-user", fallbackFactory = RemoteLogFallbackFactory.class)
public interface RemoteLogService
{
    @PostMapping("operLog/save")
    void insertOperlog(@RequestBody SysOperLog operLog);

    @PostMapping("logininfor/save")
    void insertLoginlog(@RequestBody SysLogininfor logininfor);
}
