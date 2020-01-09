package com.cy.ruoyi.tool.auth.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.user.api.entity.SysUser;
import com.cy.ruoyi.user.api.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Api(value = "AuthTest",description = "授权测试")
public class AuthTestController extends BaseController {

    private static final Log log = LogFactory.get();

    @Reference(validation = "true", version = "${dubbo.consumer.TestService.version}")
    private TestService testService;

    @PostMapping("/echo/{msg}")
    @ApiOperation(value = "测试msg")
    public String echo(@PathVariable String msg){
        return "Hello, User Test Service! " + testService.echo(msg);
    }

    @PostMapping("/testMsg/{msg}")
    @ApiOperation(value = "测试msg")
    public String testMsg(@PathVariable String msg){
        return "Hello, testMsg! " + msg;
    }

    @PostMapping("/test/{admin}")
    @ApiOperation(value = "测试admin")
    public R test(@PathVariable String admin){
        SysUser sysUser = new SysUser();
        sysUser.setUserName(admin);
//        log.info("调用User服务，传入参数：{}",admin);
        return R.data(testService.getList(sysUser));
    }

}
