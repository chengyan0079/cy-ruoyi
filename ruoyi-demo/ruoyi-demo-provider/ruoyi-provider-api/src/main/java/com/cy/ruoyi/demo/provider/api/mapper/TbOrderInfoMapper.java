package com.cy.ruoyi.demo.provider.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.ruoyi.demo.provider.api.entity.TbOrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单信息表
 * 
 */
@Mapper
public interface TbOrderInfoMapper extends BaseMapper<TbOrderInfo> {

    /**
     * 根据条件分页查询订单列表
     */
    IPage<TbOrderInfo> selectOrderList(Page page, @Param("order") TbOrderInfo orderInfo);

    /**
     * 新增订单信息
     */
    int insertOrder(TbOrderInfo orderInfo);

    /**
     * 修改订单信息
     */
    int updateOrder(TbOrderInfo orderInfo);


}
