package com.cy.ruoyi.admin.sys.resolver;

import com.cy.ruoyi.admin.sys.base.entity.SysUser;
import com.cy.ruoyi.admin.sys.base.service.ISysUserService;
import com.cy.ruoyi.common.utils.annotation.LoginUser;
import com.cy.ruoyi.common.utils.constants.Constants;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 */
@Configuration
public class LoginUserHandlerResolver implements HandlerMethodArgumentResolver
{
    @Reference(validation = "true", version = "${dubbo.provider.ISysUserService.version}")
    private ISysUserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter)
    {
        return parameter.getParameterType().isAssignableFrom(SysUser.class)
                && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory factory) throws Exception
    {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        // 获取用户ID
        Long userid = Long.valueOf(request.getHeader(Constants.CURRENT_ID));
        if (RegexUtil.isNull(userid))
        {
            return null;
        }
        return userService.selectUserById(userid);
    }
}