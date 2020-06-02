package com.cy.ruoyi.tool.activiti.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.utils.enums.TradeErrorEnum;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.tool.activiti.VO.ReProcdef;
import com.cy.ruoyi.tool.activiti.entity.ActReProcdef;
import com.cy.ruoyi.tool.activiti.service.IActReProcdefService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 流程控制接口
 */
@RestController
@RequestMapping("prof")
@Api(value = "ActivitiController",description = "流程控制接口")
public class ActivitiController extends BaseController
{
    private static final Log log = LogFactory.get();

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IActReProcdefService procdefService;

    /**
     * 启动一个流程
     * 
     * @param key
     * @return
     * @author zmr
     */
    @GetMapping("start/{key}")
    @ApiOperation(value = "启动一个流程")
    @SentinelResource("start/{key}")
    public R start(@PathVariable("key") String key)
    {
        runtimeService.startProcessInstanceByKey(key);
        return R.ok();
    }

    @GetMapping("allLatest")
    @ApiOperation(value = "所有列表")
    @SentinelResource("allLatest")
    public R list()
    {
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery().latestVersion();
        List<ProcessDefinition> processDefinitions = query.list();
        List<ReProcdef> list = new ArrayList<>();
        for (ProcessDefinition processDefinition : processDefinitions)
        {
            ReProcdef reProcdef = new ReProcdef(processDefinition);
            list.add(reProcdef);
        }
        return R.ok(list);
    }

    @GetMapping("list")
    @ApiOperation(value = "列表")
    @SentinelResource("list")
    public R list(ActReProcdef actReProcdef)
    {
        PageDomain pageDomain = getPageInfo();
        log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
        PageUtils page = procdefService.selectList(pageDomain, actReProcdef);
        return R.ok(page);
    }

    @PostMapping("remove")
    @ApiOperation(value = "删除")
    @SentinelResource("remove")
    public R deleteOne(String ids)
    {
        String[] idArr = ids.split(",");
        for (String id : idArr)
        {
            long count = runtimeService.createProcessInstanceQuery().deploymentId(id).count();
            if (count > 0)
            {
                return R.error(TradeErrorEnum.ACTIVITI_IS_RUNNING);
            }
            else
            {
                // 根据deploymentID删除定义的流程，普通删除
                repositoryService.deleteDeployment(id);
            }
            // 强制删除
            // repositoryService.deleteDeployment(id, true);
            // System.out.println("强制删除--流程定义删除成功");
        }
        return R.ok();
    }
}
