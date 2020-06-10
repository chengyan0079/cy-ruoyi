package com.cy.ruoyi.soul.admin.entity;

import com.cy.ruoyi.soul.admin.dto.RuleConditionDTO;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.dromara.soul.common.utils.UUIDUtils;

import java.sql.Timestamp;

/**
 * RuleConditionDO.
 *
 * @author jiangxiaofeng(Nicholas)
 */
@Data
public class RuleConditionDO extends BaseDO {

    /**
     * rule id.
     */
    private String ruleId;

    /**
     * parameter type.
     */
    private String paramType;

    /**
     * match operator.
     */
    private String operator;

    /**
     * parameter name.
     */
    private String paramName;

    /**
     * parameter value.
     */
    private String paramValue;

    /**
     * build ruleConditionDO.
     *
     * @param ruleConditionDTO {@linkplain RuleConditionDTO}
     * @return {@linkplain RuleConditionDO}
     */
    public static RuleConditionDO buildRuleConditionDO(final RuleConditionDTO ruleConditionDTO) {
        if (ruleConditionDTO != null) {
            RuleConditionDO ruleConditionDO = new RuleConditionDO();
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            if (StringUtils.isEmpty(ruleConditionDTO.getId())) {
                ruleConditionDO.setId(UUIDUtils.getInstance().generateShortUuid());
                ruleConditionDO.setDateCreated(currentTime);
            } else {
                ruleConditionDO.setId(ruleConditionDTO.getId());
            }

            ruleConditionDO.setParamType(ruleConditionDTO.getParamType());
            ruleConditionDO.setRuleId(ruleConditionDTO.getRuleId());
            ruleConditionDO.setOperator(ruleConditionDTO.getOperator());
            ruleConditionDO.setParamName(ruleConditionDTO.getParamName());
            ruleConditionDO.setParamValue(ruleConditionDTO.getParamValue());
            ruleConditionDO.setDateUpdated(currentTime);
            return ruleConditionDO;
        }
        return null;
    }
}
