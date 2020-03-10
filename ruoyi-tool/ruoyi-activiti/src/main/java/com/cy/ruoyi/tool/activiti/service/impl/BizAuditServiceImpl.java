/*
 * @(#)BizAuditServiceImpl.java 2020年1月6日 下午3:38:49
 * Copyright 2020 zmr, Inc. All rights reserved. 
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cy.ruoyi.tool.activiti.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.core.util.page.Query;
import com.cy.ruoyi.tool.activiti.VO.HiTaskVo;
import com.cy.ruoyi.tool.activiti.entity.ActReProcdef;
import com.cy.ruoyi.tool.activiti.entity.BizAudit;
import com.cy.ruoyi.tool.activiti.mapper.BizAuditMapper;
import com.cy.ruoyi.tool.activiti.service.IBizAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 */
@Service
public class BizAuditServiceImpl extends ServiceImpl<BizAuditMapper, BizAudit> implements IBizAuditService
{
    @Autowired
    private BizAuditMapper auditMapper;

    /**
     * 查询审核记录
     * 
     * @param id 审核记录ID
     * @return 审核记录
     */
//    @Override
//    public BizAudit selectBizAuditById(String id)
//    {
//        return auditMapper.selectByPrimaryKey(id);
//    }

    /**
     * 查询审核记录列表
     * 
     * @param bizAudit 审核记录
     * @return 审核记录
     */
//    @Override
//    public List<BizAudit> selectBizAuditList(BizAudit bizAudit)
//    {
//        return auditMapper.select(bizAudit);
//    }

    /**
     * 新增审核记录
     * 
     * @param bizAudit 审核记录
     * @return 结果
     */
//    @Override
//    public int insertBizAudit(BizAudit bizAudit)
//    {
//        return auditMapper.insertSelective(bizAudit);
//    }

    /**
     * 修改审核记录
     * 
     * @param bizAudit 审核记录
     * @return 结果
     */
//    @Override
//    public int updateBizAudit(BizAudit bizAudit)
//    {
//        return auditMapper.updateByPrimaryKeySelective(bizAudit);
//    }

    /**
     * 删除审核记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
//    @Override
//    public int deleteBizAuditByIds(String ids)
//    {
//        return auditMapper.deleteByIds(ids);
//    }
    /**
     * 删除审核记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBizAuditLogic(String ids)
    {
        String[] idArr=ids.split(",");
        return auditMapper.deleteLogic(idArr);
    }

//    /**
//     * 删除审核记录信息
//     *
//     * @param id 审核记录ID
//     * @return 结果
//     */
//    public int deleteBizAuditById(Long id)
//    {
//        return auditMapper.deleteByPrimaryKey(id);
//    }

    /* (non-Javadoc)
     * @see com.ruoyi.activiti.service.IBizAuditService#getHistoryTaskList(com.ruoyi.activiti.vo.HiTaskVo)
     */
    @Override
    public List<HiTaskVo> getHistoryTaskList(HiTaskVo hiTaskVo)
    {
        return auditMapper.getHistoryTaskList(hiTaskVo);
    }

    /**
     * 根据条件分页查询部门信息
     */
    @Override
    public PageUtils getHistoryTaskList(PageDomain pageDomain, HiTaskVo hiTaskVo)
    {
        IPage<HiTaskVo> page = auditMapper.getHistoryTaskList(new Query<HiTaskVo>(pageDomain).getPage(), hiTaskVo);
        return new PageUtils(page);
    }


}
