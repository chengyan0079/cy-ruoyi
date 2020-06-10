package com.cy.ruoyi.soul.admin.entity;

import com.cy.ruoyi.soul.admin.dto.AppAuthDTO;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.dromara.soul.common.utils.UUIDUtils;

import java.sql.Timestamp;

/**
 * AppAuthDO.
 *
 * @author xiaoyu(Myth)
 */
@Data
public class AppAuthDO extends BaseDO {

    /**
     * application key.
     */
    private String appKey;

    /**
     * encryption secret.
     */
    private String appSecret;

    /**
     * whether enabled.
     */
    private Boolean enabled;

    private String userId;

    private String phone;

    private String extInfo;

    /**
     * build appAuthDO.
     *
     * @param appAuthDTO {@linkplain AppAuthDTO}
     * @return {@linkplain AppAuthDO}
     */
    public static AppAuthDO buildAppAuthDO(final AppAuthDTO appAuthDTO) {
        if (appAuthDTO != null) {
            AppAuthDO appAuthDO = new AppAuthDO();
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            if (StringUtils.isEmpty(appAuthDTO.getId())) {
                appAuthDO.setId(UUIDUtils.getInstance().generateShortUuid());
                appAuthDO.setDateCreated(currentTime);
            } else {
                appAuthDO.setId(appAuthDTO.getId());
            }

            appAuthDO.setAppKey(appAuthDTO.getAppKey());
            appAuthDO.setAppSecret(appAuthDTO.getAppSecret());
            appAuthDO.setEnabled(appAuthDTO.getEnabled());
            appAuthDO.setDateUpdated(currentTime);
            return appAuthDO;
        }
        return null;
    }
}
