package com.cy.ruoyi.tool.activiti.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.tool.activiti.consts.ActivitiConstant;
import com.cy.ruoyi.tool.activiti.entity.BizBusiness;
import com.cy.ruoyi.tool.activiti.entity.BizLeave;
import com.cy.ruoyi.tool.activiti.service.IBizBusinessService;
import com.cy.ruoyi.tool.activiti.service.IBizLeaveService;
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
 * 请假 提供者
 */
@RestController
@RequestMapping("leave")
@Api(value = "BizLeaveController",description = "请假")
public class BizLeaveController extends BaseController
{
    private static final Log log = LogFactory.get();

    @Reference(validation = "true", version = "${dubbo.provider.IBizLeaveService.version}")
    private IBizLeaveService leaveService;

    @Reference(validation = "true", version = "${dubbo.provider.IBizBusinessService.version}")
    private IBizBusinessService bizBusinessService;

    @Autowired
    private RemoteUserService remoteUserService;

    /**
     * 查询${tableComment}
     */
    @GetMapping("get/{id}")
    @ApiOperation(value = "查询id")
    public BizLeave get(@PathVariable("id") String id)
    {
        return leaveService.selectBizLeaveById(id);
    }

    @GetMapping("biz/{businessKey}")
    @ApiOperation(value = "biz/{businessKey}")
    public R biz(@PathVariable("businessKey") String businessKey)
    {
        BizBusiness business = bizBusinessService.getById(businessKey);
        if (null != business)
        {
            BizLeave leave = leaveService.selectBizLeaveById(business.getTableId());
            return R.ok(leave);
        }
        return R.error("no record");
    }

    /**
     * 查询请假列表
     */
    @GetMapping("list")
    @ApiOperation(value = "查询请假列表")
    public R list(BizLeave leave)
    {
//        return result(leaveService.selectBizLeaveList(leave));
        return null;
    }

    /**
     * 新增保存请假
     */
    @PostMapping("save")
    @ApiOperation(value = "新增保存请假")
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
     * @author zmr
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
//        SysUser user = remoteUserService.selectSysUserByUserId(userId);
//        business.setApplyer(user.getUserName());
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
    public R editSave(@RequestBody BizLeave leave)
    {
        return toAjax(leaveService.updateBizLeave(leave));
    }

    /**
     * 删除
     */
    @PostMapping("remove")
    @ApiOperation(value = "删除请假")
    public R remove(String ids)
    {
        return toAjax(leaveService.deleteBizLeaveByIds(ids));
    }
}
