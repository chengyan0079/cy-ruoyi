package com.cy.ruoyi.soul.admin.entity;

import lombok.Data;

/**
 * The type Auth path do.
 *
 * @author xiaoyu
 */
@Data
public class AuthPathDO extends BaseDO {

    private String authId;

    private String appName;

    private String path;

    private Boolean enabled;


}
