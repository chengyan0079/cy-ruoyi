package com.cy.ruoyi.product.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  测试Controller
 */
@RestController
@RequestMapping("/prodtest")
@Api(value = "producttest",description = "product测试")
public class TestController extends BaseController {

    private static final Log log = LogFactory.get();

    @PostMapping("/echo/{msg}")
    @ApiOperation(value = "测试msg")
    public String echo(@PathVariable String msg){
        return "product===" + msg;
    }
}
