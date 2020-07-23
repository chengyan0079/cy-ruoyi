package com.cy.ruoyi.common.sql.page;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.ruoyi.common.utils.util.SqlUtil;
import lombok.Data;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 */
@Data
public class Query<T> extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
    /**
     * mybatis-plus分页参数
     */
    private Page<T> page;
    /**
     * 当前页码
     */
    private int pageNum = 1;
    /**
     * 每页条数
     */
    private int pageSize = 2;

    public Query(Map<String, Object> params){
        this.putAll(params);
        //分页参数
        if(params.get("pageNum") != null){
            pageNum = Integer.parseInt((String)params.get("pageNum"));
        }
        if(params.get("pageSize") != null){
            pageSize = Integer.parseInt((String)params.get("pageSize"));
        }

        this.put("offset", (pageNum - 1) * pageSize);
        this.put("pageNum", pageNum);
        this.put("pageSize", pageSize);

        //mybatis-plus分页
        this.page = new Page<>(pageNum, pageSize);
        //mybatis-plus排序
        String orderByColumn = SqlUtil.sqlInject((String) params.get("orderByColumn"));
        String isAsc = SqlUtil.sqlInject((String) params.get("isAsc"));
        if (StrUtil.isNotBlank(orderByColumn) && StrUtil.isNotBlank(isAsc)){
            if ("ASC".equalsIgnoreCase(isAsc)){
                this.page.setAscs(Arrays.asList(orderByColumn.split(",")));
            }
            if ("DESC".equalsIgnoreCase(isAsc)){
                this.page.setDescs(Arrays.asList(orderByColumn.split(",")));
            }
        }
    }


    public Query(PageDomain pageDomain){

        //分页参数
        if(pageDomain.getPageNum() != null){
            pageNum = pageDomain.getPageNum();
            this.put("pageNum",pageDomain.getPageNum());
        }
        if(pageDomain.getPageSize() != null){
            pageSize = pageDomain.getPageSize();
            this.put("pageSize",pageDomain.getPageSize());
        }
        this.put("offset", (pageNum - 1) * pageSize);
        this.put("pageNum", pageNum);
        this.put("pageSize", pageSize);
        //mybatis-plus分页
        this.page = new Page<>(pageNum, pageSize);

        String orderByColumn = SqlUtil.sqlInject((String)pageDomain.getOrderByColumn());
        String isAsc = SqlUtil.sqlInject((String)pageDomain.getIsAsc());
        this.put("orderByColumn", orderByColumn);
        this.put("isAsc", "ASC".equalsIgnoreCase(isAsc));
        //排序
        if (StrUtil.isNotBlank(orderByColumn) && StrUtil.isNotBlank(isAsc)){
            if ("ASC".equalsIgnoreCase(isAsc)){
                this.page.setAscs(Arrays.asList(orderByColumn.split(",")));
            }
            if ("DESC".equalsIgnoreCase(isAsc)){
                this.page.setDescs(Arrays.asList(orderByColumn.split(",")));
            }
        }
    }

}
