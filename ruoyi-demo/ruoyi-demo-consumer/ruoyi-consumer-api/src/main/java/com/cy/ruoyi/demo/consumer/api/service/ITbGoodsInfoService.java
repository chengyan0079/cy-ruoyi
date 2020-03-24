package com.cy.ruoyi.demo.consumer.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.demo.consumer.api.entity.TbGoodsInfo;

/**
 * 商品信息表
 */
public interface ITbGoodsInfoService extends IService<TbGoodsInfo> {

    /**
     * 根据条件分页查询商品列表
     */
    PageUtils selectGoodsList(PageDomain pageDomain, TbGoodsInfo goodsInfo);

    /**
     * 新增保存商品信息
     */
    int insertGoods(TbGoodsInfo goodsInfo);

    /**
     * 修改保存商品信息
     */
    int updateGoods(TbGoodsInfo goodsInfo);

}

