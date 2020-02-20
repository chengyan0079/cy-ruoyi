package com.cy.ruoyi.common.utils.util;

/**
 * sql操作工具类
 * 
 * @author ruoyi
 */
public class SqlUtil{
    /**
     * 仅支持字母、数字、下划线、空格、逗号（支持多个字段排序）
     */
    public static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,]+";

    /**
     * 检查字符，防止注入绕过
     */
    public static String escapeOrderBySql(String value)
    {
        if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value))
        {
            return StringUtils.EMPTY;
        }
        return value;
    }

    /**
     * 验证 order by 语法是否符合规范
     */
    public static boolean isValidOrderBySql(String value)
    {
        return value.matches(SQL_PATTERN);
    }


    /**
     * SQL注入过滤
     * @param str  待验证的字符串
     */
    public static String sqlInject(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};

        //判断是否包含非法字符
        for(String keyword : keywords){
            if(str.indexOf(keyword) != -1){
                throw null;
            }
        }
        return str;
    }
}
