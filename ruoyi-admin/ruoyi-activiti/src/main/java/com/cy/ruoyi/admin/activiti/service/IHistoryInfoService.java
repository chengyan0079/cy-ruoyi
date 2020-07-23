package com.cy.ruoyi.admin.activiti.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.ruoyi.admin.activiti.VO.HiProcInsVo;
import com.cy.ruoyi.common.sql.page.PageDomain;
import com.cy.ruoyi.common.sql.page.PageUtils;

import java.util.List;

/**
 */
public interface IHistoryInfoService extends IService<HiProcInsVo>
{
    List<HiProcInsVo> getHiProcInsListDone(HiProcInsVo hiProcInsVo);

    //***************************************************************************
    /**
     * 根据条件分页查询列表
     */
    PageUtils getHiProcInsListDone(PageDomain pageDomain, HiProcInsVo hiProcInsVo);

}
