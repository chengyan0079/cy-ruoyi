package com.cy.ruoyi.demo.consumer.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.demo.consumer.api.service.ITbGoodsInfoService;
import com.cy.ruoyi.demo.consumer.app.PO.OrderInfoPO;
import com.cy.ruoyi.demo.consumer.app.convert.OrderInfoAppConvert;
import com.cy.ruoyi.demo.provider.api.service.ITbOrderInfoService;
import com.cy.ruoyi.demo.provider.api.service.ITestProviderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.dromara.soul.client.common.annotation.SoulClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 *  测试Controller
 */
@RestController
@RequestMapping("/conTest")
@Api(value = "conTest",description = "Demo测试")
public class TestController extends BaseController {

    private static final Log log = LogFactory.get();


    @Reference(validation = "true", version = "${dubbo.consumer.ITestProviderService.version}")
    ITestProviderService testProviderService;

    @Reference(validation = "true", version = "${dubbo.provider.ITbGoodsInfoService.version}")
    ITbGoodsInfoService tbGoodsInfoService;

    @Reference(validation = "true", version = "${dubbo.consumer.ITbOrderInfoService.version}")
    ITbOrderInfoService tbOrderInfoService;

    @PostMapping("/echo/{msg}")
    @ApiOperation(value = "测试msg")
    @SoulClient(path = "/conTest/echo/**", desc = "测试msg")
    @SentinelResource("/echo/{msg}")
    public String echo(@PathVariable("msg") String msg){
        return "conTest === " + msg;
    }

    @PostMapping("/echo2")
    @SoulClient(path = "/conTest/echo2", desc = "测试msg2")
    @ApiOperation(value = "测试msg2")
    @SentinelResource("/echo2")
    public String echo2(@RequestParam("msg") String msg){
        return "conTest2 === " + msg;
    }

    @PostMapping("/testMsg/{msg}")
    @ApiOperation(value = "testConsumerMsg")
    @SoulClient(path = "/conTest/testMsg/**", desc = "testConsumerMsg")
    @SentinelResource("/testMsg/{msg}")
    public String testConsumerMsg(@PathVariable("msg") String msg){
        return testProviderService.testProviderMsg(msg);
    }


//    @PostMapping("testSeata")
//    @ApiOperation(value = "测试Seata,添加订单和商品")
//    @SoulClient(path = "/conTest/testSeata", desc = "测试Seata,添加订单和商品")
//    @SentinelResource("/testSeata")
//    public R testSeata(@RequestBody TbGoodsInfo goodInfo){
//        if(RegexUtil.isNull(goodInfo)){
//            return R.error();
//        }
//        tbGoodsInfoService.insertGoods(goodInfo);
//        TbOrderInfo orderInfo = new TbOrderInfo();
//        orderInfo.setPayTime("2020-03-21 12:11:24");
//        orderInfo.setOrderNo("12ddfs123");
//        orderInfo.setPayAmt(new BigDecimal(80.2));
//        orderInfo.setPayDate("2020-04-22");
//        orderInfo.setPayOrderNo("443sss31121212");
//        orderInfo.setPayStatus("1");
//        orderInfo.setUserId(1);
//        orderInfo.setCreateBy("cy");
//        orderInfo.setRemark("ioioss");
//        int result = tbOrderInfoService.insertOrder(orderInfo);
//        return R.ok(result);
//    }

    @GetMapping("queryOrder")
    @ApiOperation(value = "查询订单")
//    @SoulClient(path = "/conTest/testSeata", desc = "测试Seata,添加订单和商品")
//    @SentinelResource("/testSeata")
    public R queryOrder(OrderInfoPO orderInfoPO){
        return R.ok(OrderInfoAppConvert.INSTANCE.converListBO2VO(tbOrderInfoService.queryOrderInfo(OrderInfoAppConvert.INSTANCE.converPO2DTO(orderInfoPO))));
    }

}
