package com.cy.ruoyi.demo.consumer.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.ruoyi.demo.consumer.impl.DO.TbGoodsInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品信息表
 * 
 */
@Mapper
public interface TbGoodsInfoMapper extends BaseMapper<TbGoodsInfoDO> {

    /**
     * 根据条件分页查询商品列表
     */
    IPage<TbGoodsInfoDO> selectGoodsList(Page page, @Param("goods") TbGoodsInfoDO goodsInfo);

    /**
     * 新增商品信息
     */
    int insertGoods(TbGoodsInfoDO goodsInfo);

    /**
     * 修改商品信息
     */
    int updateGoods(TbGoodsInfoDO goodsInfo);

    List<TbGoodsInfoDO> selectGoodsList(@Param("goods") TbGoodsInfoDO goodsInfo);

}
