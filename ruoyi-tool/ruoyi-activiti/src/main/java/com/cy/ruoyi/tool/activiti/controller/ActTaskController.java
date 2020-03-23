package com.cy.ruoyi.tool.activiti.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.tool.activiti.DTO.SysUserDTO;
import com.cy.ruoyi.tool.activiti.VO.HiTaskVo;
import com.cy.ruoyi.tool.activiti.VO.RuTask;
import com.cy.ruoyi.tool.activiti.consts.ActivitiConstant;
import com.cy.ruoyi.tool.activiti.entity.BizAudit;
import com.cy.ruoyi.tool.activiti.entity.BizBusiness;
import com.cy.ruoyi.tool.activiti.feign.RemoteUserService;
import com.cy.ruoyi.tool.activiti.service.IBizAuditService;
import com.cy.ruoyi.tool.activiti.service.IBizBusinessService;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 */
@RestController
@RequestMapping("task")
@Api(value = "ActTaskController",description = "")
public class ActTaskController extends BaseController
{
    private static final Log log = LogFactory.get();

    @Autowired
    private TaskService taskService;

    @Autowired
    private IBizAuditService bizAuditService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private IBizBusinessService businessService;

    /**
     * task待办
     * 
     * @return
     * @author zmr
     */
    @RequestMapping(value = "ing")
    @ApiOperation(value = "task待办")
    @SentinelResource("ing")
    public R ing(RuTask ruTask, PageDomain page)
    {
        List<RuTask> list = new ArrayList<>();
        Long userId = getCurrentUserId();
        TaskQuery query = taskService.createTaskQuery().taskCandidateOrAssigned(userId + "").orderByTaskCreateTime()
                .desc();
        if (StrUtil.isNotBlank(ruTask.getProcessDefKey()))
        {
            query.processDefinitionKey(ruTask.getProcessDefKey());
        }
        long count = query.count();
        int first = (page.getPageNum() - 1) * page.getPageSize();
        List<Task> taskList = query.listPage(first, page.getPageSize());
        // 转换vo
        taskList.forEach(e -> {
            RuTask rt = new RuTask(e);
            List<IdentityLink> identityLinks = runtimeService.getIdentityLinksForProcessInstance(rt.getProcInstId());
            for (IdentityLink ik : identityLinks)
            {
                // 关联发起人
                if ("starter".equals(ik.getType()) && StrUtil.isNotBlank(ik.getUserId()))
                {
                    rt.setApplyer(remoteUserService.selectSysUserByUserId(Long.parseLong(ik.getUserId())).getUserName());
                }
            }
            // 关联业务key
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(rt.getProcInstId())
                    .singleResult();
            rt.setBusinessKey(pi.getBusinessKey());
            rt.setProcessName(pi.getName());
            rt.setProcessDefKey(pi.getProcessDefinitionKey());
            rt.setProcessDefName(pi.getProcessDefinitionName());
            list.add(rt);
        });
        Map<String, Object> map = Maps.newHashMap();
        map.put("rows", list);
        map.put("pageNum", page.getPageNum());
        map.put("total", count);
        return R.ok(map);
    }

    /**
     * task 已办
     */
    @RequestMapping(value = "done")
    @ApiOperation(value = "task 已办")
    @SentinelResource("done")
    public R done(HiTaskVo hiTaskVo)
    {
        hiTaskVo.setAuditorId(getCurrentUserId());
        hiTaskVo.setDeleteReason(ActivitiConstant.REASON_COMPLETED);
        return result(bizAuditService.getHistoryTaskList(hiTaskVo));
    }

    /**
     * task 流转历史
     * 
     * @param hiTaskVo
     * @return
     * @author zmr
     */
    @RequestMapping(value = "flow")
    @ApiOperation(value = "task 流转历史")
    @SentinelResource("flow")
    public R flow(HiTaskVo hiTaskVo)
    {
        PageDomain pageDomain = getPageInfo();
        log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
        PageUtils page = bizAuditService.getHistoryTaskList(pageDomain, hiTaskVo);
        return R.ok(page);
    }

    /**
     * 审批
     */
    @PostMapping("audit")
    @ApiOperation(value = "审批")
    @SentinelResource("audit")
    public R audit(@RequestBody BizAudit bizAudit)
    {
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("result", bizAudit.getResult());
        // 审批
        taskService.complete(bizAudit.getTaskId(), variables);
        SysUserDTO user = remoteUserService.selectSysUserByUserId(getCurrentUserId());
        bizAudit.setAuditor(user.getUserName() + "-" + user.getLoginName());
        bizAudit.setAuditorId(user.getUserId());
        bizAuditService.save(bizAudit);
        BizBusiness bizBusiness = new BizBusiness().setId(bizAudit.getBusinessKey())
                .setProcInstId(bizAudit.getProcInstId());
        businessService.setAuditor(bizBusiness, bizAudit.getResult(), getCurrentUserId());
        return R.ok();
    }

    @PostMapping("audit/batch")
    @ApiOperation(value = "批量审核")
    @SentinelResource("audit/batch")
    public R auditBatch(@RequestBody BizAudit bizAudit)
    {
        SysUserDTO user = remoteUserService.selectSysUserByUserId(getCurrentUserId());
        for (String taskId : bizAudit.getTaskIds())
        {
            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
            ProcessInstance pi = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId()).singleResult();
            BizBusiness bizBusiness = businessService.getById(pi.getBusinessKey());
            if (null != bizBusiness)
            {
                Map<String, Object> variables = Maps.newHashMap();
                variables.put("result", bizAudit.getResult());
                // 审批
                taskService.complete(taskId, variables);
                // 构建插入审批记录
                BizAudit audit = new BizAudit().setTaskId(taskId).setResult(bizAudit.getResult())
                        .setProcName(bizBusiness.getProcName()).setProcDefKey(bizBusiness.getProcDefKey())
                        .setApplyer(bizBusiness.getApplyer()).setAuditor(user.getUserName() + "-" + user.getLoginName())
                        .setAuditorId(user.getUserId());
                bizAuditService.save(audit);
                businessService.setAuditor(bizBusiness, audit.getResult(), getCurrentUserId());
            }
        }
        return R.ok();
    }

    /**
     *  remove审批记录 逻辑删除
     */
    @PostMapping("remove")
    @ApiOperation(value = "remove审批记录 逻辑删除")
    @SentinelResource("remove")
    public R remove(String ids)
    {
        return toAjax(bizAuditService.deleteBizAuditLogic(ids));
    }
}
