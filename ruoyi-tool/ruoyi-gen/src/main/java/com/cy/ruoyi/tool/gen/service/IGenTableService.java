package com.cy.ruoyi.tool.gen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.tool.gen.entity.GenTable;
import com.cy.ruoyi.user.api.entity.SysDept;

import java.util.List;
import java.util.Map;

/**
 * 业务 服务层
 */
public interface IGenTableService extends IService<GenTable>
{
    /**
     * 查询业务列表
     * 
     * @param genTable 业务信息
     * @return 业务集合
     */
    List<GenTable> selectGenTableList(GenTable genTable);

    /**
     * 查询据库列表
     * 
     * @param genTable 业务信息
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableList(GenTable genTable);

    /**
     * 查询据库列表
     * 
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     * 查询业务信息
     * 
     * @param id 业务ID
     * @return 业务信息
     */
    GenTable selectGenTableById(Long id);

    /**
     * 修改业务
     * 
     * @param genTable 业务信息
     * @return 结果
     */
    void updateGenTable(GenTable genTable);

    /**
     * 删除业务信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    void deleteGenTableByIds(String ids);

    /**
     * 导入表结构
     * 
     * @param tableList 导入表列表
     * @param operName 操作人员
     */
    void importGenTable(List<GenTable> tableList, String operName);

    /**
     * 预览代码
     * 
     * @param tableId 表编号
     * @return 预览数据列表
     */
    Map<String, String> previewCode(Long tableId);

    /**
     * 生成代码
     * 
     * @param tableName 表名称
     * @return 数据
     */
    byte[] generatorCode(String tableName);

    /**
     * 批量生成代码
     * 
     * @param tableNames 表数组
     * @return 数据
     */
    byte[] generatorCode(String[] tableNames);

    /**
     * 修改保存参数校验
     * 
     * @param genTable 业务信息
     */
    void validateEdit(GenTable genTable);

    //***************************************************************************
    /**
     * 根据条件分页查询列表
     */
    PageUtils selectGenTableList(PageDomain pageDomain, GenTable genTable);
}
