package com.cy.ruoyi.quartz.admin.core.alarm;


import com.cy.ruoyi.quartz.admin.core.model.XxlJobInfo;
import com.cy.ruoyi.quartz.admin.core.model.XxlJobLog;

/**
 * @author xuxueli 2020-01-19
 */
public interface JobAlarm {

    /**
     * job alarm
     *
     * @param info
     * @param jobLog
     * @return
     */
    public boolean doAlarm(XxlJobInfo info, XxlJobLog jobLog);

}
