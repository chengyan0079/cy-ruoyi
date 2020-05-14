package com.cy.ruoyi.search.vo;

import lombok.Data;

import java.util.Map;

@Data
public class ESQueryVO {

    private String indexName;

    // 返回结果实体类
    private String className;

    private Map<String, Map<String, Object>> query;

}
