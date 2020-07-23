package com.cy.ruoyi.admin.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.ruoyi.admin.sys.base.entity.SysOperLog;
import com.cy.ruoyi.admin.sys.base.mapper.SysOperLogMapper;
import com.cy.ruoyi.admin.sys.base.service.ISysOperLogService;
import com.cy.ruoyi.common.sql.page.PageDomain;
import com.cy.ruoyi.common.sql.page.PageUtils;
import com.cy.ruoyi.common.sql.page.Query;
import com.cy.ruoyi.common.utils.text.Convert;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements ISysOperLogService {

    @Autowired
    private SysOperLogMapper operLogMapper;

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(SysOperLog operLog)
    {
        operLogMapper.insertOperlog(operLog);
    }

    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog) {
        return operLogMapper.selectOperLogList(operLog);
    }

    @Override
    public int deleteOperLogByIds(String ids) {
        return operLogMapper.deleteOperLogByIds(Convert.toStrArray(ids));
    }

    @Override
    public SysOperLog selectOperLogById(Long operId) {
        return operLogMapper.selectOperLogById(operId);
    }

    @Override
    public void cleanOperLog() {
        operLogMapper.cleanOperLog();
    }

    @Override
    public PageUtils selectOperLogList(PageDomain pageDomain, SysOperLog operLog){
        if (RegexUtil.isNull(operLog)) {
            operLog = new SysOperLog();
        }
        IPage<SysOperLog> page = operLogMapper.selectOperLogList(new Query<SysOperLog>(pageDomain).getPage(), operLog);
        return new PageUtils(page);
    }

}
