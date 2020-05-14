package com.cy.ruoyi.search.vo;

import com.cy.ruoyi.search.base.ESDataEntity;
import lombok.Data;

@Data
public class ESDataVO<T> {

    private String indexName;

    private ESDataEntity dataEntity;
}
