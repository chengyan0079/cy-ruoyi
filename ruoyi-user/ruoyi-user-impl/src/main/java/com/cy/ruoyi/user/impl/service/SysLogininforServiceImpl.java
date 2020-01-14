package com.cy.ruoyi.user.impl.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.ruoyi.user.api.entity.SysLogininfor;
import com.cy.ruoyi.user.api.mapper.SysLogininforMapper;
import com.cy.ruoyi.user.api.service.ISysLogininforService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(validation = "true", version = "${dubbo.provider.ISysLogininforService.version}")
@org.springframework.stereotype.Service
public class SysLogininforServiceImpl extends ServiceImpl<SysLogininforMapper, SysLogininfor> implements ISysLogininforService {

    @Autowired
    private SysLogininforMapper logininforMapper;

    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    @Override
    public void insertLogininfor(SysLogininfor logininfor)
    {
        logininforMapper.insertLogininfor(logininfor);
    }

}
