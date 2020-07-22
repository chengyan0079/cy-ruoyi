package com.cy.ruoyi.demo.consumer.impl.convert;

import com.cy.ruoyi.demo.consumer.api.BO.GoodsInfoBO;
import com.cy.ruoyi.demo.consumer.api.DTO.GoodsInfoDTO;
import com.cy.ruoyi.demo.consumer.impl.DO.TbGoodsInfoDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GoodsInfoImplConvert {
    GoodsInfoImplConvert INSTANCE = Mappers.getMapper(GoodsInfoImplConvert.class);

    @Mappings({})
    TbGoodsInfoDO converDTO2DO(GoodsInfoDTO goodsInfoDTO);

    @Mappings({})
    List<GoodsInfoBO> converListDO2BO(List<TbGoodsInfoDO> doList);

}
