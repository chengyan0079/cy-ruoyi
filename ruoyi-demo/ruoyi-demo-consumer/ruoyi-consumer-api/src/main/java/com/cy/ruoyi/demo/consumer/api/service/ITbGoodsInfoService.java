package com.cy.ruoyi.demo.consumer.api.service;

import com.cy.ruoyi.common.sql.page.PageDomain;
import com.cy.ruoyi.common.sql.page.PageUtils;
import com.cy.ruoyi.demo.consumer.api.BO.GoodsInfoBO;
import com.cy.ruoyi.demo.consumer.api.DTO.GoodsInfoDTO;

import java.util.List;

/**
 * 商品信息表
 */
public interface ITbGoodsInfoService {

    /**
     * 根据条件分页查询商品列表
     */
    PageUtils selectGoodsList(PageDomain pageDomain, GoodsInfoDTO goodsInfo);

    /**
     * 新增保存商品信息
     */
    int insertGoods(GoodsInfoDTO goodsInfo);

    /**
     * 修改保存商品信息
     */
    int updateGoods(GoodsInfoDTO goodsInfo);

    /**
     * 查询所有商品
     */
    List<GoodsInfoBO> queryGoodsInfo(GoodsInfoDTO goodsInfo);

}

