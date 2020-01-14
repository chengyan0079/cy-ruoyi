package com.cy.ruoyi.user.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.log.annotation.OperLog;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.user.api.entity.SysUser;
import com.cy.ruoyi.user.api.service.ISysMenuService;
import com.cy.ruoyi.user.api.service.ISysUserService;
import com.cy.ruoyi.user.api.service.TestService;
import com.cy.ruoyi.user.app.annotation.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  测试Controller
 */
@RestController
@RequestMapping("/test")
@Api(value = "UseTest",description = "用户测试")
public class TestController extends BaseController {

    private static final Log log = LogFactory.get();

    @Reference(validation = "true", version = "${dubbo.provider.TestService.version}")
    private TestService testService;

    @Reference(validation = "true", version = "${dubbo.provider.ISysUserService.version}")
    private ISysUserService userService;

    @Reference(validation = "true", version = "${dubbo.provider.ISysMenuService.version}")
    private ISysMenuService menuService;

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
        return R.data(testService.getList(user));
    }

    @PostMapping("/userList")
    @ApiOperation(value = "所有用户列表")
    public R userList(SysUser user){
        return R.data(userService.selectUserList(user));
    }

    @PostMapping("/menuList/{id}")
    @ApiOperation(value = "所有菜单列表")
    public R menuList(@PathVariable Long id){
        return R.data(menuService.selectPermsByUserId(id));
    }

}
