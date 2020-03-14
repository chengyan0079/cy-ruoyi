package com.cy.ruoyi.tool.activiti.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.tool.activiti.VO.HiProcInsVo;
import com.cy.ruoyi.tool.activiti.VO.ProcessInsVo;
import com.cy.ruoyi.tool.activiti.VO.RuTask;
import com.cy.ruoyi.tool.activiti.consts.ActivitiConstant;
import com.cy.ruoyi.tool.activiti.entity.BizBusiness;
import com.cy.ruoyi.tool.activiti.feign.RemoteUserService;
import com.cy.ruoyi.tool.activiti.service.IBizBusinessService;
import com.cy.ruoyi.tool.activiti.service.IHistoryInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: 流程实例</p>
 */
@RestController
@RequestMapping("process/ins/")
@Api(value = "ProcessInsController",description = "流程实例")
public class ProcessInsController extends BaseController
{
    private static final Log log = LogFactory.get();

    @Autowired
    private RuntimeService      runtimeService;

    @Autowired
    private TaskService         taskService;

    @Autowired
    private HistoryService      historyService;

    @Autowired
    private IHistoryInfoService historyInfoService;

    @Autowired
    private RemoteUserService userService;

    @Autowired
    private IBizBusinessService bizBusinessService;

    /**
     * 挂起、激活流程实例
     */
    @RequestMapping(value = "update/{processInstanceId}/{state}")
    @ApiOperation(value = "挂起、激活流程实例")
    public R updateState(@PathVariable("state") String state,
                         @PathVariable("processInstanceId") String processInstanceId)
    {
        if (state.equals("active"))
        {
            runtimeService.activateProcessInstanceById(processInstanceId);
            log.info("已激活ID为:{}的流程实例", processInstanceId);
        }
        else if (state.equals("suspend"))
        {
            runtimeService.suspendProcessInstanceById(processInstanceId);
            log.info("已挂起ID为:{}的流程实例", processInstanceId);
        }
        return R.ok();
    }

    /**
     * 获取任务列表
     * @param page
     * @return
     */
    @RequestMapping(value = "tasks")
    @ApiOperation(value = "获取任务列表")
    public R tasks(PageDomain page)
    {
        List<Task> tasks = taskService.createTaskQuery().listPage(page.getPageSize() * (page.getPageNum() - 1),
                page.getPageSize());
        long count = runtimeService.createExecutionQuery().count();
        List<RuTask> list = new ArrayList<>();
        for (Task task : tasks)
        {
            list.add(new RuTask(task));
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("rows", list);
        m.put("pageNum", page.getPageNum());
        m.put("total", count);
        return R.ok(m);
    }

    @RequestMapping(value = "runs")
    @ApiOperation(value = "runs")
    public R getList(PageDomain page, String name, String key)
    {
        List<ProcessInsVo> list = new ArrayList<>();
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().orderByProcessInstanceId().desc();
        if (StrUtil.isNotBlank(name))
        {
            query.processInstanceNameLike("%" + name + "%");
        }
        if (StrUtil.isNotBlank(key))
        {
            query.processDefinitionKey(key);
        }
        long count = query.count();
        int first = (page.getPageNum() - 1) * page.getPageSize();
        List<ProcessInstance> processInstanceList = query.listPage(first, page.getPageSize());
        processInstanceList.forEach(e -> {
            list.add(new ProcessInsVo(e));
        });
        list.forEach(e -> {
            List<HistoricIdentityLink> identityLinks = historyService
                    .getHistoricIdentityLinksForProcessInstance(e.getId());
            for (HistoricIdentityLink hik : identityLinks)
            {
                // 关联发起人
                if ("starter".equals(hik.getType()) && StrUtil.isNotBlank(hik.getUserId()))
                {
                    e.setApplyer(userService.selectSysUserByUserId(Long.valueOf(hik.getUserId())).getLoginName());
                }
            }
            // 关联当前任务
            Task task = taskService.createTaskQuery().processInstanceId(e.getId()).singleResult();
            if (task != null)
            {
                e.setCurrTaskName(task.getName());
            }
        });
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("rows", list);
        m.put("pageNum", page.getPageNum());
        m.put("total", count);
        return R.ok(m);
    }

    @RequestMapping(value = "finished")
    @ApiOperation(value = "finished")
    public R finush(HiProcInsVo hiProcInsVo)
    {
        PageDomain pageDomain = getPageInfo();
        log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
        PageUtils page = historyInfoService.getHiProcInsListDone(pageDomain, hiProcInsVo);
        return R.ok(page);
    }


    /**
     * 删除运行中实例
     */
    @PostMapping(value = "remove")
    @ApiOperation(value = "删除运行中实例")
    public R remove(String ids, String reason)
    {
        if (StrUtil.isBlank(reason))
        {
            reason = "";
        }
        String[] idArr = ids.split(",");
        for (String id : idArr)
        {
            // 关联业务状态结束
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(id).singleResult();
            BizBusiness bizBusiness = new BizBusiness();
            bizBusiness.setId(Long.valueOf(pi.getBusinessKey()));
            bizBusiness.setCurrentTask(ActivitiConstant.END_TASK_NAME);
            bizBusiness.setStatus(ActivitiConstant.STATUS_CANCELED);
            bizBusiness.setResult(ActivitiConstant.RESULT_CANCELED);
            bizBusinessService.updateBizBusiness(bizBusiness);
            runtimeService.deleteProcessInstance(id, reason);
        }
        return R.ok();
    }

    /**
     * 删除已经结束的实例
     */
    @RequestMapping("remove/his")
    @ApiOperation(value = "删除已经结束的实例")
    public R removeHis(String ids)
    {
        String[] idArr = ids.split(",");
        for (String id : idArr)
        {
            historyService.deleteHistoricProcessInstance(id);
        }
        return R.ok();
    }
}
