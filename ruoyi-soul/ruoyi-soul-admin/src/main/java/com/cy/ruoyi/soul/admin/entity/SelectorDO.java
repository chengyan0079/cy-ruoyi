package com.cy.ruoyi.soul.admin.entity;

import com.cy.ruoyi.soul.admin.dto.SelectorDTO;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.dromara.soul.common.dto.ConditionData;
import org.dromara.soul.common.dto.SelectorData;
import org.dromara.soul.common.enums.MatchModeEnum;
import org.dromara.soul.common.enums.SelectorTypeEnum;
import org.dromara.soul.common.utils.UUIDUtils;

import java.sql.Timestamp;
import java.util.List;

/**
 * SelectorDO.
 *
 * @author jiangxiaofeng(Nicholas)
 */
@Data
public class SelectorDO extends BaseDO {

    /**
     * plugin id.
     */
    private String pluginId;

    /**
     * selector name.
     */
    private String name;

    /**
     * match mode.
     */
    private Integer matchMode;

    /**
     * selector type.
     */
    private Integer type;

    /**
     * sort type.
     */
    private Integer sort;

    /**
     * whether enabled.
     */
    private Boolean enabled;

    /**
     * whether loged.
     */
    private Boolean loged;

    /**
     * whether continued.
     */
    private Boolean continued;

    private String handle;

    /**
     * build selectorDO.
     *
     * @param selectorDTO {@linkplain SelectorDTO}
     * @return {@linkplain SelectorDO}
     */
    public static SelectorDO buildSelectorDO(final SelectorDTO selectorDTO) {
        if (selectorDTO != null) {
            SelectorDO selectorDO = new SelectorDO();
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            if (StringUtils.isEmpty(selectorDTO.getId())) {
                selectorDO.setId(UUIDUtils.getInstance().generateShortUuid());
                selectorDO.setDateCreated(currentTime);
            } else {
                selectorDO.setId(selectorDTO.getId());
            }

            selectorDO.setPluginId(selectorDTO.getPluginId());
            selectorDO.setName(selectorDTO.getName());
            if (SelectorTypeEnum.FULL_FLOW.getCode() == selectorDTO.getType()) {
                selectorDO.setMatchMode(MatchModeEnum.AND.getCode());
            } else {
                selectorDO.setMatchMode(selectorDTO.getMatchMode());
            }
            selectorDO.setType(selectorDTO.getType());
            selectorDO.setSort(selectorDTO.getSort());
            selectorDO.setEnabled(selectorDTO.getEnabled());
            selectorDO.setLoged(selectorDTO.getLoged());
            selectorDO.setContinued(selectorDTO.getContinued());
            selectorDO.setDateUpdated(currentTime);
            selectorDO.setHandle(selectorDTO.getHandle());
            return selectorDO;
        }
        return null;
    }

    /**
     * Trans from selector data.
     *
     * @param selectorDO        the selector do
     * @param pluginName        the plugin name
     * @param conditionDataList the condition data list
     * @return the selector data
     */
    public static SelectorData transFrom(final SelectorDO selectorDO, final String pluginName, final List<ConditionData> conditionDataList) {
        return new SelectorData(selectorDO.getId(),
                selectorDO.getPluginId(),
                pluginName,
                selectorDO.getName(),
                selectorDO.getMatchMode(),
                selectorDO.getType(),
                selectorDO.getSort(),
                selectorDO.getEnabled(),
                selectorDO.getLoged(),
                selectorDO.getContinued(),
                selectorDO.getHandle(),
                conditionDataList);
    }
}
