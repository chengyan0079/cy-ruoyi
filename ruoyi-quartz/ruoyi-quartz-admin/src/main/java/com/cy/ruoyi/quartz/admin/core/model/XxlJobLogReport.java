package com.cy.ruoyi.quartz.admin.core.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class XxlJobLogReport implements Serializable {

    private int id;

    private Date triggerDay;

    private int runningCount;
    private int sucCount;
    private int failCount;

}
