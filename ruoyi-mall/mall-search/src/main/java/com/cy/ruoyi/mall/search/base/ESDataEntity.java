package com.cy.ruoyi.mall.search.base;

import lombok.Data;

import java.util.Map;

/**
 *  ES 数据存储对象
 */
@Data
public class ESDataEntity<T> {

    private String id;

    private Map data;
}
