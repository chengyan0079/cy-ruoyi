package com.cy.ruoyi.user.impl.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.ruoyi.user.api.entity.SysOperLog;
import com.cy.ruoyi.user.api.mapper.SysOperLogMapper;
import com.cy.ruoyi.user.api.service.ISysOperLogService;
import org.apache.dubbo.config.annotation.Service;

@Service(validation = "true", version = "${dubbo.provider.ISysOperLogService.version}")
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements ISysOperLogService {
}
