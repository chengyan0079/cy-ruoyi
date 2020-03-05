package com.cy.ruoyi.tool.activiti.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.ruoyi.tool.activiti.entity.BizPurchase;
import com.cy.ruoyi.tool.activiti.mapper.BizPurchaseMapper;
import com.cy.ruoyi.tool.activiti.service.IBizPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报销Service业务层处理
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.IBizPurchaseService.version}")
public class BizPurchaseServiceImpl extends ServiceImpl<BizPurchaseMapper, BizPurchase> implements IBizPurchaseService
{
    @Autowired
    private BizPurchaseMapper purchaseMapper;

    /**
     * 查询报销
     * 
     * @param id 报销ID
     * @return 报销
     */
//    @Override
//    public BizPurchase selectBizPurchaseById(String id)
//    {
//        return purchaseMapper.selectByPrimaryKey(id);
//    }

    /**
     * 查询报销列表
     * 
     * @param bizPurchase 报销
     * @return 报销
     */
//    @Override
//    public List<BizPurchase> selectBizPurchaseList(BizPurchase bizPurchase)
//    {
//        return purchaseMapper.select(bizPurchase);
//    }

    /**
     * 新增报销
     * 
     * @param bizPurchase 报销
     * @return 结果
     */
//    @Override
//    public int insertBizPurchase(BizPurchase bizPurchase)
//    {
//        return purchaseMapper.insertSelective(bizPurchase);
//    }

//    /**
//     * 修改报销
//     *
//     * @param bizPurchase 报销
//     * @return 结果
//     */
//    @Override
//    public int updateBizPurchase(BizPurchase bizPurchase)
//    {
//        return purchaseMapper.updateByPrimaryKeySelective(bizPurchase);
//    }

//    /**
//     * 删除报销对象
//     *
//     * @param ids 需要删除的数据ID
//     * @return 结果
//     */
//    @Override
//    public int deleteBizPurchaseByIds(String ids)
//    {
//        return purchaseMapper.deleteByIds(ids);
//    }
//
//    /**
//     * 删除报销信息
//     *
//     * @param id 报销ID
//     * @return 结果
//     */
//    public int deleteBizPurchaseById(String id)
//    {
//        return purchaseMapper.deleteByPrimaryKey(id);
//    }
}
