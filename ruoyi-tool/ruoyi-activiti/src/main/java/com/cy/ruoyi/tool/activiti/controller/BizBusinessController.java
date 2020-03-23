package com.cy.ruoyi.tool.activiti.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.tool.activiti.entity.BizBusiness;
import com.cy.ruoyi.tool.activiti.service.IBizBusinessService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 流程业务 提供者
 */
@RestController
@RequestMapping("business")
@Api(value = "BizBusinessController",description = "流程业务")
public class BizBusinessController extends BaseController
{
    private static final Log log = LogFactory.get();

    @Autowired
    private IBizBusinessService bizBusinessService;

    /**
     * 查询流程业务
     */
    @GetMapping("get/{id}")
    @ApiOperation(value = "查询流程业务")
    @SentinelResource("get/{id}")
    public BizBusiness get(@PathVariable("id") String id)
    {
        return bizBusinessService.getById(id);
    }

    /**
     * 查询流程业务列表
     */
    @GetMapping("list/my")
    @ApiOperation(value = "查询流程业务列表")
    @SentinelResource("list/my")
    public R list(BizBusiness bizBusiness)
    {
        bizBusiness.setUserId(getCurrentUserId());
        bizBusiness.setDelFlag(false);
        return result(bizBusinessService.selectBizBusinessList(bizBusiness));
    }

    /**
     * 新增保存流程业务
     */
    @PostMapping("save")
    @ApiOperation(value = "新增保存流程业务")
    @SentinelResource("save")
    public R addSave(@RequestBody BizBusiness bizBusiness)
    {
        bizBusiness.setUserId(getCurrentUserId());
        return toAjax(bizBusinessService.save(bizBusiness));
    }

    /**
     * 修改保存流程业务
     */
    @PostMapping("update")
    @ApiOperation(value = "修改保存流程业务")
    @SentinelResource("update")
    public R editSave(@RequestBody BizBusiness bizBusiness)
    {
        return toAjax(bizBusinessService.saveOrUpdate(bizBusiness));
    }

    /**
     * 删除流程业务
     */
    @PostMapping("remove")
    @ApiOperation(value = "删除流程业务,逻辑删除")
    @SentinelResource("remove")
    public R remove(String ids)
    {
        List<BizBusiness> list = (List)Lists.newArrayList(ids.split(","), new BizBusiness().setDelFlag(true));
        return toAjax(bizBusinessService.updateBatchById(list));
    }

}
