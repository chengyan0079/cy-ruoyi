package com.cy.ruoyi.tool.activiti.DTO;

import com.cy.ruoyi.tool.activiti.consts.ActivitiConstant;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>Title: 审批表单</p>
 */
@Data
public class AuditDTO implements Serializable
{//
    private static final long serialVersionUID = 4220317062676914258L;

    // 任务id
    @NotBlank(message = "任务id不能为空")
    private String id;

    // 流程实例id
    @NotBlank(message = "流程实例id不能为空")
    private String procInstId;

    // 审批意见
    private String comment;

    // 审批结果 2通过(默认) 3驳回
    private Integer result = ActivitiConstant.RESULT_PASS;

    @NotNull(message = "业务key不能为空")
    private Long businessKey;
}
