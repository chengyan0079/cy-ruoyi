package com.cy.ruoyi.common.core.util;

import lombok.Data;

@Data
public class PageParam {
    /**
     * 当前页
     */
    private Integer currPage = 1;
    /**
     * 当页查询数量
     */
    private Integer pageSize = 10;
    /**
     * 排序条件
     */
    private String cons;
    /**
     * 顺序
     */
    private String order;

}
