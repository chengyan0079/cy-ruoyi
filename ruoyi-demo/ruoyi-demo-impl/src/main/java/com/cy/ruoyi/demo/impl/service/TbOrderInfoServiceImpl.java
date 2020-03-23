package com.cy.ruoyi.demo.impl.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.core.util.page.Query;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import com.cy.ruoyi.demo.api.entity.TbOrderInfo;
import com.cy.ruoyi.demo.api.mapper.TbOrderInfoMapper;
import com.cy.ruoyi.demo.api.service.ITbOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.ITbOrderInfoService.version}")
public class TbOrderInfoServiceImpl extends ServiceImpl<TbOrderInfoMapper, TbOrderInfo> implements ITbOrderInfoService {

    @Autowired
    private TbOrderInfoMapper orderInfoMapper;

    @Override
    public PageUtils selectOrderList(PageDomain pageDomain, TbOrderInfo orderInfo)
    {
        if (RegexUtil.isNull(orderInfo)) {
            orderInfo = new TbOrderInfo();
        }
        IPage<TbOrderInfo> page = orderInfoMapper.selectOrderList(new Query<TbOrderInfo>(pageDomain).getPage(), orderInfo);
        return new PageUtils(page);
    }

}
