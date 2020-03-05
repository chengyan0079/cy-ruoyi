package com.cy.ruoyi.user.app.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.user.api.entity.SysConfig;
import com.cy.ruoyi.user.api.service.ISysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

/**
 * 参数配置 提供者
 */
@RestController
@RequestMapping("config")
@Api(value = "SysConfigController",description = "参数配置")
public class SysConfigController extends BaseController
{

	private static final Log log = LogFactory.get();

	@Reference(validation = "true", version = "${dubbo.provider.ISysConfigService.version}")
	private ISysConfigService sysConfigService;
	
	/**
	 * 查询参数配置
	 */
	@GetMapping("get/{configId}")
	@ApiOperation(value = "查询参数配置")
	public SysConfig get(@PathVariable("configId") Long configId)
	{
		return sysConfigService.selectConfigById(configId);
		
	}
	
	/**
	 * 查询参数配置列表
	 */
	@GetMapping("list")
	@ApiOperation(value = "查询参数配置列表")
	public R list(SysConfig sysConfig)
	{
		PageDomain pageDomain = getPageInfo();
		log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
		PageUtils page = sysConfigService.selectConfigList(pageDomain, sysConfig);
		return R.ok(page);
	}
	
	
	/**
	 * 新增保存参数配置
	 */
	@PostMapping("save")
	@ApiOperation(value = "新增保存参数配置")
	public R addSave(@RequestBody SysConfig sysConfig)
	{		
		return toAjax(sysConfigService.save(sysConfig));
	}

	/**
	 * 修改保存参数配置
	 */
	@PostMapping("update")
	@ApiOperation(value = "修改保存参数配置")
	public R editSave(@RequestBody SysConfig sysConfig)
	{		
		return toAjax(sysConfigService.saveOrUpdate(sysConfig));
	}
	
	/**
	 * 删除参数配置
	 */
	@PostMapping("remove")
	@ApiOperation(value = "删除参数配置")
	public R remove(String ids)
	{		
		return toAjax(sysConfigService.deleteConfigByIds(ids));
	}
	
}
