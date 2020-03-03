package com.cy.ruoyi.tool.auth.controller;

import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import com.cy.ruoyi.tool.auth.VO.LoginVO;
import com.cy.ruoyi.tool.auth.service.AccessTokenService;
import com.cy.ruoyi.tool.auth.service.SysLoginService;
import com.cy.ruoyi.user.api.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(value = "TokenController",description = "登陆")
public class TokenController extends BaseController
{
    @Autowired
    AccessTokenService tokenService;

    @Autowired
    SysLoginService sysLoginService;

    @PostMapping("login")
    @ApiOperation(value = "login")
    public R login(@RequestBody LoginVO loginVO)
    {
        logger.info("收到登陆请求。。。。");
        // 用户登录
        SysUser user = sysLoginService.login(loginVO.getUsername(), loginVO.getPassword());
        // 获取登录token
        return R.ok(tokenService.createToken(user));
    }

    @PostMapping("logout")
    @ApiOperation(value = "logout")
    public R logout(HttpServletRequest request)
    {
        String token = request.getHeader("token");
        SysUser user = tokenService.queryByToken(token);
        if (RegexUtil.isNotNull(user))
        {
            sysLoginService.logout(user.getLoginName());
            tokenService.expireToken(user.getUserId());
        }
        return R.ok();
    }
}
