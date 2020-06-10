package com.cy.ruoyi.soul.admin.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * The type Auth param vo.
 *
 * @author xiaoyu
 */
@Data
public class AuthParamVO implements Serializable {

    private String appName;

    private String appParam;
}
