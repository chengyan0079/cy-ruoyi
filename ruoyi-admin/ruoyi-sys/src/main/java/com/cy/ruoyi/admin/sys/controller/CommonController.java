package com.cy.ruoyi.admin.sys.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.core.exception.BusinessException;
import com.cy.ruoyi.common.utils.enums.TradeErrorEnum;
import com.cy.ruoyi.common.utils.util.DateUtils;
import com.cy.ruoyi.common.utils.util.ToolUtil;
import com.cy.ruoyi.common.utils.util.file.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dromara.soul.client.common.annotation.SoulClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用请求处理
 */
@RestController
@Api(value = "CommonController",description = "通用请求处理")
public class CommonController extends BaseController
{
    private static final Log log = LogFactory.get();

    /**
     * 通用下载请求
     * 
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("common/download")
    @ApiOperation(value = "通用下载请求")
    @SentinelResource("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.isValidFilename(fileName))
            {
                throw new BusinessException(TradeErrorEnum.USER_FILENAME_ERROR.msg + ": 文件名：" + fileName);
            }
            String realFileName = DateUtils.dateTimeNow() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = ToolUtil.getDownloadPath() + fileName;
            response.setCharacterEncoding("utf-8");
            // 下载使用"application/octet-stream"更标准
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error(TradeErrorEnum.USER_FILE_DOWNLOAD_FAIL.msg, e);
        }
    }
}