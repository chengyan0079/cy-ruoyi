package com.cy.ruoyi.demo.provider.api.service;

import com.cy.ruoyi.common.sql.page.PageDomain;
import com.cy.ruoyi.common.sql.page.PageUtils;
import com.cy.ruoyi.demo.provider.api.BO.OrderInfoBO;
import com.cy.ruoyi.demo.provider.api.DTO.OrderInfoDTO;

import java.util.List;

/**
 * 订单信息表
 */
public interface ITbOrderInfoService {

    /**
     * 根据条件分页查询订单列表
     */
    PageUtils selectOrderList(PageDomain pageDomain, OrderInfoDTO orderInfo);

    /**
     * 新增保存订单信息
     */
    int insertOrder(OrderInfoDTO orderInfo);

    /**
     * 修改保存订单信息
     */
    int updateOrder(OrderInfoDTO orderInfo);

    /**
     * 查询所有订单
     */
    List<OrderInfoBO> queryOrderInfo(OrderInfoDTO orderInfo);

}

