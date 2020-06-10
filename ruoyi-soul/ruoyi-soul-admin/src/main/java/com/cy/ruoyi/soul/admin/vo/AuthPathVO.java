package com.cy.ruoyi.soul.admin.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * The type Auth path vo.
 *
 * @author xiaoyu
 */
@Data
public class AuthPathVO implements Serializable {

    private String id;

    private String appName;

    private String path;

    private Boolean enabled;
}
