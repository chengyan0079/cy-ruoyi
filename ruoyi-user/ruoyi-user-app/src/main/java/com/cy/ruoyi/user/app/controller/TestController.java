package com.cy.ruoyi.user.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.log.annotation.OperLog;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.user.api.entity.SysUser;
import com.cy.ruoyi.user.api.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  测试Controller
 */
@RestController
@RequestMapping("/user/test")
@Api(value = "UseTest",description = "用户测试")
public class TestController extends BaseController {

    private static final Log log = LogFactory.get();

    @Reference(validation = "true", version = "${dubbo.provider.TestService.version}")
    private TestService testService;

    @PostMapping("/echo/{msg}")
    @ApiOperation(value = "测试msg")
    public String echo(@PathVariable String msg){
        return testService.echo(msg);
    }

    @PostMapping("/getList/{loginName}")
    @ApiOperation(value = "测试loginName")
    public R getList(@PathVariable String loginName){
        SysUser user = new SysUser();
        user.setLoginName(loginName);
//        log.info("传入参数：{}",loginName);
        return R.data(testService.getList(user));
    }

}
