package com.cy.ruoyi.tool.auth.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.redis.annotation.RedisEvict;
import com.cy.ruoyi.common.redis.util.RedisUtils;
import com.cy.ruoyi.common.utils.constants.Constants;
import com.cy.ruoyi.tool.auth.DTO.SysUserDTO;
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
     * 12小时后过期
     */
    private final static long   EXPIRE        = 12 * 60 * 60;

    private final static String ACCESS_TOKEN  = Constants.ACCESS_TOKEN;

    private final static String ACCESS_USERID = Constants.ACCESS_USERID;

    public SysUserDTO queryByToken(String token)
    {
        return redis.get(ACCESS_TOKEN + token, SysUserDTO.class);
    }

    @RedisEvict(key = "user_perms", fieldKey = "#sysUser.userId")
    public Map<String, Object> createToken(SysUserDTO sysUser)
    {
        // 生成token
        String token = IdUtil.fastSimpleUUID();
        log.info("生成token为:{}", token);
        // 保存或更新用户token
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", sysUser.getUserId());
        map.put("token", token);
        map.put("expire", EXPIRE);
        // expireToken(userId);
        redis.set(ACCESS_TOKEN + token, sysUser, EXPIRE);
        redis.set(ACCESS_USERID + sysUser.getUserId(), token, EXPIRE);
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
}