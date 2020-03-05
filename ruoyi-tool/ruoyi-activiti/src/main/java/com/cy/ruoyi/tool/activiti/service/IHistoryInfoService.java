package com.cy.ruoyi.tool.activiti.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.ruoyi.tool.activiti.VO.HiProcInsVo;
import java.util.List;

/**
 */
public interface IHistoryInfoService extends IService<HiProcInsVo>
{
    List<HiProcInsVo> getHiProcInsListDone(HiProcInsVo hiProcInsVo);
}
