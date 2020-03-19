package com.cy.ruoyi.quartz.admin.core.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xuxueli on 16/9/30.
 */
@Data
public class XxlJobRegistry implements Serializable {

    private int id;
    private String registryGroup;
    private String registryKey;
    private String registryValue;
    private Date updateTime;

}
