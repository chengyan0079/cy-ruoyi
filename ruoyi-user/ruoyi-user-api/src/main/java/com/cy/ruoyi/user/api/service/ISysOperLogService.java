package com.cy.ruoyi.user.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.ruoyi.user.api.entity.SysOperLog;

public interface ISysOperLogService extends IService<SysOperLog> {

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    void insertOperlog(SysOperLog operLog);

}
