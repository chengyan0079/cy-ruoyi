package com.cy.ruoyi.admin.activiti.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.ruoyi.admin.activiti.entity.BizLeave;
import com.cy.ruoyi.common.sql.page.PageDomain;
import com.cy.ruoyi.common.sql.page.PageUtils;

import java.util.List;

/**
 * 请假Service接口
 */
public interface IBizLeaveService extends IService<BizLeave>
{
    /**
     * 查询请假
     * 
     * @param id 请假ID
     * @return 请假
     */
    BizLeave selectBizLeaveById(String id);

    /**
     * 查询请假列表
     * 
     * @param bizLeave 请假
     * @return 请假集合
     */
    List<BizLeave> selectBizLeaveList(BizLeave bizLeave);

    /**
     * 新增请假
     * 
     * @param bizLeave 请假
     * @return 结果
     */
    int insertBizLeave(BizLeave bizLeave);

    /**
     * 修改请假
     * 
     * @param bizLeave 请假
     * @return 结果
     */
    int updateBizLeave(BizLeave bizLeave);

    /**
     * 批量删除请假
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBizLeaveByIds(String ids);

    /**
     * 删除请假信息
     * 
     * @param id 请假ID
     * @return 结果
     */
    int deleteBizLeaveById(String id);

    //***************************************************************************
    /**
     * 根据条件分页查询列表
     */
    PageUtils selectBizLeaveList(PageDomain pageDomain, BizLeave bizLeave);
}
