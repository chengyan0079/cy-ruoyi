package com.cy.ruoyi.demo.consumer.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.demo.provider.api.service.ITestProviderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  测试Controller
 */
@RestController
@RequestMapping("/conTest")
@Api(value = "conTest",description = "Demo测试")
public class TestController extends BaseController {

    private static final Log log = LogFactory.get();


    @Reference(validation = "true", version = "${dubbo.consumer.ITestProviderService.version}")
    ITestProviderService testProviderService;

    @PostMapping("/echo/{msg}")
    @ApiOperation(value = "测试msg")
//    @SentinelResource("/echo/{msg}")
    public String echo(@PathVariable String msg){
        return "conTest ===" + msg;
    }

    @PostMapping("/testMsg/{msg}")
    @ApiOperation(value = "testConsumerMsg")
//    @SentinelResource("/testMsg/{msg}")
    public String testConsumerMsg(@PathVariable String msg){
        return testProviderService.testProviderMsg(msg);
    }

}
