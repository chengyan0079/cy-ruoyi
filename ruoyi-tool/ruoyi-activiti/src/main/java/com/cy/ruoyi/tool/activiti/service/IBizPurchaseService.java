package com.cy.ruoyi.tool.activiti.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.ruoyi.tool.activiti.entity.BizNode;
import com.cy.ruoyi.tool.activiti.entity.BizPurchase;

import java.util.List;

/**
 * 报销Service接口
 */
public interface IBizPurchaseService extends IService<BizPurchase>
{
    /**
     * 查询报销
     * 
     * @param id 报销ID
     * @return 报销
     */
//    BizPurchase selectBizPurchaseById(String id);

    /**
     * 查询报销列表
     * 
     * @param bizPurchase 报销
     * @return 报销集合
     */
//    List<BizPurchase> selectBizPurchaseList(BizPurchase bizPurchase);

    /**
     * 新增报销
     * 
     * @param bizPurchase 报销
     * @return 结果
     */
//    int insertBizPurchase(BizPurchase bizPurchase);

    /**
     * 修改报销
     * 
     * @param bizPurchase 报销
     * @return 结果
     */
//    int updateBizPurchase(BizPurchase bizPurchase);

    /**
     * 批量删除报销
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
//    int deleteBizPurchaseByIds(String ids);

    /**
     * 删除报销信息
     * 
     * @param id 报销ID
     * @return 结果
     */
//    int deleteBizPurchaseById(String id);
}
