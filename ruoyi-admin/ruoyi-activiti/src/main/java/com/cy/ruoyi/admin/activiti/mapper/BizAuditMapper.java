/*
 * @(#)ActBusinessMapper.java 2020年1月6日 下午3:38:12
 * Copyright 2020 zmr, Inc. All rights reserved. 
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cy.ruoyi.admin.activiti.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.ruoyi.admin.activiti.entity.BizAudit;
import com.cy.ruoyi.admin.activiti.VO.HiTaskVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 */
@Mapper
public interface BizAuditMapper extends BaseMapper<BizAudit>
{
    List<HiTaskVo> getHistoryTaskList(@Param("hit") HiTaskVo hiTaskVo);

    /**
     * logic删除
     * @param ids
     * @return
     * @author zmr
     */
    int deleteLogic(String[] ids);

    //************************************************************************
    /**
     * 根据条件分页查询列表
     * @param page
     * @param hiTaskVo
     * @return
     */
    IPage<HiTaskVo> getHistoryTaskList(Page page, @Param("hit") HiTaskVo hiTaskVo);
}
