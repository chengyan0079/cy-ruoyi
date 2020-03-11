package com.cy.ruoyi.tool.activiti.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import com.cy.ruoyi.tool.activiti.consts.ActivitiConstant;
import com.cy.ruoyi.tool.activiti.entity.ActReProcdef;
import com.cy.ruoyi.tool.activiti.entity.BizBusiness;
import com.cy.ruoyi.tool.activiti.mapper.BizBusinessMapper;
import com.cy.ruoyi.tool.activiti.service.IBizBusinessService;
import com.cy.ruoyi.tool.activiti.service.IBizNodeService;
import com.google.common.collect.Lists;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 */
@Service
public class BizBusinessServiceImpl extends ServiceImpl<BizBusinessMapper, BizBusiness> implements IBizBusinessService
{
    @Autowired
    private BizBusinessMapper businessMapper;

    @Autowired
    private RuntimeService    runtimeService;

    @Autowired
    private IdentityService   identityService;

    @Autowired
    private TaskService       taskService;

    @Autowired
    private IBizNodeService bizNodeService;

    /**
     * 查询流程业务
     * 
     * @param id 流程业务ID
     * @return 流程业务
     */
    @Override
    public BizBusiness selectBizBusinessById(String id)
    {
        return businessMapper.selectById(id);
    }

    /**
     * 查询流程业务列表
     * 
     * @param bizBusiness 流程业务
     * @return 流程业务
     */
    @Override
    public List<BizBusiness> selectBizBusinessList(BizBusiness bizBusiness)
    {
        return businessMapper.selectList(new QueryWrapper<BizBusiness>()
        .eq("user_id", bizBusiness.getUserId())
        .eq("del_flag", bizBusiness.getDelFlag())
        .eq(RegexUtil.isNotNull(bizBusiness.getTitle()), "title", bizBusiness.getTitle())
        .eq(RegexUtil.isNotNull(bizBusiness.getStatus()), "status", bizBusiness.getStatus())
        .eq(RegexUtil.isNotNull(bizBusiness.getResult()), "result", bizBusiness.getResult())
        );
    }

    /**
     * 新增流程业务
     * 
     * @param bizBusiness 流程业务
     * @return 结果
     */
    @Override
    public int insertBizBusiness(BizBusiness bizBusiness)
    {
        return businessMapper.insert(bizBusiness);
    }

    /**
     * 修改流程业务
     *
     * @param bizBusiness 流程业务
     * @return 结果
     */
    @Override
    public int updateBizBusiness(BizBusiness bizBusiness)
    {
        return businessMapper.updateById(bizBusiness);
    }

//    /**
//     * 删除流程业务对象
//     *
//     * @param ids 需要删除的数据ID
//     * @return 结果
//     */
//    @Override
//    public int deleteBizBusinessByIds(String ids)
//    {
//        return businessMapper.deleteByIds(ids);
//    }

    /**
     * 删除流程业务信息
     *
     * @param id 流程业务ID
     * @return 结果
     */
    @Override
    public int deleteBizBusinessById(Long id)
    {
        return businessMapper.deleteById(id);
    }

    /* (non-Javadoc)
     * @see com.ruoyi.activiti.service.IBizBusinessService#deleteBizBusinessLogic(java.lang.String)
     */
//    @Override
//    public int deleteBizBusinessLogic(String ids)
//    {
//        Example example = new Example(BizBusiness.class);
//        example.createCriteria().andIn("id", Lists.newArrayList(ids.split(",")));
//        return businessMapper.updateByExampleSelective(new BizBusiness().setDelFlag(true), example);
//    }

    /* (non-Javadoc)
     * @see com.ruoyi.activiti.service.IBizBusinessService#startProcess(com.ruoyi.activiti.entity.BizBusiness, java.util.Map)
     */
    @Override
    public void startProcess(BizBusiness business, Map<String, Object> variables)
    {
        // 启动流程用户
        identityService.setAuthenticatedUserId(business.getUserId().toString());
        // 启动流程 需传入业务表id变量
        ProcessInstance pi = runtimeService.startProcessInstanceById(business.getProcDefId(),
                business.getId().toString(), variables);
        // 设置流程实例名称
        runtimeService.setProcessInstanceName(pi.getId(), business.getTitle());
        BizBusiness bizBusiness = new BizBusiness().setId(business.getId()).setProcInstId(pi.getId())
                .setProcDefKey(pi.getProcessDefinitionKey());
        // 假如开始就没有任务，那就认为是中止的流程，通常是不存在的
        setAuditor(bizBusiness, ActivitiConstant.RESULT_SUSPEND, business.getUserId());
    }

    @Override
    public int setAuditor(BizBusiness business, int result, long currentUserId)
    {
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(business.getProcInstId()).list();
        if (null != tasks && tasks.size() > 0)
        {
            Task task = tasks.get(0);
            Set<String> auditors = bizNodeService.getAuditors(task.getTaskDefinitionKey(), currentUserId);
            if (null != auditors && auditors.size() > 0)
            {
                // 添加审核候选人
                for (String auditor : auditors)
                {
                    taskService.addCandidateUser(task.getId(), auditor);
                }
                business.setCurrentTask(task.getName());
            }
            else
            {
                runtimeService.deleteProcessInstance(task.getProcessInstanceId(),
                        ActivitiConstant.SUSPEND_PRE + "no auditor");
                business.setCurrentTask(ActivitiConstant.END_TASK_NAME).setStatus(ActivitiConstant.STATUS_SUSPEND)
                        .setResult(ActivitiConstant.RESULT_SUSPEND);
            }
        }
        else
        {
            // 任务结束
            business.setCurrentTask(ActivitiConstant.END_TASK_NAME).setStatus(ActivitiConstant.STATUS_FINISH)
                    .setResult(result);
        }
        return updateBizBusiness(business);
    }
}
