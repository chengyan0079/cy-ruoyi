package com.cy.ruoyi.demo.consumer.impl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.core.util.page.Query;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import com.cy.ruoyi.demo.consumer.api.entity.TbGoodsInfo;
import com.cy.ruoyi.demo.consumer.api.mapper.TbGoodsInfoMapper;
import com.cy.ruoyi.demo.consumer.api.service.ITbGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.ITbGoodsInfoService.version}")
public class TbGoodsInfoServiceImpl extends ServiceImpl<TbGoodsInfoMapper, TbGoodsInfo> implements ITbGoodsInfoService {

    @Autowired
    private TbGoodsInfoMapper goodsInfoMapper;

    @Override
    public PageUtils selectGoodsList(PageDomain pageDomain, TbGoodsInfo goodsInfo)
    {
        if (RegexUtil.isNull(goodsInfo)) {
            goodsInfo = new TbGoodsInfo();
        }
        IPage<TbGoodsInfo> page = goodsInfoMapper.selectGoodsList(new Query<TbGoodsInfo>(pageDomain).getPage(), goodsInfo);
        return new PageUtils(page);
    }

    @Override
    public int insertGoods(TbGoodsInfo goodsInfo) {
        return goodsInfoMapper.insertGoods(goodsInfo);
    }

    @Override
    public int updateGoods(TbGoodsInfo goodsInfo) {
        return goodsInfoMapper.updateGoods(goodsInfo);
    }

}
