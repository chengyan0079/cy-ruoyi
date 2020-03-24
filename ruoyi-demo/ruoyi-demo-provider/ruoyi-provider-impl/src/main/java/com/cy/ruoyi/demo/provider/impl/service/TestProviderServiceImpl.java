package com.cy.ruoyi.demo.provider.impl.service;

import com.cy.ruoyi.demo.provider.api.service.ITestProviderService;
import org.springframework.stereotype.Service;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.ITestProviderService.version}")
public class TestProviderServiceImpl implements ITestProviderService {
    @Override
    public String testProviderMsg(String msg) {
        return "Hello, I'm Provider service! Your massage is " + msg;
    }
}
