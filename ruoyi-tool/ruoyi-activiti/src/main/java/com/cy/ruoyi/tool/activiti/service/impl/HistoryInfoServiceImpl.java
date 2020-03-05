package com.cy.ruoyi.tool.activiti.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.ruoyi.tool.activiti.VO.HiProcInsVo;
import com.cy.ruoyi.tool.activiti.mapper.HistoryMapper;
import com.cy.ruoyi.tool.activiti.service.IHistoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 */
@Service
public class HistoryInfoServiceImpl extends ServiceImpl<HistoryMapper, HiProcInsVo> implements IHistoryInfoService
{
    @Autowired
    private HistoryMapper historyMapper;

    /* (non-Javadoc)
     * @see com.ruoyi.activiti.service.IHistoryInfoService#getHiProcInsListDone(com.ruoyi.activiti.vo.HiProcInsVo)
     */
    @Override
    public List<HiProcInsVo> getHiProcInsListDone(HiProcInsVo hiProcInsVo)
    {
        return historyMapper.getHiProcInsListDone(hiProcInsVo);
    }
}
