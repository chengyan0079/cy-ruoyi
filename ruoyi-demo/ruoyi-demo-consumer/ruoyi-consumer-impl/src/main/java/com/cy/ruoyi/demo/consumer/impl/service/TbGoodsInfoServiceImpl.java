package com.cy.ruoyi.demo.consumer.impl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.ruoyi.common.sql.page.PageDomain;
import com.cy.ruoyi.common.sql.page.PageUtils;
import com.cy.ruoyi.common.sql.page.Query;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import com.cy.ruoyi.demo.consumer.api.BO.GoodsInfoBO;
import com.cy.ruoyi.demo.consumer.api.DTO.GoodsInfoDTO;
import com.cy.ruoyi.demo.consumer.api.service.ITbGoodsInfoService;
import com.cy.ruoyi.demo.consumer.impl.DO.TbGoodsInfoDO;
import com.cy.ruoyi.demo.consumer.impl.convert.GoodsInfoImplConvert;
import com.cy.ruoyi.demo.consumer.impl.mapper.TbGoodsInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.ITbGoodsInfoService.version}")
public class TbGoodsInfoServiceImpl implements ITbGoodsInfoService {

    @Autowired
    private TbGoodsInfoMapper goodsInfoMapper;

    @Override
    public PageUtils selectGoodsList(PageDomain pageDomain, GoodsInfoDTO goodsInfo)
    {
        if (RegexUtil.isNull(goodsInfo)) {
            goodsInfo = new GoodsInfoDTO();
        }
        IPage<TbGoodsInfoDO> page = goodsInfoMapper.selectGoodsList(new Query<TbGoodsInfoDO>(pageDomain).getPage(), GoodsInfoImplConvert.INSTANCE.converDTO2DO(goodsInfo));
        return new PageUtils(page);
    }

    @Override
    public int insertGoods(GoodsInfoDTO goodsInfo) {
        return goodsInfoMapper.insertGoods(GoodsInfoImplConvert.INSTANCE.converDTO2DO(goodsInfo));
    }

    @Override
    public int updateGoods(GoodsInfoDTO goodsInfo) {
        return goodsInfoMapper.updateGoods(GoodsInfoImplConvert.INSTANCE.converDTO2DO(goodsInfo));
    }

    @Override
    public List<GoodsInfoBO> queryGoodsInfo(GoodsInfoDTO goodsInfo) {
        return GoodsInfoImplConvert.INSTANCE.converListDO2BO(goodsInfoMapper.selectGoodsList(GoodsInfoImplConvert.INSTANCE.converDTO2DO(goodsInfo)));
    }

}
