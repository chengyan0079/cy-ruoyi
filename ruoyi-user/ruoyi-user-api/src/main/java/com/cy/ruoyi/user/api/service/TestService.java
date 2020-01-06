package com.cy.ruoyi.user.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.ruoyi.user.api.entity.SysUser;

import java.util.List;

public interface TestService extends IService<SysUser> {

    String echo(String msg);

    List<SysUser> getList(SysUser sysUser);

}
