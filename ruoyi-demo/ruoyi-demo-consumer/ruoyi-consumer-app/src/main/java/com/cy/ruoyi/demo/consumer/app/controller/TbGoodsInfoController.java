package com.cy.ruoyi.demo.consumer.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.sql.page.PageDomain;
import com.cy.ruoyi.common.sql.page.PageUtils;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.demo.consumer.api.service.ITbGoodsInfoService;
import com.cy.ruoyi.demo.consumer.app.PO.GoodsInfoPO;
import com.cy.ruoyi.demo.consumer.app.convert.GoodsInfoAppConvert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.dromara.soul.client.common.annotation.SoulClient;
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
    @PostMapping("list")
    @ApiOperation(value = "分页查询商品列表")
    @SentinelResource("list")
    @SoulClient(path = "/goods/list", desc = "分页查询商品列表")
    public R list(@RequestBody GoodsInfoPO goodsInfo)
    {
        PageDomain pageDomain = getPageInfo();
        log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
        PageUtils page = goodsInfoService.selectGoodsList(pageDomain, GoodsInfoAppConvert.INSTANCE.converPO2DTO(goodsInfo));
        return R.ok(page);
    }

    /**
     * 新增保存商品
     */
    @PostMapping("save")
//    @OperLog(title = "商品管理", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增保存商品")
    @SentinelResource("save")
    @SoulClient(path = "/goods/save", desc = "新增保存商品")
    public R addSave(@RequestBody GoodsInfoPO goodsInfo)
    {
        return toAjax(goodsInfoService.insertGoods(GoodsInfoAppConvert.INSTANCE.converPO2DTO(goodsInfo)));
    }

    /**
     * 修改保存商品
     */
//    @OperLog(title = "商品管理", businessType = BusinessType.UPDATE)
    @PutMapping("update")
    @ApiOperation(value = "修改保存商品")
    @SentinelResource("update")
    @SoulClient(path = "/goods/update", desc = "修改保存商品")
    public R editSave(@RequestBody GoodsInfoPO goodsInfo)
    {
        return toAjax(goodsInfoService.updateGoods(GoodsInfoAppConvert.INSTANCE.converPO2DTO(goodsInfo)));
    }

    @GetMapping("quertAll")
    @ApiOperation(value = "查询所有商品")
    @SentinelResource("quertAll")
    @SoulClient(path = "/goods/quertAll", desc = "查询所有商品")
    public R queryListGoods(){
        return R.ok(GoodsInfoAppConvert.INSTANCE.converListBO2VO(goodsInfoService.queryGoodsInfo()));
    }

}
