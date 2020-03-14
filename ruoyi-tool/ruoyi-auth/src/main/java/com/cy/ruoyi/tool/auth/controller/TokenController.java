package com.cy.ruoyi.tool.auth.controller;

import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import com.cy.ruoyi.tool.auth.DTO.LoginDTO;
import com.cy.ruoyi.tool.auth.DTO.SysUserDTO;
import com.cy.ruoyi.tool.auth.service.AccessTokenService;
import com.cy.ruoyi.tool.auth.service.SysLoginService;
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
    public R login(@RequestBody LoginDTO loginDTO)
    {
        logger.info("收到登陆请求。。。。");
        // 用户登录
        SysUserDTO user = sysLoginService.login(loginDTO.getUsername(), loginDTO.getPassword());
        // 获取登录token
        return R.ok(tokenService.createToken(user));
    }

    @PostMapping("logout")
    @ApiOperation(value = "logout")
    public R logout(HttpServletRequest request)
    {
        String token = request.getHeader("token");
        SysUserDTO user = tokenService.queryByToken(token);
        if (RegexUtil.isNotNull(user))
        {
            sysLoginService.logout(user.getLoginName());
            tokenService.expireToken(user.getUserId());
        }
        return R.ok();
    }
}
