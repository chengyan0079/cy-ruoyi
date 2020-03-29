package com.cy.ruoyi.user.impl.service;

import com.cy.ruoyi.common.auth.DTO.LoginUserDTO;
import com.cy.ruoyi.common.utils.util.StringUtils;
import com.cy.ruoyi.user.api.entity.SysUserOnline;
import com.cy.ruoyi.user.api.service.ISysUserOnlineService;
import org.springframework.stereotype.Service;

/**
 * 在线用户 服务层处理
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.ISysUserOnlineService.version}")
public class SysUserOnlineServiceImpl implements ISysUserOnlineService
{
    @Override
    public SysUserOnline selectOnlineByIpaddr(String ipaddr, LoginUserDTO user) {
        if (StringUtils.equals(ipaddr, user.getIpaddr()))
        {
            return loginUserToUserOnline(user);
        }
        return null;
    }

    @Override
    public SysUserOnline selectOnlineByUserName(String userName, LoginUserDTO user) {
        if (StringUtils.equals(userName, user.getUser().getUserName()))
        {
            return loginUserToUserOnline(user);
        }
        return null;
    }

    @Override
    public SysUserOnline selectOnlineByInfo(String ipaddr, String userName, LoginUserDTO user) {
        if (StringUtils.equals(ipaddr, user.getIpaddr()) && StringUtils.equals(userName, user.getUser().getUserName()))
        {
            return loginUserToUserOnline(user);
        }
        return null;
    }

    @Override
    public SysUserOnline loginUserToUserOnline(LoginUserDTO user) {
        if (StringUtils.isNull(user) && StringUtils.isNull(user.getUser()))
        {
            return null;
        }
        SysUserOnline sysUserOnline = new SysUserOnline();
        sysUserOnline.setToken(user.getToken());
        sysUserOnline.setLoginName(user.getUser().getUserName());
        sysUserOnline.setIpaddr(user.getIpaddr());
        sysUserOnline.setLoginLocation(user.getLoginLocation());
        sysUserOnline.setBrowser(user.getBrowser());
        sysUserOnline.setOs(user.getOs());
        sysUserOnline.setLoginTime(user.getLoginTime());
        if (StringUtils.isNotNull(user.getUser().getDept()))
        {
            sysUserOnline.setDeptName(user.getUser().getDept().getDeptName());
        }
        return sysUserOnline;
    }

//    /**
//     * 通过会话序号查询信息
//     *
//     * @param sessionId 会话ID
//     * @return 在线用户信息
//     */
//    @Override
//    public SysUserOnline selectOnlineById(String sessionId)
//    {
//        return userOnlineDao.selectOnlineById(sessionId);
//    }
//
//    /**
//     * 通过会话序号删除信息
//     *
//     * @param sessionId 会话ID
//     * @return 在线用户信息
//     */
//    @Override
//    public void deleteOnlineById(String sessionId)
//    {
//        SysUserOnline userOnline = selectOnlineById(sessionId);
//        if (StringUtils.isNotNull(userOnline))
//        {
//            userOnlineDao.deleteOnlineById(sessionId);
//        }
//    }
//
//    /**
//     * 通过会话序号删除信息
//     *
//     * @param sessions 会话ID集合
//     * @return 在线用户信息
//     */
//    @Override
//    public void batchDeleteOnline(List<String> sessions)
//    {
//        for (String sessionId : sessions)
//        {
//            SysUserOnline userOnline = selectOnlineById(sessionId);
//            if (StringUtils.isNotNull(userOnline))
//            {
//                userOnlineDao.deleteOnlineById(sessionId);
//            }
//        }
//    }
//
//    /**
//     * 保存会话信息
//     *
//     * @param online 会话信息
//     */
//    @Override
//    public void saveOnline(SysUserOnline online)
//    {
//        userOnlineDao.saveOnline(online);
//    }
//
//    /**
//     * 查询会话集合
//     *
//     * @param userOnline 在线用户
//     */
//    @Override
//    public List<SysUserOnline> selectUserOnlineList(SysUserOnline userOnline)
//    {
//        return userOnlineDao.selectUserOnlineList(userOnline);
//    }
//
//    /**
//     * 强退用户
//     *
//     * @param sessionId 会话ID
//     */
//    @Override
//    public void forceLogout(String sessionId)
//    {
//        userOnlineDao.deleteOnlineById(sessionId);
//    }
//
//    /**
//     * 查询会话集合
//     *
//     * @param expiredDate 失效日期
//     */
//    @Override
//    public List<SysUserOnline> selectOnlineByExpired(Date expiredDate)
//    {
//        String lastAccessTime = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, expiredDate);
//        return userOnlineDao.selectOnlineByExpired(lastAccessTime);
//    }



}
