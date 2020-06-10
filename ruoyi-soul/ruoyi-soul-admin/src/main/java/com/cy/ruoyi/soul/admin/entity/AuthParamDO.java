package com.cy.ruoyi.soul.admin.entity;

import lombok.Data;

/**
 * The type Auth param do.
 *
 * @author xiaoyu
 */
@Data
public class AuthParamDO extends BaseDO {

    private String authId;

    private String appName;

    private String appParam;
}
