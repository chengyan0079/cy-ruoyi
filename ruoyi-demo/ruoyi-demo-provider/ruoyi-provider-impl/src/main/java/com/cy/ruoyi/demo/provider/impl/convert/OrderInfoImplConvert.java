package com.cy.ruoyi.demo.provider.impl.convert;

import com.cy.ruoyi.demo.provider.api.BO.OrderInfoBO;
import com.cy.ruoyi.demo.provider.api.DTO.OrderInfoDTO;
import com.cy.ruoyi.demo.provider.impl.DO.TbOrderInfoDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderInfoImplConvert {
    OrderInfoImplConvert INSTANCE = Mappers.getMapper(OrderInfoImplConvert.class);

    @Mappings({})
    TbOrderInfoDO converDTO2DO(OrderInfoDTO OrderInfoDTO);

    @Mappings({})
    List<OrderInfoBO> converListDO2BO(List<TbOrderInfoDO> doList);

}
