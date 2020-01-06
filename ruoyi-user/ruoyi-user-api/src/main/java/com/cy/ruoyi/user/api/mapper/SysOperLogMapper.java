package com.cy.ruoyi.user.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.ruoyi.user.api.entity.SysNotice;
import com.cy.ruoyi.user.api.entity.SysOperLog;
import java.util.List;

/**
 * 操作日志 数据层
 * 
 * @author ruoyi
 */
public interface SysOperLogMapper extends BaseMapper<SysOperLog>
{

    /**
     * 批量删除系统操作日志
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    int deleteOperLogByIds(String[] ids);
    
    /**
     * 清空操作日志
     */
    void cleanOperLog();
}
