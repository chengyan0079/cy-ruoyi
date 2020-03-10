package com.cy.ruoyi.tool.activiti.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.tool.activiti.entity.ActReModel;
import com.cy.ruoyi.tool.activiti.entity.ActReProcdef;

import java.util.List;

/**
 * 流程设计模型部署Service接口
 */
public interface IActReModelService extends IService<ActReModel>
{
    /**
     * 查询流程设计模型部署
     * 
     * @param id 流程设计模型部署ID
     * @return 流程设计模型部署
     */
    ActReModel selectActReModelById(String id);

    /**
     * 查询流程设计模型部署列表
     * 
     * @param actReModel 流程设计模型部署
     * @return 流程设计模型部署集合
     */
    List<ActReModel> selectActReModelList(ActReModel actReModel);

    /**
     * 新增流程设计模型部署
     * 
     * @param actReModel 流程设计模型部署
     * @return 结果
     */
    int insertActReModel(ActReModel actReModel);

    /**
     * 修改流程设计模型部署
     * 
     * @param actReModel 流程设计模型部署
     * @return 结果
     */
    int updateActReModel(ActReModel actReModel);

    /**
     * 批量删除流程设计模型部署
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteActReModelByIds(String ids);

    /**
     * 删除流程设计模型部署信息
     * 
     * @param id 流程设计模型部署ID
     * @return 结果
     */
    int deleteActReModelById(String id);

    //***************************************************************************
    /**
     * 根据条件分页查询列表
     */
    PageUtils selectActReModelList(PageDomain pageDomain, ActReModel actReModel);
}
