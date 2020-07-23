package com.cy.ruoyi.admin.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.ruoyi.admin.sys.base.entity.SysDictData;
import com.cy.ruoyi.admin.sys.base.mapper.SysDictDataMapper;
import com.cy.ruoyi.admin.sys.base.service.ISysDictDataService;
import com.cy.ruoyi.common.sql.page.PageDomain;
import com.cy.ruoyi.common.sql.page.PageUtils;
import com.cy.ruoyi.common.sql.page.Query;
import com.cy.ruoyi.common.utils.text.Convert;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典 业务层处理
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.ISysDictDataService.version}")
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData>  implements ISysDictDataService
{
    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 根据条件分页查询字典数据
     * 
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataList(SysDictData dictData)
    {
        return dictDataMapper.selectDictDataList(dictData);
    }

    /**
     * 根据字典类型查询字典数据
     * 
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType)
    {
        return dictDataMapper.selectDictDataByType(dictType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     * 
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue)
    {
        return dictDataMapper.selectDictLabel(dictType, dictValue);
    }

    /**
     * 根据字典数据ID查询信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictData selectDictDataById(Long dictCode)
    {
        return dictDataMapper.selectDictDataById(dictCode);
    }

    /**
     * 通过字典ID删除字典数据信息
     * 
     * @param dictCode 字典数据ID
     * @return 结果
     */
    @Override
    public int deleteDictDataById(Long dictCode)
    {
        return dictDataMapper.deleteDictDataById(dictCode);
    }

    /**
     * 批量删除字典数据
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    @Override
    public int deleteDictDataByIds(String ids)
    {
        return dictDataMapper.deleteDictDataByIds(Convert.toStrArray(ids));
    }

    /**
     * 新增保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int insertDictData(SysDictData dictData)
    {
        return dictDataMapper.insertDictData(dictData);
    }

    /**
     * 修改保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int updateDictData(SysDictData dictData)
    {
        return dictDataMapper.updateDictData(dictData);
    }

    /**
     * 根据条件分页查询列表
     */
    @Override
    public PageUtils selectDictDataList(PageDomain pageDomain, SysDictData dictData)
    {
        if (RegexUtil.isNull(dictData)) {
            dictData = new SysDictData();
        }
        IPage<SysDictData> page = dictDataMapper.selectDictDataList(new Query<SysDictData>(pageDomain).getPage(), dictData);
        return new PageUtils(page);
    }
}
