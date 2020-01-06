package com.cy.ruoyi.user.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.ruoyi.user.api.entity.SysUserOnline;
import java.util.List;

/**
 * 在线用户 数据层
 * 
 * @author ruoyi
 */
public interface SysUserOnlineMapper extends BaseMapper<SysUserOnline>
{
    /**
     * 通过会话序号查询信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    SysUserOnline selectOnlineById(String sessionId);

    /**
     * 通过会话序号删除信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    int deleteOnlineById(String sessionId);

    /**
     * 保存会话信息
     * 
     * @param online 会话信息
     * @return 结果
     */
    int saveOnline(SysUserOnline online);

    /**
     * 查询过期会话集合
     * 
     * @param lastAccessTime 过期时间
     * @return 会话集合
     */
    List<SysUserOnline> selectOnlineByExpired(String lastAccessTime);
}
