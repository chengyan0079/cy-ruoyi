package com.cy.ruoyi.admin.sys.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.ruoyi.admin.sys.base.entity.SysUser;

import java.util.List;

public interface TestService extends IService<SysUser> {

    String echo(String msg);

    List<SysUser> getList(SysUser sysUser);

}
