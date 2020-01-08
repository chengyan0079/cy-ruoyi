package com.cy.ruoyi.user.impl.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.ruoyi.user.api.entity.SysLogininfor;
import com.cy.ruoyi.user.api.mapper.SysLogininforMapper;
import com.cy.ruoyi.user.api.service.ISysLogininforService;
import org.apache.dubbo.config.annotation.Service;

@Service(validation = "true", version = "${dubbo.provider.ISysLogininforService.version}")
public class SysLogininforServiceImpl extends ServiceImpl<SysLogininforMapper, SysLogininfor> implements ISysLogininforService {
}
