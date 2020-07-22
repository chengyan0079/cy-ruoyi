package com.cy.ruoyi.demo.consumer.app.convert;

import com.cy.ruoyi.demo.consumer.api.BO.GoodsInfoBO;
import com.cy.ruoyi.demo.consumer.api.DTO.GoodsInfoDTO;
import com.cy.ruoyi.demo.consumer.app.PO.GoodsInfoPO;
import com.cy.ruoyi.demo.consumer.app.VO.GoodsInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GoodsInfoAppConvert {
    GoodsInfoAppConvert INSTANCE = Mappers.getMapper(GoodsInfoAppConvert.class);

    @Mappings({})
    GoodsInfoDTO converPO2DTO(GoodsInfoPO goodsInfoPO);

    @Mappings({})
    List<GoodsInfoVO> converListBO2VO(List<GoodsInfoBO> goodsInfoBO);

}
