package com.cy.ruoyi.user.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.auth.DTO.LoginUserDTO;
import com.cy.ruoyi.common.auth.annotation.HasPermissions;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.log.annotation.OperLog;
import com.cy.ruoyi.common.log.enums.BusinessType;
import com.cy.ruoyi.common.redis.util.RedisUtils;
import com.cy.ruoyi.common.utils.constants.Constants;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.common.utils.util.StringUtils;
import com.cy.ruoyi.user.api.entity.SysUserOnline;
import com.cy.ruoyi.user.api.service.ISysUserOnlineService;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 在线用户监控
 */
@RestController
@RequestMapping("/online")
@Api(value = "SysUserOnlineController",description = "在线用户监控")
public class SysUserOnlineController extends BaseController
{
    private static final Log log = LogFactory.get();

    @Reference(validation = "true", version = "${dubbo.provider.ISysUserOnlineService.version}")
    private ISysUserOnlineService userOnlineService;

    @Autowired
    private RedisUtils redis;

    private final static String ACCESS_TOKEN  = Constants.ACCESS_TOKEN;

    private final static String ACCESS_USERID = Constants.ACCESS_USERID;

//    @HasPermissions("monitor:online:list")
    @GetMapping("/list")
    public R list(String ipaddr, String userName)
    {
        PageDomain pageDomain = getPageInfo();
        log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
        Collection<String> keys = redis.keys(ACCESS_TOKEN + "*");
        List<SysUserOnline> userOnlineList = new ArrayList<SysUserOnline>();
        for (String key : keys)
        {
            LoginUserDTO user = redis.get(key, LoginUserDTO.class);
            if (StringUtils.isNotEmpty(ipaddr) && StringUtils.isNotEmpty(userName))
            {
                if (StringUtils.equals(ipaddr, user.getIpaddr()) && StringUtils.equals(userName, user.getUser().getUserName()))
                {
                    userOnlineList.add(userOnlineService.selectOnlineByInfo(ipaddr, userName, user));
                }
            }
            else if (StringUtils.isNotEmpty(ipaddr))
            {
                if (StringUtils.equals(ipaddr, user.getIpaddr()))
                {
                    userOnlineList.add(userOnlineService.selectOnlineByIpaddr(ipaddr, user));
                }
            }
            else if (StringUtils.isNotEmpty(userName) && StringUtils.isNotNull(user.getUser()))
            {
                if (StringUtils.equals(userName, user.getUser().getUserName()))
                {
                    userOnlineList.add(userOnlineService.selectOnlineByUserName(userName, user));
                }
            }
            else
            {
                userOnlineList.add(userOnlineService.loginUserToUserOnline(user));
            }
        }
        Collections.reverse(userOnlineList);
        userOnlineList.removeAll(Collections.singleton(null));

        return R.ok(new PageUtils(userOnlineList, userOnlineList.size(), pageDomain.getPageSize(), pageDomain.getPageNum()));

    }

    /**
     * 强退用户
     */
//    @HasPermissions("monitor:online:forceLogout")
    @OperLog(title = "在线用户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userId}")
    public R forceLogout(@PathVariable String userId)
    {

        String token = redis.get(ACCESS_USERID + userId);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(token))
        {
            redis.delete(ACCESS_USERID + userId);
            redis.delete(ACCESS_TOKEN + token);
        }

        return R.ok();
    }
}
