package com.cy.ruoyi.demo.provider.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.demo.provider.api.entity.TbOrderInfo;
import com.cy.ruoyi.demo.provider.api.service.ITbOrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.dromara.soul.client.common.annotation.SoulClient;
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
    @SoulClient(path = "/order/list", desc = "分页查询订单列表")
    @SentinelResource("list")
    public R list(TbOrderInfo orderInfo)
    {
        PageDomain pageDomain = getPageInfo();
        log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
        PageUtils page = orderInfoService.selectOrderList(pageDomain, orderInfo);
        return R.ok(page);
    }

    /**
     * 新增保存订单
     */
    @PostMapping("save")
    @ApiOperation(value = "新增保存订单")
    @SoulClient(path = "/order/save", desc = "新增保存订单")
    @SentinelResource("save")
    public R addSave(@RequestBody TbOrderInfo orderInfo)
    {
        return toAjax(orderInfoService.insertOrder(orderInfo));
    }

    /**
     * 修改保存订单
     */
//    @OperLog(title = "订单管理", businessType = BusinessType.UPDATE)
    @PostMapping("update")
    @ApiOperation(value = "修改保存订单")
    @SoulClient(path = "/order/update", desc = "修改保存订单")
    @SentinelResource("update")
    public R editSave(@RequestBody TbOrderInfo orderInfo)
    {
        return toAjax(orderInfoService.updateOrder(orderInfo));
    }

}
