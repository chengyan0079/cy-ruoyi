package com.cy.ruoyi.admin.activiti.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.admin.activiti.VO.ProcessNodeVo;
import com.cy.ruoyi.admin.activiti.consts.ActivitiConstant;
import com.cy.ruoyi.admin.activiti.service.IBizNodeService;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.utils.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.bpmn.model.*;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 */
@RestController
@RequestMapping("node")
@Api(value = "BizNodeController",description = "")
public class BizNodeController extends BaseController
{
    private static final Log log = LogFactory.get();

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private IBizNodeService bizNodeService;

    /**
     * 获取节点列表
     */
    @GetMapping("list/{proDefId}")
    @ApiOperation(value = "获取节点列表")
    @SentinelResource("remove")
    public R list(@PathVariable String proDefId)
    {
        List<ProcessNodeVo> list = new ArrayList<>();
        BpmnModel model = repositoryService.getBpmnModel(proDefId);
        if (model != null)
        {
            Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
            for (FlowElement element : flowElements)
            {
                ProcessNodeVo node = new ProcessNodeVo();
                node.setNodeId(element.getId());
                node.setName(element.getName());
                if (element instanceof StartEvent)
                {
                    // 开始节点
                    node.setType(ActivitiConstant.NODE_TYPE_START);
                }
                else if (element instanceof UserTask)
                {
                    // 用户任务
                    node.setType(ActivitiConstant.NODE_TYPE_TASK);
                }
                else if (element instanceof EndEvent)
                {
                    // 结束
                    node.setType(ActivitiConstant.NODE_TYPE_END);
                }
                else
                {
                    // 排除其他连线或节点
                    continue;
                }
                list.add(node);
            }
        }
        return result(list);
    }

    /**
     * 获取节点属性
     */
    @GetMapping("get/{nodeId}")
    @ApiOperation(value = "获取节点属性")
    @SentinelResource("remove")
    public R get(@PathVariable String nodeId)
    {
        ProcessNodeVo node = new ProcessNodeVo();
        node.setNodeId(nodeId);
        // 设置关联角色，用户，负责人
        node = bizNodeService.setAuditors(node);
        return R.ok(node);
    }

    /**
     * 修改节点属性
     */
    @PostMapping("update")
    @ApiOperation(value = "修改节点属性")
    @SentinelResource("update")
    public R update(@RequestBody ProcessNodeVo node)
    {
        bizNodeService.updateBizNode(node);
        return R.ok();
    }
}
