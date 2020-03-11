package com.cy.ruoyi.tool.gen.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.auth.annotation.HasPermissions;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.log.annotation.OperLog;
import com.cy.ruoyi.common.log.enums.BusinessType;
import com.cy.ruoyi.common.utils.text.Convert;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.tool.gen.entity.GenTable;
import com.cy.ruoyi.tool.gen.entity.GenTableColumn;
import com.cy.ruoyi.tool.gen.service.IGenTableColumnService;
import com.cy.ruoyi.tool.gen.service.IGenTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 操作处理
 */
@RestController
@Api(value = "GenController",description = "代码生成")
public class GenController extends BaseController
{
    private static final Log log = LogFactory.get();

    @Autowired
    private IGenTableService genTableService;

    @Autowired
    private IGenTableColumnService genTableColumnService;

    /**
     * 查询代码生成列表
     */
    @HasPermissions("tool:gen:list")
    @GetMapping("/list")
    @ApiOperation(value = "查询代码生成列表")
    public R genList(GenTable genTable)
    {
        PageDomain pageDomain = getPageInfo();
		log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
		PageUtils page = genTableService.selectGenTableList(pageDomain, genTable);
		return R.ok(page);
    }
    
    /**
     * 修改代码生成业务
     */
    @GetMapping("/get/{tableId}")
    @ApiOperation(value = "修改代码生成业务")
    public R get(@PathVariable("tableId") Long tableId)
    {
        GenTable table = genTableService.selectGenTableById(tableId);
        return R.ok(table);
    }

    /**
     * 查询数据库列表
     */
    @HasPermissions("tool:gen:list")
    @GetMapping("/db/list")
    @ApiOperation(value = "查询数据库列表")
    public R dataList(GenTable genTable)
    {
        List<GenTable> list = genTableService.selectDbTableList(genTable);
        return result(list);
    }

    /**
     * 查询数据表字段列表
     */
    @HasPermissions("tool:gen:edit")
    @GetMapping("edit")
    @ApiOperation(value = "查询数据表字段列表")
    public R edit(GenTableColumn genTableColumn)
    {
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(genTableColumn);
        GenTable table = genTableService.selectGenTableById(genTableColumn.getTableId());
        Map<String ,Object> map = new HashMap<>();
        map.put("child", table);
        map.put("rows", list);
        map.put("total", list.size());
        return R.ok(map);
    }

    /**
     * 导入表结构（保存）
     */
    @HasPermissions("tool:gen:list")
    @OperLog(title = "代码生成", businessType = BusinessType.IMPORT)
    @PostMapping("/importTable")
    @ApiOperation(value = "导入表结构（保存）")
    public R importTableSave(String tables)
    {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        String operName = getLoginName();
        genTableService.importGenTable(tableList, operName);
        return R.ok();
    }

    /**
     * 修改保存代码生成业务
     */
    @HasPermissions("tool:gen:edit")
    @OperLog(title = "代码生成", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ApiOperation(value = "改保存代码生成业务")
    public R editSave(@RequestBody @Validated GenTable genTable)
    {
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return R.ok();
    }

    @HasPermissions("tool:gen:remove")
    @OperLog(title = "删除", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ApiOperation(value = "删除")
    public R remove(String ids)
    {
        genTableService.deleteGenTableByIds(ids);
        return R.ok();
    }

    /**
     * 预览代码
     */
    @HasPermissions("tool:gen:preview")
    @GetMapping("/preview/{tableId}")
    @ApiOperation(value = "预览代码")
    public R preview(@PathVariable("tableId") Long tableId) throws IOException
    {
        Map<String, String> dataMap = genTableService.previewCode(tableId);
        return R.ok(dataMap);
    }

    /**
     * 生成代码
     */
    @HasPermissions("tool:gen:code")
    @OperLog(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/genCode/{tableName}")
    @ApiOperation(value = "代码生成")
    public void genCode(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException
    {
        byte[] data = genTableService.generatorCode(tableName);
        genCode(response, data);
    }

    /**
     * 批量生成代码
     */
    @HasPermissions("tool:gen:code")
    @OperLog(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/batchGenCode")
    @ApiOperation(value = "批量生成代码")
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException
    {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableService.generatorCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException
    {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=ruoyi.zip");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}