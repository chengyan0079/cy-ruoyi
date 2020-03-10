package com.cy.ruoyi.tool.activiti.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.tool.activiti.VO.HiProcInsVo;
import com.cy.ruoyi.tool.activiti.entity.BizLeave;

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
