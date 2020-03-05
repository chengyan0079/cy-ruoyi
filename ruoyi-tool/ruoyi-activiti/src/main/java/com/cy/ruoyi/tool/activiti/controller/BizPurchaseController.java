package com.cy.ruoyi.tool.activiti.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.tool.activiti.consts.ActivitiConstant;
import com.cy.ruoyi.tool.activiti.entity.BizBusiness;
import com.cy.ruoyi.tool.activiti.entity.BizPurchase;
import com.cy.ruoyi.tool.activiti.service.IBizBusinessService;
import com.cy.ruoyi.tool.activiti.service.IBizPurchaseService;
import com.cy.ruoyi.user.api.entity.SysUser;
import com.cy.ruoyi.user.api.feign.RemoteUserService;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * 报销 提供者
 */
@RestController
@RequestMapping("purchase")
@Api(value = "BizPurchaseController",description = "报销")
public class BizPurchaseController extends BaseController
{
    private static final Log log = LogFactory.get();

    @Reference(validation = "true", version = "${dubbo.provider.IBizPurchaseService.version}")
    private IBizPurchaseService purchaseService;

    @Reference(validation = "true", version = "${dubbo.provider.IBizBusinessService.version}")
    private IBizBusinessService bizBusinessService;

    @Autowired
    private RemoteUserService remoteUserService;

    /**
     * 查询报销
     */
    @GetMapping("get/{id}")
    @ApiOperation(value = "查询报销")
    public R get(@PathVariable("id") String id)
    {
        return R.ok(purchaseService.getById(id));
    }

    /**
     * 根据业务key获取数据
     */
    @GetMapping("biz/{businessKey}")
    @ApiOperation(value = "根据业务key获取数据")
    public R biz(@PathVariable("businessKey") String businessKey)
    {
        BizBusiness business = bizBusinessService.getById(businessKey);
        if (null != business)
        {
            BizPurchase purchase = purchaseService.getById(business.getTableId());
            return R.ok(purchase);
        }
        return R.error("no record");
    }

    /**
     * 新增保存报销
     */
    @PostMapping("save")
    @ApiOperation(value = "新增保存报销")
    public R addSave(@RequestBody BizPurchase purchase)
    {
        boolean index = purchaseService.save(purchase);
        if (index){
            BizBusiness business = initBusiness(purchase);
            bizBusinessService.save(business);
            Map<String, Object> variables = Maps.newHashMap();
            // 这里可以设置各个负责人，key跟模型的代理变量一致
            // variables.put("fm", 1l);
            // variables.put("fc", 1l);
            // variables.put("gm", 1l);
            variables.put("money", purchase.getMoney());
            bizBusinessService.startProcess(business, variables);
        }
        return toAjax(index);
    }

    /**
     * biz构造业务信息
     */
    private BizBusiness initBusiness(BizPurchase purchase)
    {
        BizBusiness business = new BizBusiness();
        business.setTableId(purchase.getId().toString());
        business.setProcDefId(purchase.getProcDefId());
        business.setTitle(purchase.getTitle());
        business.setProcName(purchase.getProcName());
        long userId = getCurrentUserId();
        business.setUserId(userId);
//        SysUser user = remoteUserService.selectSysUserByUserId(userId);
//        business.setApplyer(user.getUserName());
        business.setStatus(ActivitiConstant.STATUS_DEALING);
        business.setResult(ActivitiConstant.RESULT_DEALING);
        business.setApplyTime(new Date());
        return business;
    }
}
