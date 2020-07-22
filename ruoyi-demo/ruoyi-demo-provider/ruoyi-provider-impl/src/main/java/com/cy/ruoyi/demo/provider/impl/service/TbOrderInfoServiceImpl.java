package com.cy.ruoyi.demo.provider.impl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.core.util.page.Query;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import com.cy.ruoyi.demo.provider.api.BO.OrderInfoBO;
import com.cy.ruoyi.demo.provider.api.DTO.OrderInfoDTO;
import com.cy.ruoyi.demo.provider.api.service.ITbOrderInfoService;
import com.cy.ruoyi.demo.provider.impl.DO.TbOrderInfoDO;
import com.cy.ruoyi.demo.provider.impl.convert.OrderInfoImplConvert;
import com.cy.ruoyi.demo.provider.impl.mapper.TbOrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.ITbOrderInfoService.version}")
public class TbOrderInfoServiceImpl implements ITbOrderInfoService {

    @Autowired
    private TbOrderInfoMapper orderInfoMapper;

    @Override
    public PageUtils selectOrderList(PageDomain pageDomain, OrderInfoDTO orderInfo)
    {
        if (RegexUtil.isNull(orderInfo)) {
            orderInfo = new OrderInfoDTO();
        }
        IPage<TbOrderInfoDO> page = orderInfoMapper.selectOrderList(new Query<TbOrderInfoDO>(pageDomain).getPage(), OrderInfoImplConvert.INSTANCE.converDTO2DO(orderInfo));
        return new PageUtils(page);
    }

    @Override
    public int insertOrder(OrderInfoDTO orderInfo) {
        return orderInfoMapper.insertOrder(OrderInfoImplConvert.INSTANCE.converDTO2DO(orderInfo));
    }

    @Override
    public int updateOrder(OrderInfoDTO orderInfo) {
        return orderInfoMapper.updateOrder(OrderInfoImplConvert.INSTANCE.converDTO2DO(orderInfo));
    }

    @Override
    public List<OrderInfoBO> queryOrderInfo(OrderInfoDTO orderInfo) {
        return OrderInfoImplConvert.INSTANCE.converListDO2BO(orderInfoMapper.selectOrderList(OrderInfoImplConvert.INSTANCE.converDTO2DO(orderInfo)));
    }

}
