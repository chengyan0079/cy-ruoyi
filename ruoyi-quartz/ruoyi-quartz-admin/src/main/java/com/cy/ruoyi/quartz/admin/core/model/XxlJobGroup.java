package com.cy.ruoyi.quartz.admin.core.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xuxueli on 16/9/30.
 */
@Data
public class XxlJobGroup implements Serializable {

    private int id;
    private String appName;
    private String title;
    private int order;
    private int addressType;        // 执行器地址类型：0=自动注册、1=手动录入
    private String addressList;     // 执行器地址列表，多地址逗号分隔(手动录入)

    // registry list
    private List<String> registryList;  // 执行器地址列表(系统注册)
    public List<String> getRegistryList() {
        if (addressList!=null && addressList.trim().length()>0) {
            registryList = new ArrayList<String>(Arrays.asList(addressList.split(",")));
        }
        return registryList;
    }

}
