package com.cy.ruoyi.demo.consumer.impl.service;

import com.cy.ruoyi.demo.consumer.api.service.ITestConsumerService;
import org.springframework.stereotype.Service;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.ITestConsumerService.version}")
public class TestConsumerServiceImpl implements ITestConsumerService {
    @Override
    public String testConsumerMsg(String msg) {
        return "Hello, I'm Consumer service! Your massage is " + msg;
    }
}
