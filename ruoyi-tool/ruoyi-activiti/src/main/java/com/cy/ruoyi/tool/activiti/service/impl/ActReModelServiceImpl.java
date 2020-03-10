package com.cy.ruoyi.tool.activiti.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.core.util.page.Query;
import com.cy.ruoyi.common.utils.text.Convert;
import com.cy.ruoyi.common.utils.util.DateUtils;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import com.cy.ruoyi.tool.activiti.VO.HiTaskVo;
import com.cy.ruoyi.tool.activiti.entity.ActReModel;
import com.cy.ruoyi.tool.activiti.entity.ActReProcdef;
import com.cy.ruoyi.tool.activiti.mapper.ActReModelMapper;
import com.cy.ruoyi.tool.activiti.service.IActReModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 流程设计模型部署Service业务层处理
 */
@Service
public class ActReModelServiceImpl extends ServiceImpl<ActReModelMapper, ActReModel> implements IActReModelService
{
    @Autowired
    private ActReModelMapper actReModelMapper;

    /**
     * 查询流程设计模型部署
     * 
     * @param id 流程设计模型部署ID
     * @return 流程设计模型部署
     */
    @Override
    public ActReModel selectActReModelById(String id)
    {
        return actReModelMapper.selectActReModelById(id);
    }

    /**
     * 查询流程设计模型部署列表
     * 
     * @param actReModel 流程设计模型部署
     * @return 流程设计模型部署
     */
    @Override
    public List<ActReModel> selectActReModelList(ActReModel actReModel)
    {
        return actReModelMapper.selectActReModelList(actReModel);
    }

    /**
     * 新增流程设计模型部署
     * 
     * @param actReModel 流程设计模型部署
     * @return 结果
     */
    @Override
    public int insertActReModel(ActReModel actReModel)
    {
        actReModel.setCreateTime(DateUtils.getNowDate());
        return actReModelMapper.insertActReModel(actReModel);
    }

    /**
     * 修改流程设计模型部署
     * 
     * @param actReModel 流程设计模型部署
     * @return 结果
     */
    @Override
    public int updateActReModel(ActReModel actReModel)
    {
        actReModel.setUpdateTime(DateUtils.getNowDate());
        return actReModelMapper.updateActReModel(actReModel);
    }

    /**
     * 删除流程设计模型部署对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteActReModelByIds(String ids)
    {
        return actReModelMapper.deleteActReModelByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除流程设计模型部署信息
     * 
     * @param id 流程设计模型部署ID
     * @return 结果
     */
    @Override
    public int deleteActReModelById(String id)
    {
        return actReModelMapper.deleteActReModelById(id);
    }


    /**
     * 根据条件分页查询部门信息
     */
    @Override
    public PageUtils selectActReModelList(PageDomain pageDomain, ActReModel actReModel)
    {
        if(RegexUtil.isNull(actReModel)){
            actReModel = new ActReModel();
        }
        IPage<ActReModel> page = actReModelMapper.selectActReModelList(new Query<ActReModel>(pageDomain).getPage(), actReModel);
        return new PageUtils(page);
    }
}
