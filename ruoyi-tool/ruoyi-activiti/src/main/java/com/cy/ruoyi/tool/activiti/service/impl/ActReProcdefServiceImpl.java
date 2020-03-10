package com.cy.ruoyi.tool.activiti.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.core.util.page.Query;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import com.cy.ruoyi.tool.activiti.entity.ActReProcdef;
import com.cy.ruoyi.tool.activiti.mapper.ActReProcdefMapper;
import com.cy.ruoyi.tool.activiti.service.IActReProcdefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.EntityWriter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 */
@Service
public class ActReProcdefServiceImpl extends ServiceImpl<ActReProcdefMapper, ActReProcdef> implements IActReProcdefService
{
    @Autowired
    private ActReProcdefMapper procdefMapper;

    @Override
    public List<ActReProcdef> selectList(ActReProcdef actReProcdef)
    {
        return procdefMapper.selectList(new QueryWrapper<ActReProcdef>());
    }

    /**
     * 根据条件分页查询部门信息
     */
    @Override
    public PageUtils selectList(PageDomain pageDomain, ActReProcdef actReProcdef)
    {
        IPage<ActReProcdef> page = procdefMapper.selectPage(new Query<ActReProcdef>(pageDomain).getPage(), new QueryWrapper<ActReProcdef>()
                .eq(RegexUtil.isNotNull(actReProcdef.getKey()), "KEY_", actReProcdef.getKey()));
        return new PageUtils(page);
    }

}
