package com.cy.ruoyi.user.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.log.annotation.OperLog;
import com.cy.ruoyi.common.log.enums.BusinessType;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.common.utils.util.RandomUtil;
import com.cy.ruoyi.user.api.entity.SysUser;
import com.cy.ruoyi.user.api.service.ISysMenuService;
import com.cy.ruoyi.user.api.service.ISysUserService;
import com.cy.ruoyi.user.api.utils.PasswordUtil;
import com.cy.ruoyi.user.app.annotation.LoginUser;
import com.cy.ruoyi.user.impl.constants.UserConstants;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户 提供者
 * 
 * @author zmr
 * @date 2019-05-20
 */
@RestController
@RequestMapping("user")
public class SysUserController extends BaseController
{
    private static final Log log = LogFactory.get();

    @Reference(validation = "true", version = "${dubbo.provider.ISysUserService.version}")
    private ISysUserService sysUserService;

    @Reference(validation = "true", version = "${dubbo.provider.ISysMenuService.version}")
    private ISysMenuService sysMenuService;

    /**
     * 查询用户
     */
    @GetMapping("get/{userId}")
    public SysUser get(@PathVariable("userId") Long userId)
    {
        return sysUserService.selectUserById(userId);
    }

    @GetMapping("info")
    public SysUser info(@LoginUser SysUser sysUser)
    {
        sysUser.setButtons(sysMenuService.selectPermsByUserId(sysUser.getUserId()));
        return sysUser;
    }

    /**
     * 查询用户
     */
    @GetMapping("find/{username}")
    public SysUser findByUsername(@PathVariable("username") String username)
    {
        return sysUserService.selectUserByLoginName(username);
    }

    /**
     * 查询用户列表
     */
    @GetMapping("list")
    @ApiOperation(value = "分页用户列表")
    public R list(SysUser sysUser)
    {
        PageDomain pageDomain = getPageInfo();
        log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
        PageUtils page = sysUserService.selectUserList(pageDomain, sysUser);
        return R.ok(page);
    }

//    /**
//     * 新增保存用户
//     */
//    @HasPermissions("system:user:add")
//    @PostMapping("save")
//    @OperLog(title = "用户管理", businessType = BusinessType.INSERT)
//    public R addSave(@RequestBody SysUser sysUser)
//    {
//        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(sysUserService.checkLoginNameUnique(sysUser.getLoginName())))
//        {
//            return R.error("新增用户'" + sysUser.getLoginName() + "'失败，登录账号已存在");
//        }
//        else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(sysUserService.checkPhoneUnique(sysUser)))
//        {
//            return R.error("新增用户'" + sysUser.getLoginName() + "'失败，手机号码已存在");
//        }
//        else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(sysUserService.checkEmailUnique(sysUser)))
//        {
//            return R.error("新增用户'" + sysUser.getLoginName() + "'失败，邮箱账号已存在");
//        }
//        sysUser.setSalt(RandomUtil.randomStr(6));
//        sysUser.setPassword(
//                PasswordUtil.encryptPassword(sysUser.getLoginName(), sysUser.getPassword(), sysUser.getSalt()));
//        sysUser.setCreateBy(getLoginName());
//        return toAjax(sysUserService.insertUser(sysUser));
//    }

//    /**
//     * 修改保存用户
//     */
//    @HasPermissions("system:user:edit")
//    @OperLog(title = "用户管理", businessType = BusinessType.UPDATE)
//    @PostMapping("update")
//    public R editSave(@RequestBody SysUser sysUser)
//    {
//        if (null != sysUser.getUserId() && SysUser.isAdmin(sysUser.getUserId()))
//        {
//            return R.error("不允许修改超级管理员用户");
//        }
//        else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(sysUserService.checkPhoneUnique(sysUser)))
//        {
//            return R.error("修改用户'" + sysUser.getLoginName() + "'失败，手机号码已存在");
//        }
//        else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(sysUserService.checkEmailUnique(sysUser)))
//        {
//            return R.error("修改用户'" + sysUser.getLoginName() + "'失败，邮箱账号已存在");
//        }
//        return toAjax(sysUserService.updateUser(sysUser));
//    }

//    /**
//     * 修改用户信息
//     * @param sysUser
//     * @return
//     * @author zmr
//     */
//    @HasPermissions("system:user:edit")
//    @PostMapping("update/info")
//    @OperLog(title = "用户管理", businessType = BusinessType.UPDATE)
//    public R updateInfo(@RequestBody SysUser sysUser)
//    {
//        return toAjax(sysUserService.updateUserInfo(sysUser));
//    }

    /**
     * 记录登陆信息
     * @param sysUser
     * @return
     * @author zmr
     */
    @PostMapping("update/login")
    public R updateLoginRecord(@RequestBody SysUser sysUser)
    {
        return toAjax(sysUserService.updateUser(sysUser));
    }
//
//    @HasPermissions("system:user:resetPwd")
//    @OperLog(title = "重置密码", businessType = BusinessType.UPDATE)
//    @PostMapping("/resetPwd")
//    public R resetPwdSave(@RequestBody SysUser user)
//    {
//        user.setSalt(RandomUtil.randomStr(6));
//        user.setPassword(PasswordUtil.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
//        return toAjax(sysUserService.resetUserPwd(user));
//    }
//
//    /**
//     * 修改状态
//     * @param sysUser
//     * @return
//     * @author zmr
//     */
//    @HasPermissions("system:user:edit")
//    @PostMapping("status")
//    @OperLog(title = "用户管理", businessType = BusinessType.UPDATE)
//    public R status(@RequestBody SysUser sysUser)
//    {
//        return toAjax(sysUserService.changeStatus(sysUser));
//    }
//
//    /**
//     * 删除用户
//     * @throws Exception
//     */
//    @HasPermissions("system:user:remove")
//    @OperLog(title = "用户管理", businessType = BusinessType.DELETE)
//    @PostMapping("remove")
//    public R remove(String ids) throws Exception
//    {
//        return toAjax(sysUserService.deleteUserByIds(ids));
//    }
}
