package com.cy.ruoyi.admin.sys.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.admin.sys.base.entity.SysDictData;
import com.cy.ruoyi.admin.sys.base.service.ISysDictDataService;
import com.cy.ruoyi.common.auth.annotation.HasPermissions;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.log.annotation.OperLog;
import com.cy.ruoyi.common.log.enums.BusinessType;
import com.cy.ruoyi.common.sql.page.PageDomain;
import com.cy.ruoyi.common.sql.page.PageUtils;
import com.cy.ruoyi.common.utils.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典数据 提供者
 */
@RestController
@RequestMapping("dict/data")
@Api(value = "SysDictDataController",description = "字典数据 ")
public class SysDictDataController extends BaseController
{

	private static final Log log = LogFactory.get();

	@Autowired
	private ISysDictDataService sysDictDataService;
	
	/**
	 * 查询字典数据
	 */
	@GetMapping("get/{dictCode}")
	@ApiOperation(value = "查询字典数据")
	@SentinelResource("get/{dictCode}")
	public SysDictData get(@PathVariable("dictCode") Long dictCode)
	{
		return sysDictDataService.selectDictDataById(dictCode);
		
	}
	
	/**
	 * 查询字典数据列表
	 */
	@GetMapping("list")
	@HasPermissions("system:dict:list")
	@ApiOperation(value = "查询字典数据列表")
	@SentinelResource("list")
	public R list(SysDictData sysDictData)
	{
		PageDomain pageDomain = getPageInfo();
		log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
		PageUtils page = sysDictDataService.selectDictDataList(pageDomain, sysDictData);
		return R.ok(page);
	}
	
	/**
     * 根据字典类型查询字典数据信息
     * 
     * @param dictType 字典类型
     * @return 参数键值
     */
	@GetMapping("type")
	@ApiOperation(value = "根据字典类型查询字典数据信息")
	@SentinelResource("type")
    public List<SysDictData> getType(String dictType)
    {
        return sysDictDataService.selectDictDataByType(dictType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     * 
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
	@GetMapping("label")
	@ApiOperation(value = "根据字典类型和字典键值查询字典数据信息")
	@SentinelResource("label")
    public String getLabel(String dictType, String dictValue)
    {
        return sysDictDataService.selectDictLabel(dictType, dictValue);
    }
	
	
	/**
	 * 新增保存字典数据
	 */
	@OperLog(title = "字典数据", businessType = BusinessType.INSERT)
    @HasPermissions("system:dict:add")
	@PostMapping("save")
	@ApiOperation(value = "新增保存字典数据")
	@SentinelResource("save")
	public R addSave(@RequestBody SysDictData sysDictData)
	{		
		return toAjax(sysDictDataService.insertDictData(sysDictData));
	}

	/**
	 * 修改保存字典数据
	 */
	@OperLog(title = "字典数据", businessType = BusinessType.UPDATE)
    @HasPermissions("system:dict:edit")
	@PostMapping("update")
	@ApiOperation(value = "修改保存字典数据")
	@SentinelResource("update")
	public R editSave(@RequestBody SysDictData sysDictData)
	{		
		return toAjax(sysDictDataService.updateDictData(sysDictData));
	}
	
	/**
	 * 删除字典数据
	 */
	@OperLog(title = "字典数据", businessType = BusinessType.DELETE)
    @HasPermissions("system:dict:remove")
	@PostMapping("remove")
	@ApiOperation(value = "删除字典数据")
	@SentinelResource("remove")
	public R remove(String ids)
	{		
		return toAjax(sysDictDataService.deleteDictDataByIds(ids));
	}
	
}
