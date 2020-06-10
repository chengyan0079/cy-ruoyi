package com.cy.ruoyi.soul.admin.entity;

import com.cy.ruoyi.soul.admin.dto.SelectorConditionDTO;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.dromara.soul.common.utils.UUIDUtils;

import java.sql.Timestamp;

/**
 * SelectorConditionDO.
 *
 * @author jiangxiaofeng(Nicholas)
 */
@Data
public class SelectorConditionDO extends BaseDO {

    /**
     * selector id.
     */
    private String selectorId;

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
     * build selectorConditionDO.
     *
     * @param selectorConditionDTO {@linkplain SelectorConditionDTO}
     * @return {@linkplain SelectorConditionDO}
     */
    public static SelectorConditionDO buildSelectorConditionDO(final SelectorConditionDTO selectorConditionDTO) {
        if (selectorConditionDTO != null) {
            SelectorConditionDO selectorConditionDO = new SelectorConditionDO();
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            if (StringUtils.isEmpty(selectorConditionDTO.getId())) {
                selectorConditionDO.setId(UUIDUtils.getInstance().generateShortUuid());
                selectorConditionDO.setDateCreated(currentTime);
            } else {
                selectorConditionDO.setId(selectorConditionDTO.getId());
            }

            selectorConditionDO.setParamType(selectorConditionDTO.getParamType());
            selectorConditionDO.setSelectorId(selectorConditionDTO.getSelectorId());
            selectorConditionDO.setOperator(selectorConditionDTO.getOperator());
            selectorConditionDO.setParamName(selectorConditionDTO.getParamName());
            selectorConditionDO.setParamValue(selectorConditionDTO.getParamValue());
            selectorConditionDO.setDateUpdated(currentTime);
            return selectorConditionDO;
        }
        return null;
    }
}
