package com.cy.ruoyi.demo.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.common.auth.annotation.HasPermissions;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.log.annotation.OperLog;
import com.cy.ruoyi.common.log.enums.BusinessType;
import com.cy.ruoyi.common.utils.annotation.LoginUser;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.demo.api.entity.TbOrderInfo;
import com.cy.ruoyi.demo.api.service.ITbOrderInfoService;
import com.cy.ruoyi.user.api.entity.SysMenu;
import com.cy.ruoyi.user.api.entity.SysUser;
import com.cy.ruoyi.user.api.service.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 菜单权限 
 */
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
    @OperLog(title = "订单管理", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增保存订单")
    @SentinelResource("save")
    public R addSave(@RequestBody TbOrderInfo orderInfo)
    {
        return toAjax(orderInfoService.save(orderInfo));
    }

    /**
     * 修改保存订单
     */
    @OperLog(title = "订单管理", businessType = BusinessType.UPDATE)
    @PostMapping("update")
    @ApiOperation(value = "修改保存订单")
    @SentinelResource("update")
    public R editSave(@RequestBody TbOrderInfo orderInfo)
    {
        return toAjax(orderInfoService.updateById(orderInfo));
    }

}