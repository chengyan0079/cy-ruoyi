package com.cy.ruoyi.admin.auth.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.common.auth.DTO.LoginDTO;
import com.cy.ruoyi.common.auth.DTO.LoginUserDTO;
import com.cy.ruoyi.common.auth.DTO.SysUserDTO;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import com.cy.ruoyi.admin.auth.service.AccessTokenService;
import com.cy.ruoyi.admin.auth.service.SysLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dromara.soul.client.common.annotation.SoulClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(value = "TokenController",description = "登陆")
public class TokenController
{
    private static final Log log = LogFactory.get();

    @Autowired
    AccessTokenService tokenService;

    @Autowired
    SysLoginService sysLoginService;

    @PostMapping("login")
    @ApiOperation(value = "login")
    @SentinelResource("login")
    public R login(@RequestBody LoginDTO loginDTO)
    {
        log.info("收到登陆请求。。。。");
        // 用户登录
        SysUserDTO user = sysLoginService.login(loginDTO.getUsername(), loginDTO.getPassword());
        // 获取登录token
        LoginUserDTO userDTO = new LoginUserDTO();
        userDTO.setUser(user);
        return R.ok(tokenService.createToken(userDTO));
    }

    @PostMapping("logout")
    @ApiOperation(value = "logout")
    @SentinelResource("logout")
    public R logout(HttpServletRequest request)
    {
        String token = request.getHeader("token");
        LoginUserDTO user = tokenService.queryByToken(token);
        if (RegexUtil.isNotNull(user))
        {
            sysLoginService.logout(user.getUser().getLoginName());
            tokenService.expireToken(user.getUser().getUserId());
        }
        return R.ok();
    }
}
