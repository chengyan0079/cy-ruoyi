package com.cy.ruoyi.common.core.basic.controller;

import com.cy.ruoyi.common.core.util.ServletUtils;
import com.cy.ruoyi.common.core.util.page.PageDomain;
import com.cy.ruoyi.common.core.util.page.PageUtils;
import com.cy.ruoyi.common.core.util.page.TableSupport;
import com.cy.ruoyi.common.utils.constants.Constants;
import com.cy.ruoyi.common.utils.util.DateUtils;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.common.utils.util.SqlUtil;
import com.cy.ruoyi.common.utils.util.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * web层通用数据处理
 * 
 * @author ruoyi
 */
public class BaseController
{
    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 获取request
     */
    public HttpServletRequest getRequest()
    {
        return ServletUtils.getRequest();
    }

    /**
     * 获取response
     */
    public HttpServletResponse getResponse()
    {
        return ServletUtils.getResponse();
    }

    /**
     * 获取session
     */
    public HttpSession getSession()
    {
        return getRequest().getSession();
    }

    public long getCurrentUserId()
    {
        String currentId = getRequest().getHeader(Constants.CURRENT_ID);
        if (StringUtils.isNotBlank(currentId))
        {
            return Long.valueOf(currentId);
        }
        return 0L;
    }

    public String getLoginName()
    {
        return getRequest().getHeader(Constants.CURRENT_USERNAME);
    }

    /**
     * 响应返回结果
     * 
     * @param rows 影响行数
     * @return 操作结果
     */
    protected R toAjax(int rows)
    {
        return rows > 0 ? R.ok() : R.error();
    }

    /**
     * 响应返回结果
     * 
     * @param result 结果
     * @return 操作结果
     */
    protected R toAjax(boolean result)
    {
        return result ? R.ok() : R.error();
    }

    /**
     *  获取分页数据
     * @return
     */
    protected PageDomain getPageInfo(){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (null != pageNum && null != pageSize)
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
        }
        return pageDomain;
    }

    /**
     *  不分页结果封装
     * @param list
     * @return
     */
    protected R result(List<?> list)
    {
        PageUtils pageInfo = new PageUtils(list, list.size());
        return R.ok(pageInfo);
    }

}
