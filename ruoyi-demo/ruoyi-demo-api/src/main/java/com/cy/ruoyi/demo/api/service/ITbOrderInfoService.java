package com.cy.ruoyi.demo.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.demo.api.entity.TbOrderInfo;

/**
 * 订单信息表
 */
public interface ITbOrderInfoService extends IService<TbOrderInfo> {

    /**
     * 根据条件分页查询订单列表
     */
    PageUtils selectOrderList(PageDomain pageDomain, TbOrderInfo user);

}

