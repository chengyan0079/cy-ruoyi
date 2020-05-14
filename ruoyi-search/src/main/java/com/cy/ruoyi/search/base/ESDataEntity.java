package com.cy.ruoyi.search.base;

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
