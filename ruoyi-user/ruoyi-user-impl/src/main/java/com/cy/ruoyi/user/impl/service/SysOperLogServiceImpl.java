package com.cy.ruoyi.user.impl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.core.util.page.Query;
import com.cy.ruoyi.common.utils.text.Convert;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import com.cy.ruoyi.user.api.entity.SysLogininfor;
import com.cy.ruoyi.user.api.entity.SysOperLog;
import com.cy.ruoyi.user.api.mapper.SysOperLogMapper;
import com.cy.ruoyi.user.api.service.ISysOperLogService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(validation = "true", version = "${dubbo.provider.ISysOperLogService.version}")
@org.springframework.stereotype.Service
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
