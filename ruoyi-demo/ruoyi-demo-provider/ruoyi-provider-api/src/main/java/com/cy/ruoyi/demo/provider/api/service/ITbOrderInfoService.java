package com.cy.ruoyi.demo.provider.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.demo.provider.api.entity.TbOrderInfo;

/**
 * 订单信息表
 */
public interface ITbOrderInfoService extends IService<TbOrderInfo> {

    /**
     * 根据条件分页查询订单列表
     */
    PageUtils selectOrderList(PageDomain pageDomain, TbOrderInfo orderInfo);

    /**
     * 新增保存订单信息
     */
    int insertOrder(TbOrderInfo orderInfo);

    /**
     * 修改保存订单信息
     */
    int updateOrder(TbOrderInfo orderInfo);

}

