package com.cy.ruoyi.demo.consumer.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.ruoyi.demo.consumer.api.entity.TbGoodsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品信息表
 * 
 */
@Mapper
public interface TbGoodsInfoMapper extends BaseMapper<TbGoodsInfo> {

    /**
     * 根据条件分页查询商品列表
     */
    IPage<TbGoodsInfo> selectGoodsList(Page page, @Param("goods") TbGoodsInfo goodsInfo);

    /**
     * 新增商品信息
     */
    int insertGoods(TbGoodsInfo goodsInfo);

    /**
     * 修改商品信息
     */
    int updateGoods(TbGoodsInfo goodsInfo);

}
