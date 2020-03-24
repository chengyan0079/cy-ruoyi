package com.cy.ruoyi.demo.provider.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.demo.consumer.api.service.ITestConsumerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

/**
 *  测试Controller
 */
@RestController
@RequestMapping("/proTest")
@Api(value = "proTest",description = "Demo测试")
public class TestController extends BaseController {

    private static final Log log = LogFactory.get();

    @Reference(validation = "true", version = "${dubbo.consumer.ITestConsumerService.version}")
    ITestConsumerService testConsumerService;

    @PostMapping("/echo/{msg}")
    @ApiOperation(value = "测试msg")
    @SentinelResource("/echo/{msg}")
    public String echo(@PathVariable String msg){
        return "proTest ===" + msg;
    }


    @PostMapping("/testMsg/{msg}")
    @ApiOperation(value = "testConsumerMsg")
    @SentinelResource("/testMsg/{msg}")
    public String testConsumerMsg(@PathVariable String msg){
        return testConsumerService.testConsumerMsg(msg);
    }
}
