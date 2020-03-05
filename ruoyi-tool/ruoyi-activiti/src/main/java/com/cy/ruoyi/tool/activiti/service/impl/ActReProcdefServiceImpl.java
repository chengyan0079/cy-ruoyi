package com.cy.ruoyi.tool.activiti.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.ruoyi.tool.activiti.entity.ActReProcdef;
import com.cy.ruoyi.tool.activiti.mapper.ActReProcdefMapper;
import com.cy.ruoyi.tool.activiti.service.IActReProcdefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.IActReProcdefService.version}")
public class ActReProcdefServiceImpl extends ServiceImpl<ActReProcdefMapper, ActReProcdef> implements IActReProcdefService
{
//    @Autowired
//    private ActReProcdefMapper procdefMapper;

//    /* (non-Javadoc)
//     * @see com.ruoyi.activiti.service.IActReProcdefService#selectList(com.ruoyi.activiti.entity.ActReProcdef)
//     */
//    @Override
//    public List<ActReProcdef> selectList(ActReProcdef actReProcdef)
//    {
//        return procdefMapper.selectList(actReProcdef);
//    }
}
