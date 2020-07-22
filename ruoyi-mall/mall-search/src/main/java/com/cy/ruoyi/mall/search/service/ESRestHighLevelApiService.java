package com.cy.ruoyi.mall.search.service;

import com.cy.ruoyi.mall.search.base.ESDataEntity;
import com.cy.ruoyi.mall.search.vo.ESQueryVO;
import org.elasticsearch.rest.RestStatus;

import java.io.IOException;
import java.util.List;

public interface ESRestHighLevelApiService {

    boolean createIndex(String indexName) throws IOException;

    boolean existIndex(String indexName) throws IOException;

    boolean deleteIndex(String indexName) throws IOException;

    RestStatus addDoc(String indexName, ESDataEntity data) throws IOException;

    RestStatus addDocBatch(String indexName, List<ESDataEntity> list) throws IOException;

    boolean existDoc(String indexName, ESDataEntity data) throws IOException;

    String getDoc(String indexName, ESDataEntity data) throws IOException;

    RestStatus updateDoc(String indexName, ESDataEntity data) throws IOException;

    RestStatus deleteDoc(String indexName, ESDataEntity data) throws IOException;

    <T> List<T> search(ESQueryVO vo) throws IOException;

}
