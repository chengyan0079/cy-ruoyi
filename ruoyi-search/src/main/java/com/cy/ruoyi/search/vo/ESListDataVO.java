package com.cy.ruoyi.search.vo;

import com.cy.ruoyi.search.base.ESDataEntity;
import lombok.Data;

import java.util.List;

@Data
public class ESListDataVO<T> {

    private String indexName;

    private List<ESDataEntity> list;
}
