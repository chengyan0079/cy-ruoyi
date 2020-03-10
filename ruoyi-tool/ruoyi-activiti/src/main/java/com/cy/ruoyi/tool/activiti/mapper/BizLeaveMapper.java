package com.cy.ruoyi.tool.activiti.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.ruoyi.tool.activiti.VO.HiTaskVo;
import com.cy.ruoyi.tool.activiti.entity.BizLeave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 请假Mapper接口
 */
@Mapper
public interface BizLeaveMapper extends BaseMapper<BizLeave>
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
     * @param actLeave 请假
     * @return 请假集合
     */
    List<BizLeave> selectBizLeaveList(@Param("act") BizLeave actLeave);

    /**
     * 新增请假
     * 
     * @param actLeave 请假
     * @return 结果
     */
    int insertBizLeave(BizLeave actLeave);

    /**
     * 修改请假
     * 
     * @param actLeave 请假
     * @return 结果
     */
    int updateBizLeave(BizLeave actLeave);

    /**
     * 删除请假
     * 
     * @param id 请假ID
     * @return 结果
     */
    int deleteBizLeaveById(String id);

    /**
     * 批量删除请假
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBizLeaveByIds(String[] ids);


    //************************************************************************
    /**
     * 根据条件分页查询列表
     * @param page
     * @param actLeave
     * @return
     */
    IPage<BizLeave> selectBizLeaveList(Page page, @Param("act") BizLeave actLeave);
}
