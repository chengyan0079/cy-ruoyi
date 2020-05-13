package com.cy.ruoyi.demo.consumer.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import com.cy.ruoyi.demo.consumer.api.entity.TbGoodsInfo;
import com.cy.ruoyi.demo.consumer.api.service.ITbGoodsInfoService;
import com.cy.ruoyi.demo.provider.api.entity.TbOrderInfo;
import com.cy.ruoyi.demo.provider.api.service.ITbOrderInfoService;
import com.cy.ruoyi.demo.provider.api.service.ITestProviderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
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
//    @SentinelResource("/echo/{msg}")
    public String echo(@PathVariable String msg){
        return "conTest ===" + msg;
    }

    @PostMapping("/testMsg/{msg}")
    @ApiOperation(value = "testConsumerMsg")
//    @SentinelResource("/testMsg/{msg}")
    public String testConsumerMsg(@PathVariable String msg){
        return testProviderService.testProviderMsg(msg);
    }


    @PostMapping("testSeata")
    @ApiOperation(value = "测试Seata,添加订单和商品")
    public R testSeata(@RequestBody TbGoodsInfo goodInfo){
        if(RegexUtil.isNull(goodInfo)){
            return R.error();
        }
        tbGoodsInfoService.insertGoods(goodInfo);
        TbOrderInfo orderInfo = new TbOrderInfo();
        orderInfo.setPayTime("2020-03-21 12:11:24");
        orderInfo.setOrderNo("12ddfs123");
        orderInfo.setPayAmt(new BigDecimal(80.2));
        orderInfo.setPayDate("2020-04-22");
        orderInfo.setPayOrderNo("443sss31121212");
        orderInfo.setPayStatus("1");
        orderInfo.setUserId(1);
        orderInfo.setCreateBy("cy");
        orderInfo.setRemark("ioioss");
        int result = tbOrderInfoService.insertOrder(orderInfo);
        return R.ok(result);
    }

}
