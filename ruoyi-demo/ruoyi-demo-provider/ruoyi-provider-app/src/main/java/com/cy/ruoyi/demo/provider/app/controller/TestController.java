package com.cy.ruoyi.demo.provider.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import com.cy.ruoyi.demo.consumer.api.entity.TbGoodsInfo;
import com.cy.ruoyi.demo.consumer.api.service.ITbGoodsInfoService;
import com.cy.ruoyi.demo.consumer.api.service.ITestConsumerService;
import com.cy.ruoyi.demo.provider.api.entity.TbOrderInfo;
import com.cy.ruoyi.demo.provider.api.service.ITbOrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 *  测试Controller
 */
@RestController
@RequestMapping("/proTest")
@Api(value = "proTest",description = "Demo测试")
public class TestController extends BaseController {

    private static final Log log = LogFactory.get();

    @Reference(validation = "true", version = "${dubbo.consumer.ITestConsumerService.version}")
    ITestConsumerService testConsumerService;

    @Reference(validation = "true", version = "${dubbo.consumer.ITbGoodsInfoService.version}")
    ITbGoodsInfoService tbGoodsInfoService;

    @Reference(validation = "true", version = "${dubbo.provider.ITbOrderInfoService.version}")
    ITbOrderInfoService tbOrderInfoService;

    @PostMapping("/echo/{msg}")
    @ApiOperation(value = "测试msg")
//    @SentinelResource("/echo/{msg}")
    public String echo(@PathVariable String msg){
        return "proTest ===" + msg;
    }


    @PostMapping("/testMsg/{msg}")
    @ApiOperation(value = "testConsumerMsg")
//    @SentinelResource("/testMsg/{msg}")
    public String testConsumerMsg(@PathVariable String msg){
        return testConsumerService.testConsumerMsg(msg);
    }


    @PostMapping("testSeata")
    @ApiOperation(value = "测试Seata,添加订单和商品")
    public R testSeata(@RequestBody TbOrderInfo orderInfo){
        if(RegexUtil.isNull(orderInfo)){
            return R.error();
        }
        tbOrderInfoService.insertOrder(orderInfo);

        TbGoodsInfo goodInfo = new TbGoodsInfo();
        goodInfo.setBuyingPrice(new BigDecimal(1.22));
        goodInfo.setGoodsCount(23);
        goodInfo.setGoodsName("seate test");
        goodInfo.setGoodsNo("232323323sss");
        goodInfo.setGoodsType("1");
        goodInfo.setSellingPrice(new BigDecimal(40.1));
        goodInfo.setStatus("0");
        goodInfo.setCreateBy("cy test");
        goodInfo.setRemark("ddd");
        int result = tbGoodsInfoService.insertGoods(goodInfo);
        return R.ok(result);
    }
}
