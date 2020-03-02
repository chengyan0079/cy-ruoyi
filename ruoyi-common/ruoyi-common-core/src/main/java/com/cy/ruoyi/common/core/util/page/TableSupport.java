package com.cy.ruoyi.common.core.util.page;


import com.cy.ruoyi.common.core.util.ServletUtils;
import com.cy.ruoyi.common.utils.constants.Constants;
import com.cy.ruoyi.common.utils.util.RegexUtil;

/**
 * 表格数据处理
 * 
 * @author ruoyi
 */
public class TableSupport
{
    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain()
    {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(RegexUtil.isNull(ServletUtils.getParameterToInt(Constants.PAGE_NUM)) == true ? 1 : ServletUtils.getParameterToInt(Constants.PAGE_NUM));
        pageDomain.setPageSize(RegexUtil.isNull(ServletUtils.getParameterToInt(Constants.PAGE_SIZE)) == true ? 10 : ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest()
    {
        return getPageDomain();
    }
}
