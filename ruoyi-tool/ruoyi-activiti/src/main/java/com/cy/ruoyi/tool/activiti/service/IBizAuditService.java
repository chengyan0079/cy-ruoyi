/*
 * @(#)IBizAuditService.java 2020年1月6日 下午3:38:40
 * Copyright 2020 zmr, Inc. All rights reserved. 
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cy.ruoyi.tool.activiti.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.tool.activiti.VO.HiTaskVo;
import com.cy.ruoyi.tool.activiti.entity.ActReProcdef;
import com.cy.ruoyi.tool.activiti.entity.BizAudit;

import java.util.List;

/**
 */
public interface IBizAuditService extends IService<BizAudit>
{
    /**
     * 查询审核记录
     * 
     * @param id 审核记录ID
     * @return 审核记录
     */
//    BizAudit selectBizAuditById(String id);

    /**
     * 查询审核记录列表
     * 
     * @param bizAudit 审核记录
     * @return 审核记录集合
     */
//    List<BizAudit> selectBizAuditList(BizAudit bizAudit);

    /**
     * 新增审核记录
     * 
     * @param bizAudit 审核记录
     * @return 结果
     */
//    int insertBizAudit(BizAudit bizAudit);

    /**
     * 修改审核记录
     * 
     * @param bizAudit 审核记录
     * @return 结果
     */
//    int updateBizAudit(BizAudit bizAudit);

    /**
     * 批量删除审核记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
//    int deleteBizAuditByIds(String ids);

    /**
     * 批量删除审核记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBizAuditLogic(String ids);

    /**
     * 删除审核记录信息
     * 
     * @param id 审核记录ID
     * @return 结果
     */
//    int deleteBizAuditById(Long id);

    /**
     * history task 历史任务记录
     * 
     * @param hiTaskVo
     * @return
     * @author zmr
     */
    List<HiTaskVo> getHistoryTaskList(HiTaskVo hiTaskVo);

    //***************************************************************************
    /**
     * 根据条件分页查询列表
     */
    PageUtils getHistoryTaskList(PageDomain pageDomain, HiTaskVo hiTaskVo);
}
