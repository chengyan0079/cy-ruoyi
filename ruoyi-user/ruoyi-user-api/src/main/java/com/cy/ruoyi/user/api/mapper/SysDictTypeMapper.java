package com.cy.ruoyi.user.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.ruoyi.user.api.entity.SysDictData;
import com.cy.ruoyi.user.api.entity.SysDictType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典表 数据层
 * 
 * @author ruoyi
 */
@Mapper
public interface SysDictTypeMapper extends BaseMapper<SysDictType>
{
    /**
     * 根据条件分页查询字典类型
     * 
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    List<SysDictType> selectDictTypeList(SysDictType dictType);

    /**
     * 批量删除字典类型
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    int deleteDictTypeByIds(Long[] ids);

    /**
     * 修改字典类型信息
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
    SysDictType checkDictTypeUnique(String dictType);
}
