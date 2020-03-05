package com.cy.ruoyi.user.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.auth.annotation.HasPermissions;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.log.annotation.OperLog;
import com.cy.ruoyi.common.log.enums.BusinessType;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.user.api.entity.SysDictType;
import com.cy.ruoyi.user.api.service.ISysDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 字典类型 提供者
 * 
 */
@RestController
@RequestMapping("dict/type")
@Api(value = "SysDictTypeController",description = "参数配置")
public class SysDictTypeController extends BaseController
{

	private static final Log log = LogFactory.get();

	@Reference(validation = "true", version = "${dubbo.provider.ISysDictTypeService.version}")
	private ISysDictTypeService sysDictTypeService;
	
	/**
	 * 查询字典类型
	 */
	@GetMapping("get/{dictId}")
	@ApiOperation(value = "查询字典类型")
	public SysDictType get(@PathVariable("dictId") Long dictId)
	{
		return sysDictTypeService.selectDictTypeById(dictId);
		
	}
	
	/**
	 * 查询字典类型列表
	 */
	@GetMapping("list")
	@HasPermissions("system:dict:list")
	@ApiOperation(value = "查询字典类型列表")
	public R list(SysDictType sysDictType)
	{
		PageDomain pageDomain = getPageInfo();
		log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
		PageUtils page = sysDictTypeService.selectDictTypeList(pageDomain, sysDictType);
		return R.ok(page);
	}
	
	
	/**
	 * 新增保存字典类型
	 */
	@OperLog(title = "字典类型", businessType = BusinessType.INSERT)
    @HasPermissions("system:dict:add")
	@PostMapping("save")
	@ApiOperation(value = "新增保存字典类型")
	public R addSave(@RequestBody SysDictType sysDictType)
	{		
		return toAjax(sysDictTypeService.insertDictType(sysDictType));
	}

	/**
	 * 修改保存字典类型
	 */
	@OperLog(title = "字典类型", businessType = BusinessType.UPDATE)
    @HasPermissions("system:dict:edit")
	@PostMapping("update")
	@ApiOperation(value = "修改保存字典类型")
	public R editSave(@RequestBody SysDictType sysDictType)
	{		
		return toAjax(sysDictTypeService.updateDictType(sysDictType));
	}
	
	/**
	 * 删除字典类型
	 * @throws Exception 
	 */
	@OperLog(title = "字典类型", businessType = BusinessType.DELETE)
	@HasPermissions("system:dict:remove")
	@PostMapping("remove")
	@ApiOperation(value = "删除字典类型")
	public R remove(String ids) throws Exception
	{		
		return toAjax(sysDictTypeService.deleteDictTypeByIds(ids));
	}
	
}
