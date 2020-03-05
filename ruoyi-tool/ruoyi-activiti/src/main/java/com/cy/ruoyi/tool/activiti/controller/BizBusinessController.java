package com.cy.ruoyi.tool.activiti.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.tool.activiti.entity.BizBusiness;
import com.cy.ruoyi.tool.activiti.service.IBizBusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

/**
 * 流程业务 提供者
 */
@RestController
@RequestMapping("business")
@Api(value = "BizBusinessController",description = "流程业务")
public class BizBusinessController extends BaseController
{
    private static final Log log = LogFactory.get();

    @Reference(validation = "true", version = "${dubbo.provider.IBizBusinessService.version}")
    private IBizBusinessService bizBusinessService;

    /**
     * 查询流程业务
     */
    @GetMapping("get/{id}")
    @ApiOperation(value = "查询流程业务")
    public BizBusiness get(@PathVariable("id") String id)
    {
        return bizBusinessService.getById(id);
    }

    /**
     * 查询流程业务列表
     */
    @GetMapping("list/my")
    @ApiOperation(value = "查询流程业务列表")
    public R list(BizBusiness bizBusiness)
    {
        bizBusiness.setUserId(getCurrentUserId());
        bizBusiness.setDelFlag(false);
//        return result(bizBusinessService.selectBizBusinessList(bizBusiness));
        return null;
    }

    /**
     * 新增保存流程业务
     */
    @PostMapping("save")
    @ApiOperation(value = "新增保存流程业务")
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
    public R editSave(@RequestBody BizBusiness bizBusiness)
    {
        return toAjax(bizBusinessService.saveOrUpdate(bizBusiness));
    }

    /**
     * 删除流程业务
     */
    @PostMapping("remove")
    @ApiOperation(value = "删除流程业务")
    public R remove(String ids)
    {
//        return toAjax(bizBusinessService.deleteBizBusinessLogic(ids));
        return R.ok();
    }

}
