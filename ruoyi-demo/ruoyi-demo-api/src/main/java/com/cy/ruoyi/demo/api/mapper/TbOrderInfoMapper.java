package com.cy.ruoyi.demo.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.ruoyi.demo.api.entity.TbOrderInfo;
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
     * 查询订单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    List<TbOrderInfo> selectOrderList(@Param("order") TbOrderInfo menu);

    /**
     * 根据条件分页查询订单列表
     * @param page
     * @param menu
     * @return
     */
    IPage<TbOrderInfo> selectOrderList(Page page, @Param("order") TbOrderInfo menu);
}
