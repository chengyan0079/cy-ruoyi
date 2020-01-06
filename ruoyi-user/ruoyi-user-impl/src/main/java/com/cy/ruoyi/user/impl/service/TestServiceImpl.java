package com.cy.ruoyi.user.impl.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.ruoyi.user.api.entity.SysUser;
import com.cy.ruoyi.user.api.mapper.SysUserMapper;
import com.cy.ruoyi.user.api.service.TestService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(validation = "true", version = "${dubbo.provider.TestService.version}")
public class TestServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements TestService {

    @Autowired
    SysUserMapper sysUserMapper;


    @Override
    public String echo(String msg) {
        return "Hello,TestServiceImpl " + msg;
    }

    @Override
    public List<SysUser> getList(SysUser sysUser) {
        return sysUserMapper.selectUserList(sysUser);
    }
}
