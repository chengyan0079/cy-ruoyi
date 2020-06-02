package com.cy.ruoyi.tool.auth.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.auth.DTO.LoginUserDTO;
import com.cy.ruoyi.common.core.exception.BusinessException;
import com.cy.ruoyi.common.core.util.ServletUtils;
import com.cy.ruoyi.common.redis.annotation.RedisEvict;
import com.cy.ruoyi.common.redis.util.RedisUtils;
import com.cy.ruoyi.common.utils.constants.Constants;
import com.cy.ruoyi.common.utils.enums.TradeErrorEnum;
import com.cy.ruoyi.common.utils.util.AddressUtils;
import com.cy.ruoyi.common.utils.util.IpUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("accessTokenService")
public class AccessTokenService
{
    private static final Log log = LogFactory.get();

    @Autowired
    private RedisUtils redis;

    /**
     * 12分钟后过期
     */
    private final static long   EXPIRE        = 12 * 60;

    private final static String ACCESS_TOKEN  = Constants.ACCESS_TOKEN;

    private final static String ACCESS_USERID = Constants.ACCESS_USERID;

    public LoginUserDTO queryByToken(String token) {
        try {
            return redis.get(ACCESS_TOKEN + token, LoginUserDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(TradeErrorEnum.AUTH_REDIS_GET_ERROR.msg, e);
        }
        return null;
    }

    @RedisEvict(key = "user_perms", fieldKey = "#sysUser.user.userId")
    public Map<String, Object> createToken(LoginUserDTO sysUser) throws BusinessException {
        // 生成token
        String token = IdUtil.fastSimpleUUID();
        log.info("生成token为:{}", token);

        // 保存或更新用户token
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", sysUser.getUser().getUserId());
        map.put("token", token);
        map.put("expire", EXPIRE);

        sysUser.setToken(token);
        setUserAgent(sysUser);
        // expireToken(userId);
        try {
            redis.set(ACCESS_TOKEN + token, sysUser, EXPIRE);
            redis.set(ACCESS_USERID + sysUser.getUser().getUserId(), token, EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(TradeErrorEnum.AUTH_REDIS_SET_ERROR.msg, e);
        }

        return map;
    }

    public void expireToken(long userId)
    {
        String token = redis.get(ACCESS_USERID + userId);
        if (StringUtils.isNotBlank(token))
        {
            redis.delete(ACCESS_USERID + userId);
            redis.delete(ACCESS_TOKEN + token);
        }
    }

    /**
     * 设置用户代理信息
     *
     * @param userDTO 登录信息
     */
    private void setUserAgent(LoginUserDTO userDTO)
    {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        userDTO.setUserId(userDTO.getUser().getUserId());
        userDTO.setIpaddr(ip);
        userDTO.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        userDTO.setBrowser(userAgent.getBrowser().getName());
        userDTO.setOs(userAgent.getOperatingSystem().getName());
        userDTO.setLoginTime(DateUtil.now());
    }

}