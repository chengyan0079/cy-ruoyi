package com.cy.ruoyi.demo.provider.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.ruoyi.demo.provider.impl.DO.TbOrderInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单信息表
 * 
 */
@Mapper
public interface TbOrderInfoMapper extends BaseMapper<TbOrderInfoDO> {

    /**
     * 根据条件分页查询订单列表
     */
    IPage<TbOrderInfoDO> selectOrderList(Page page, @Param("order") TbOrderInfoDO orderInfo);

    /**
     * 新增订单信息
     */
    int insertOrder(TbOrderInfoDO orderInfo);

    /**
     * 修改订单信息
     */
    int updateOrder(TbOrderInfoDO orderInfo);

    List<TbOrderInfoDO> selectOrderList(@Param("order") TbOrderInfoDO orderInfo);

}
