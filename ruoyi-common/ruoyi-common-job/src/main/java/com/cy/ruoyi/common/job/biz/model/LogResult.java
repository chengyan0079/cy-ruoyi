package com.cy.ruoyi.common.job.biz.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xuxueli on 17/3/23.
 */
@Data
public class LogResult implements Serializable {
    private static final long serialVersionUID = 42L;

    public LogResult(int fromLineNum, int toLineNum, String logContent, boolean isEnd) {
        this.fromLineNum = fromLineNum;
        this.toLineNum = toLineNum;
        this.logContent = logContent;
        this.isEnd = isEnd;
    }

    private int fromLineNum;
    private int toLineNum;
    private String logContent;
    private boolean isEnd;

}
