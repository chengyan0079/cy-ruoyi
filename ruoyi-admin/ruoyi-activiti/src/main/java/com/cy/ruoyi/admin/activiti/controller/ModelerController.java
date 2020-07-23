package com.cy.ruoyi.admin.activiti.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.admin.activiti.entity.ActReModel;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.sql.page.PageDomain;
import com.cy.ruoyi.common.sql.page.PageUtils;
import com.cy.ruoyi.common.utils.enums.TradeErrorEnum;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.admin.activiti.service.IActReModelService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * 模型管理
 */
@Controller
@RequestMapping("models")
@Api(value = "ModelerController",description = "模型管理")
public class ModelerController extends BaseController
{
    private static final Log log = LogFactory.get();

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private IActReModelService modelService;

    /**
     * 新建一个空模型
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping("newModel")
    @ApiOperation(value = "新建一个空模型")
    @SentinelResource("newModel")
    public Object newModel() throws UnsupportedEncodingException
    {
        // 初始化一个空模型
        Model model = repositoryService.newModel();
        // 设置一些默认信息
        String name = "new-process";
        String description = "";
        int revision = 1;
        String key = "process";
        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
        modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);
        model.setName(name);
        model.setKey(key);
        model.setMetaInfo(modelNode.toString());
        repositoryService.saveModel(model);
        String id = model.getId();
        // 完善ModelEditorSource
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.replace("stencilset", stencilSetNode);
        repositoryService.addModelEditorSource(id, editorNode.toString().getBytes("utf-8"));
        return "redirect:/modeler.html?modelId=" + id;
    }

    /**
     * 发布模型为流程定义
     * @throws Exception
     */
    @PostMapping("deploy/{id}")
    @ResponseBody
    @ApiOperation(value = "发布模型为流程定义")
    @SentinelResource("deploy/{id}")
    public R deploy(@PathVariable("id") String id) throws Exception
    {
        // 获取模型
        Model modelData = repositoryService.getModel(id);
        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());
        if (bytes == null)
        {
            return R.error(TradeErrorEnum.ACTIVITI_MODELER_NO_DATA);
        }
        JsonNode modelNode = new ObjectMapper().readTree(bytes);
        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        if (model.getProcesses().size() == 0)
        {
            return R.error(TradeErrorEnum.ACTIVITI_MODELER_ERROR);
        }
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);
        // 发布流程
        String processName = modelData.getName() + ".bpmn20.xml";
        Deployment deployment = repositoryService.createDeployment().name(modelData.getName())
                .addString(processName, new String(bpmnBytes, "UTF-8")).deploy();
        modelData.setDeploymentId(deployment.getId());
        repositoryService.saveModel(modelData);
        return R.ok();
    }


    @GetMapping("get/{id}")
    @ApiOperation(value = "get/{id}")
    @SentinelResource("get/{id}")
    public R get(@PathVariable("id") String id)
    {
        Model model = repositoryService.createModelQuery().modelId(id).singleResult();
        return R.ok(model);
    }

    @GetMapping("list")
    @ResponseBody
    @ApiOperation(value = "列表")
    @SentinelResource("list")
    public R getList(ActReModel actReModel)
    {
        PageDomain pageDomain = getPageInfo();
        pageDomain.setOrderByColumn("create_time_");
        pageDomain.setIsAsc("desc");
        log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
        PageUtils page = modelService.selectActReModelList(pageDomain, actReModel);
        return R.ok(page);
    }

    @PostMapping("remove")
    @ResponseBody
    @ApiOperation(value = "删除")
    @SentinelResource("remove")
    public R deleteOne(String ids)
    {
        String[] idsArr = ids.split(",");
        for (String id : idsArr)
        {
            repositoryService.deleteModel(id);
        }
        return R.ok();
    }
}
