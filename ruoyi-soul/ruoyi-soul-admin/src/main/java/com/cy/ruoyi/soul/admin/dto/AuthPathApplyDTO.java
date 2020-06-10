package com.cy.ruoyi.soul.admin.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * The type Auth path apply dto.
 *
 * @author xiaoyu
 */
@Data
public class AuthPathApplyDTO implements Serializable {

    private String appName;

    private String path;
}
