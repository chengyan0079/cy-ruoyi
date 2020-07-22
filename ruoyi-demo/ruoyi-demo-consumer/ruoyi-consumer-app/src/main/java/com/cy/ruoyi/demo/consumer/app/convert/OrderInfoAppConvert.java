package com.cy.ruoyi.demo.consumer.app.convert;

import com.cy.ruoyi.demo.consumer.app.PO.OrderInfoPO;
import com.cy.ruoyi.demo.consumer.app.VO.OrderInfoVO;
import com.cy.ruoyi.demo.provider.api.BO.OrderInfoBO;
import com.cy.ruoyi.demo.provider.api.DTO.OrderInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderInfoAppConvert {
    OrderInfoAppConvert INSTANCE = Mappers.getMapper(OrderInfoAppConvert.class);

    @Mappings({})
    OrderInfoDTO converPO2DTO(OrderInfoPO orderInfoPO);

    @Mappings({})
    List<OrderInfoVO> converListBO2VO(List<OrderInfoBO> orderInfoBO);

}
