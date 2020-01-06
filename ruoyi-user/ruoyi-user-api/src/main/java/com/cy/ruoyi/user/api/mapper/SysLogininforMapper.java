package com.cy.ruoyi.user.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.ruoyi.user.api.entity.SysDictType;
import com.cy.ruoyi.user.api.entity.SysLogininfor;
import java.util.List;

/**
 * 系统访问日志情况信息 数据层
 * 
 * @author ruoyi
 */
public interface SysLogininforMapper extends BaseMapper<SysLogininfor>
{
    /**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
    void insertLogininfor(SysLogininfor logininfor);

    /**
     * 查询系统登录日志集合
     * 
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    List<SysLogininfor> selectLogininforList(SysLogininfor logininfor);

    /**
     * 批量删除系统登录日志
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    int deleteLogininforByIds(String[] ids);

    /**
     * 清空系统登录日志
     * 
     * @return 结果
     */
    int cleanLogininfor();
}
