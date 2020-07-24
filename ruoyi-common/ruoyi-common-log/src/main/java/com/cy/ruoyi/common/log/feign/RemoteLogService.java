package com.cy.ruoyi.common.log.feign;

import com.cy.ruoyi.common.log.DTO.SysLogininforDTO;
import com.cy.ruoyi.common.log.DTO.SysOperLogDTO;
import com.cy.ruoyi.common.log.feign.fallback.RemoteLogFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 日志Feign服务层
 */
@FeignClient(name = "ruoyi-sys", fallbackFactory = RemoteLogFallbackFactory.class)
public interface RemoteLogService
{
    @PostMapping("operLog/save")
    void insertOperlog(@RequestBody SysOperLogDTO operLog);

    @PostMapping("logininfor/save")
    void insertLoginlog(@RequestBody SysLogininforDTO logininfor);
}
