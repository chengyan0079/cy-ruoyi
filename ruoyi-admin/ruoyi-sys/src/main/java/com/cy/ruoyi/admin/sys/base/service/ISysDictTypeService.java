package com.cy.ruoyi.admin.sys.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.ruoyi.admin.sys.base.entity.SysDictType;
import com.cy.ruoyi.common.sql.page.PageDomain;
import com.cy.ruoyi.common.sql.page.PageUtils;

import java.util.List;

/**
 * 字典 业务层
 */
public interface ISysDictTypeService extends IService<SysDictType>
{
    /**
     * 根据条件分页查询字典类型
     * 
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    List<SysDictType> selectDictTypeList(SysDictType dictType);

    /**
     * 根据所有字典类型
     * 
     * @return 字典类型集合信息
     */
    List<SysDictType> selectDictTypeAll();

    /**
     * 根据字典类型ID查询信息
     * 
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    SysDictType selectDictTypeById(Long dictId);

    /**
     * 通过字典ID删除字典信息
     * 
     * @param dictId 字典ID
     * @return 结果
     */
    int deleteDictTypeById(Long dictId);

    /**
     * 批量删除字典类型
     * 
     * @param ids 需要删除的数据
     * @return 结果
     * @throws Exception 异常
     */
    int deleteDictTypeByIds(String ids) throws Exception;

    /**
     * 新增保存字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    int insertDictType(SysDictType dictType);

    /**
     * 修改保存字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    int updateDictType(SysDictType dictType);

    /**
     * 校验字典类型称是否唯一
     * 
     * @param dictType 字典类型
     * @return 结果
     */
    String checkDictTypeUnique(SysDictType dictType);


    //*************************************************************************************
    /**
     *  分页查询
     * @param pageDomain
     * @param dictType
     * @return
     */
    PageUtils selectDictTypeList(PageDomain pageDomain, SysDictType dictType);
}
