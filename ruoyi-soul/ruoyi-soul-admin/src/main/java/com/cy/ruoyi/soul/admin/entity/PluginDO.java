package com.cy.ruoyi.soul.admin.entity;

import com.cy.ruoyi.soul.admin.dto.PluginDTO;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.dromara.soul.common.utils.UUIDUtils;

import java.sql.Timestamp;

/**
 * The config field has been added in 2.0
 * PluginDO.
 *
 * @author jiangxiaofeng(Nicholas)
 * @author xiaoyu
 */
@Data
public class PluginDO extends BaseDO {

    /**
     * plugin name.
     */
    private String name;

    /**
     * plugin config @see 2.0.
     */
    private String config;

    /**
     * whether enabled.
     */
    private Boolean enabled;

    /**
     * the role.
     * {@linkplain org.dromara.soul.common.enums.PluginRoleEnum}
     */
    private Integer role;

    /**
     * build pluginDO.
     *
     * @param pluginDTO {@linkplain PluginDTO}
     * @return {@linkplain PluginDO}
     */
    public static PluginDO buildPluginDO(final PluginDTO pluginDTO) {
        if (pluginDTO != null) {
            PluginDO pluginDO = new PluginDO();
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            if (StringUtils.isEmpty(pluginDTO.getId())) {
                pluginDO.setId(UUIDUtils.getInstance().generateShortUuid());
                pluginDO.setDateCreated(currentTime);
            } else {
                pluginDO.setId(pluginDTO.getId());
            }
            pluginDO.setName(pluginDTO.getName());
            pluginDO.setConfig(pluginDTO.getConfig());
            pluginDO.setEnabled(pluginDTO.getEnabled());
            pluginDO.setRole(pluginDTO.getRole());
            pluginDO.setDateUpdated(currentTime);
            return pluginDO;
        }
        return null;
    }
}
