/*
 * @(#)IActReProcdefService.java 2020年1月6日 上午10:21:18
 * Copyright 2020 zmr, Inc. All rights reserved. 
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cy.ruoyi.admin.activiti.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.ruoyi.admin.activiti.entity.ActReProcdef;
import com.cy.ruoyi.common.sql.page.PageDomain;
import com.cy.ruoyi.common.sql.page.PageUtils;

import java.util.List;

/**
 */
public interface IActReProcdefService extends IService<ActReProcdef>
{
    List<ActReProcdef> selectList(ActReProcdef actReProcdef);


    //***************************************************************************
    /**
     * 根据条件分页查询列表
     */
    PageUtils selectList(PageDomain pageDomain, ActReProcdef actReProcdef);
}
