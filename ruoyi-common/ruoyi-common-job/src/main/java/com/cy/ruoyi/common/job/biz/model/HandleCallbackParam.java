package com.cy.ruoyi.common.job.biz.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xuxueli on 17/3/2.
 */
@Data
public class HandleCallbackParam implements Serializable {
    private static final long serialVersionUID = 42L;

    private long logId;
    private long logDateTim;

    private ReturnT<String> executeResult;

    public HandleCallbackParam(){}
    public HandleCallbackParam(long logId, long logDateTim, ReturnT<String> executeResult) {
        this.logId = logId;
        this.logDateTim = logDateTim;
        this.executeResult = executeResult;
    }

    @Override
    public String toString() {
        return "HandleCallbackParam{" +
                "logId=" + logId +
                ", logDateTim=" + logDateTim +
                ", executeResult=" + executeResult +
                '}';
    }

}
