package com.cy.ruoyi.soul.admin.entity;

import com.cy.ruoyi.soul.admin.dto.RuleDTO;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.dromara.soul.common.dto.ConditionData;
import org.dromara.soul.common.dto.RuleData;
import org.dromara.soul.common.utils.UUIDUtils;

import java.sql.Timestamp;
import java.util.List;

/**
 * RuleDO.
 *
 * @author jiangxiaofeng(Nicholas)
 */
@Data
public class RuleDO extends BaseDO {

    /**
     * selector id.
     */
    private String selectorId;

    /**
     * match mode.
     */
    private Integer matchMode;

    /**
     * rule name.
     */
    private String name;

    /**
     * whether enabled.
     */
    private Boolean enabled;

    /**
     * whether loged.
     */
    private Boolean loged;

    /**
     * sort type.
     */
    private Integer sort;

    /**
     * process logic.
     */
    private String handle;

    /**
     * build ruleDO.
     *
     * @param ruleDTO {@linkplain RuleDTO}
     * @return {@linkplain RuleDO}
     */
    public static RuleDO buildRuleDO(final RuleDTO ruleDTO) {
        if (ruleDTO != null) {
            RuleDO ruleDO = new RuleDO();
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            if (StringUtils.isEmpty(ruleDTO.getId())) {
                ruleDO.setId(UUIDUtils.getInstance().generateShortUuid());
                ruleDO.setDateCreated(currentTime);
            } else {
                ruleDO.setId(ruleDTO.getId());
            }

            ruleDO.setSelectorId(ruleDTO.getSelectorId());
            ruleDO.setMatchMode(ruleDTO.getMatchMode());
            ruleDO.setName(ruleDTO.getName());
            ruleDO.setEnabled(ruleDTO.getEnabled());
            ruleDO.setLoged(ruleDTO.getLoged());
            ruleDO.setSort(ruleDTO.getSort());
            ruleDO.setHandle(ruleDTO.getHandle());
            ruleDO.setDateUpdated(currentTime);
            return ruleDO;
        }
        return null;
    }

    public static RuleData transFrom(final RuleDO ruleDO, final String pluginName, final List<ConditionData> conditionDataList) {
        return new RuleData(ruleDO.getId(),
                ruleDO.getName(),
                pluginName,
                ruleDO.getSelectorId(),
                ruleDO.getMatchMode(),
                ruleDO.getSort(),
                ruleDO.getEnabled(),
                ruleDO.getLoged(),
                ruleDO.getHandle(),
                conditionDataList);
    }
}
