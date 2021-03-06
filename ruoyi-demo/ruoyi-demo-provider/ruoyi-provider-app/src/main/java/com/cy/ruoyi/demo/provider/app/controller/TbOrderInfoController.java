package com.cy.ruoyi.demo.provider.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.sql.page.PageDomain;
import com.cy.ruoyi.common.sql.page.PageUtils;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.demo.provider.api.service.ITbOrderInfoService;
import com.cy.ruoyi.demo.provider.app.PO.OrderInfoPO;
import com.cy.ruoyi.demo.provider.app.convert.OrderInfoAppConvert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
@Api(value = "TbOrderInfoController",description = "订单")
public class TbOrderInfoController extends BaseController
{
    private static final Log log = LogFactory.get();

    @Reference(validation = "true", version = "${dubbo.provider.ITbOrderInfoService.version}")
    private ITbOrderInfoService orderInfoService;

    /**
     * 分页查询订单列表
     */
    @GetMapping("list")
    @ApiOperation(value = "分页查询订单列表")
    @SentinelResource("list")
    public R list(OrderInfoPO orderInfo)
    {
        PageDomain pageDomain = getPageInfo();
        log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
        PageUtils page = orderInfoService.selectOrderList(pageDomain, OrderInfoAppConvert.INSTANCE.converPO2DTO(orderInfo));
        return R.ok(page);
    }

    /**
     * 新增保存订单
     */
    @PostMapping("save")
    @ApiOperation(value = "新增保存订单")
    @SentinelResource("save")
    public R addSave(@RequestBody OrderInfoPO orderInfo)
    {
        return toAjax(orderInfoService.insertOrder(OrderInfoAppConvert.INSTANCE.converPO2DTO(orderInfo)));
    }

    /**
     * 修改保存订单
     */
//    @OperLog(title = "订单管理", businessType = BusinessType.UPDATE)
    @PostMapping("update")
    @ApiOperation(value = "修改保存订单")
    @SentinelResource("update")
    public R editSave(@RequestBody OrderInfoPO orderInfo)
    {
        return toAjax(orderInfoService.updateOrder(OrderInfoAppConvert.INSTANCE.converPO2DTO(orderInfo)));
    }

    @GetMapping("quertAll")
    @ApiOperation(value = "查询所有订单")
    @SentinelResource("quertAll")
    public R queryListOrder(OrderInfoPO orderInfo){
        return R.ok(OrderInfoAppConvert.INSTANCE.converListBO2VO(orderInfoService.queryOrderInfo(OrderInfoAppConvert.INSTANCE.converPO2DTO(orderInfo))));
    }

}
