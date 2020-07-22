package com.cy.ruoyi.mall.search.vo;

import com.cy.ruoyi.mall.search.base.ESDataEntity;
import lombok.Data;

@Data
public class ESDataVO<T> {

    private String indexName;

    private ESDataEntity dataEntity;
}
