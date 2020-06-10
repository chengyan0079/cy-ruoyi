package com.cy.ruoyi.soul.admin.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * The type Meta data do.
 */
@Data
public class MetaDataDO extends BaseDO implements Serializable {

    private String appName;

    private String path;

    private String pathDesc;

    private String rpcType;

    private String serviceName;

    private String methodName;

    private String parameterTypes;

    private String rpcExt;

    /**
     * whether enabled.
     */
    private Boolean enabled;


}
