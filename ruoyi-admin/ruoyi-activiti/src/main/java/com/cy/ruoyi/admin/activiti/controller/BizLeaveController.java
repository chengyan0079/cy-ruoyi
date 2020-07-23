package com.cy.ruoyi.admin.activiti.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.admin.activiti.DTO.SysUserDTO;
import com.cy.ruoyi.admin.activiti.consts.ActivitiConstant;
import com.cy.ruoyi.admin.activiti.entity.BizBusiness;
import com.cy.ruoyi.admin.activiti.entity.BizLeave;
import com.cy.ruoyi.admin.activiti.feign.RemoteUserService;
import com.cy.ruoyi.admin.activiti.service.IBizBusinessService;
import com.cy.ruoyi.admin.activiti.service.IBizLeaveService;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.sql.page.PageDomain;
import com.cy.ruoyi.common.sql.page.PageUtils;
import com.cy.ruoyi.common.utils.enums.TradeErrorEnum;
import com.cy.ruoyi.common.utils.util.R;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * 请假 提供者
 */
@RestController
@RequestMapping("leave")
@Api(value = "BizLeaveController",description = "请假")
public class BizLeaveController extends BaseController
{
    private static final Log log = LogFactory.get();

    @Autowired
    private IBizLeaveService leaveService;

    @Autowired
    private IBizBusinessService bizBusinessService;

    @Autowired
    private RemoteUserService remoteUserService;

    /**
     * 查询${tableComment}
     */
    @GetMapping("get/{id}")
    @ApiOperation(value = "查询id")
    @SentinelResource("get/{id}")
    public BizLeave get(@PathVariable("id") String id)
    {
        return leaveService.selectBizLeaveById(id);
    }

    @GetMapping("biz/{businessKey}")
    @ApiOperation(value = "biz/{businessKey}")
    @SentinelResource("biz/{businessKey}")
    public R biz(@PathVariable("businessKey") String businessKey)
    {
        BizBusiness business = bizBusinessService.getById(businessKey);
        if (null != business)
        {
            BizLeave leave = leaveService.selectBizLeaveById(business.getTableId());
            return R.ok(leave);
        }
        return R.error(TradeErrorEnum.ACTIVITI_NO_RECORD);
    }

    /**
     * 查询请假列表
     */
    @GetMapping("list")
    @ApiOperation(value = "查询请假列表")
    @SentinelResource("list")
    public R list(BizLeave leave)
    {
        PageDomain pageDomain = getPageInfo();
        log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
        PageUtils page = leaveService.selectBizLeaveList(pageDomain, leave);
        return R.ok(page);
    }

    /**
     * 新增保存请假
     */
    @PostMapping("save")
    @ApiOperation(value = "新增保存请假")
    @SentinelResource("save")
    public R addSave(@RequestBody BizLeave leave)
    {
        int index = leaveService.insertBizLeave(leave);
        if (index == 1)
        {
            BizBusiness business = initBusiness(leave);
            bizBusinessService.save(business);
            Map<String, Object> variables = Maps.newHashMap();
            // 这里可以设置各个负责人，key跟模型的代理变量一致
            // variables.put("pm", 1l);
            // variables.put("sup", 1l);
            // variables.put("gm", 1l);
            // variables.put("hr", 1l);
            variables.put("duration", leave.getDuration());
            bizBusinessService.startProcess(business, variables);
        }
        return toAjax(index);
    }

    /**
     * 
     * @param leave
     * @return
     */
    private BizBusiness initBusiness(BizLeave leave)
    {
        BizBusiness business = new BizBusiness();
        business.setTableId(leave.getId().toString());
        business.setProcDefId(leave.getProcDefId());
        business.setTitle(leave.getTitle());
        business.setProcName(leave.getProcName());
        long userId = getCurrentUserId();
        business.setUserId(userId);
        SysUserDTO user = remoteUserService.selectSysUserByUserId(userId);
        business.setApplyer(user.getUserName());
        business.setStatus(ActivitiConstant.STATUS_DEALING);
        business.setResult(ActivitiConstant.RESULT_DEALING);
        business.setApplyTime(new Date());
        return business;
    }

    /**
     * 修改保存请假
     */
    @PostMapping("update")
    @ApiOperation(value = "修改保存请假")
    @SentinelResource("update")
    public R editSave(@RequestBody BizLeave leave)
    {
        return toAjax(leaveService.updateBizLeave(leave));
    }

    /**
     * 删除
     */
    @PostMapping("remove")
    @ApiOperation(value = "删除请假")
    @SentinelResource("remove")
    public R remove(String ids)
    {
        return toAjax(leaveService.deleteBizLeaveByIds(ids));
    }
}
