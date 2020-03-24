package com.cy.ruoyi.demo.consumer.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.log.annotation.OperLog;
import com.cy.ruoyi.common.log.enums.BusinessType;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.demo.consumer.api.entity.TbGoodsInfo;
import com.cy.ruoyi.demo.consumer.api.service.ITbGoodsInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("goods")
@Api(value = "TbGoodsInfoController",description = "商品")
public class TbGoodsInfoController extends BaseController
{
    private static final Log log = LogFactory.get();

    @Reference(validation = "true", version = "${dubbo.provider.ITbGoodsInfoService.version}")
    private ITbGoodsInfoService goodsInfoService;

    /**
     * 分页查询商品列表
     */
    @GetMapping("list")
    @ApiOperation(value = "分页查询商品列表")
    @SentinelResource("list")
    public R list(TbGoodsInfo goodsInfo)
    {
        PageDomain pageDomain = getPageInfo();
        log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
        PageUtils page = goodsInfoService.selectGoodsList(pageDomain, goodsInfo);
        return R.ok(page);
    }

    /**
     * 新增保存商品
     */
    @PostMapping("save")
//    @OperLog(title = "商品管理", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增保存商品")
    @SentinelResource("save")
    public R addSave(@RequestBody TbGoodsInfo goodsInfo)
    {
        return toAjax(goodsInfoService.insertGoods(goodsInfo));
    }

    /**
     * 修改保存商品
     */
//    @OperLog(title = "商品管理", businessType = BusinessType.UPDATE)
    @PostMapping("update")
    @ApiOperation(value = "修改保存商品")
    @SentinelResource("update")
    public R editSave(@RequestBody TbGoodsInfo goodsInfo)
    {
        return toAjax(goodsInfoService.updateGoods(goodsInfo));
    }

}
