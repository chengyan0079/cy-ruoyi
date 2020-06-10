package com.cy.ruoyi.soul.admin.entity;

import com.cy.ruoyi.soul.admin.dto.DashboardUserDTO;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.dromara.soul.common.utils.UUIDUtils;

import java.sql.Timestamp;

/**
 * DashboardUserDO.
 *
 * @author jiangxiaofeng(Nicholas)
 */
@Data
public class DashboardUserDO extends BaseDO {

    /**
     * user name.
     */
    private String userName;

    /**
     * user password.
     */
    private String password;

    /**
     * dashboard role.
     */
    private Integer role;

    /**
     * whether enabled.
     */
    private Boolean enabled;

    /**
     * build dashboardUserDO.
     *
     * @param dashboardUserDTO {@linkplain DashboardUserDTO}
     * @return {@linkplain DashboardUserDO}
     */
    public static DashboardUserDO buildDashboardUserDO(final DashboardUserDTO dashboardUserDTO) {
        if (dashboardUserDTO != null) {
            DashboardUserDO dashboardUserDO = new DashboardUserDO();
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            if (StringUtils.isEmpty(dashboardUserDTO.getId())) {
                dashboardUserDO.setId(UUIDUtils.getInstance().generateShortUuid());
                dashboardUserDO.setEnabled(true);
                dashboardUserDO.setDateCreated(currentTime);
            } else {
                dashboardUserDO.setId(dashboardUserDTO.getId());
                dashboardUserDO.setEnabled(dashboardUserDTO.getEnabled());
            }
            dashboardUserDO.setUserName(dashboardUserDTO.getUserName());
            dashboardUserDO.setPassword(dashboardUserDTO.getPassword());
            dashboardUserDO.setRole(dashboardUserDTO.getRole());
            dashboardUserDO.setDateUpdated(currentTime);
            return dashboardUserDO;
        }
        return null;
    }
}
