package com.cy.ruoyi.admin.activiti.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.ruoyi.admin.activiti.VO.HiProcInsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HistoryMapper extends BaseMapper<HiProcInsVo>
{
    List<HiProcInsVo> getHiProcInsListDone(@Param("hip") HiProcInsVo hiProcInsVo);

    //************************************************************************
    /**
     * 根据条件分页查询列表
     * @param page
     * @param hiProcInsVo
     * @return
     */
    IPage<HiProcInsVo> getHiProcInsListDone(Page page, @Param("hip") HiProcInsVo hiProcInsVo);

}