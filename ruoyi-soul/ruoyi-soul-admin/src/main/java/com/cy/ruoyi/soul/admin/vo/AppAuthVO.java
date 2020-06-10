package com.cy.ruoyi.soul.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * this is application authority view to web front.
 *
 * @author xiaoyu(Myth)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppAuthVO implements Serializable {

    /**
     * primary key.
     */
    private String id;

    /**
     * application key.
     */
    private String appKey;

    /**
     * encryption secret.
     */
    private String appSecret;

    private String userId;

    private String phone;

    private String extInfo;

    /**
     * whether enabled.
     */
    private Boolean enabled;

    private List<AuthParamVO> authParamVOList;

    /**
     * updated time.
     */
    private String dateUpdated;

}
