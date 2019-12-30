package com.cy.ruoyi.user.impl.service;

import com.cy.ruoyi.user.api.service.TestService;
import org.apache.dubbo.config.annotation.Service;

@Service(validation = "true", version = "${dubbo.provider.TestService.version}")
public class TestServiceImpl implements TestService {

    @Override
    public String echo(String msg) {
        return "Hello,TestServiceImpl " + msg;
    }
}
