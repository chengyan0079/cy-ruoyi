package com.cy.ruoyi.admin.sys.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.ruoyi.admin.sys.base.entity.SysLogininfor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统访问日志情况信息 数据层
 */
@Mapper
public interface SysLogininforMapper extends BaseMapper<SysLogininfor>
{
    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    void insertLogininfor(SysLogininfor logininfor);

    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    List<SysLogininfor> selectLogininforList(@Param("logininfor") SysLogininfor logininfor);

    /**
     * 批量删除系统登录日志
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    int deleteLogininforByIds(String[] ids);

    /**
     * 清空系统登录日志
     *
     * @return 结果
     */
    int cleanLogininfor();

    //**************************************************************************8
    /**
     * 根据条件分页查询
     * @param page
     * @param logininfor
     * @return
     */
    IPage<SysLogininfor> selectLogininforList(Page page, @Param("logininfor") SysLogininfor logininfor);
}
