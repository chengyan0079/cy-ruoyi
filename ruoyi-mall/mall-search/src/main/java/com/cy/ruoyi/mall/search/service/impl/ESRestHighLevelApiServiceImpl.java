package com.cy.ruoyi.mall.search.service.impl;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import com.cy.ruoyi.common.core.exception.BusinessException;
import com.cy.ruoyi.common.utils.enums.TradeErrorEnum;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import com.cy.ruoyi.mall.search.base.ESDataEntity;
import com.cy.ruoyi.mall.search.service.ESRestHighLevelApiService;
import com.cy.ruoyi.mall.search.utils.ElasticUtil;
import com.cy.ruoyi.mall.search.vo.ESQueryVO;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *  ES Client客户端  version:7.6.0
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.ESRestHighLevelApiService.version}")
public class ESRestHighLevelApiServiceImpl implements ESRestHighLevelApiService {

    private static final Log log = LogFactory.get();

    private final Integer INDEX_NUMBER_OF_SHARDS = 3;

    private final Integer INDEX_NUMBER_OF_REPLICAS = 2;

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    /**
     *  创建索引
     * @param indexName
     * @return
     * @throws IOException
     */
    @Override
    public boolean createIndex(String indexName) throws IOException {
        if (RegexUtil.isNull(indexName)) {
            log.error(TradeErrorEnum.SEARCH_INDEX_NULL.msg);
            new BusinessException(TradeErrorEnum.SEARCH_INDEX_NULL);
        }
        if (existIndex(indexName)) {
            log.error(TradeErrorEnum.SEARCH_CREATE_INDEX_ERROR.msg);
            new BusinessException(TradeErrorEnum.SEARCH_CREATE_INDEX_ERROR);
        }
        // 1、创建索引请求
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        // 设置分片
//        buildSetting(request, INDEX_NUMBER_OF_SHARDS, INDEX_NUMBER_OF_REPLICAS);
        // 2、客户端执行请求 IndicesClient,请求后获得响应
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        return createIndexResponse.isAcknowledged();
    }

    /**
     *  设置分片
     * @param request
     * @param shards
     * @param replicas
     */
    private void buildSetting(CreateIndexRequest request, int shards, int replicas){
        request.settings(Settings.builder().put("index.number_of_shards", shards)
                .put("index.number_of_replicas", replicas));
    }

    /**
     *  判断是否存在该索引
     * @param indexName
     * @return
     * @throws IOException
     */
    @Override
    public boolean existIndex(String indexName) throws IOException {
        if (RegexUtil.isNull(indexName)) {
            log.error(TradeErrorEnum.SEARCH_INDEX_NULL.msg);
            new BusinessException(TradeErrorEnum.SEARCH_INDEX_NULL);
        }

        GetIndexRequest request = new GetIndexRequest(indexName);
        return client.indices().exists(request, RequestOptions.DEFAULT);
    }

    /**
     *  删除索引
     * @param indexName
     * @return
     * @throws IOException
     */
    @Override
    public boolean deleteIndex(String indexName) throws IOException {
        if(RegexUtil.isNull(indexName)){
            log.error(TradeErrorEnum.SEARCH_INDEX_NULL.msg);
            new BusinessException(TradeErrorEnum.SEARCH_INDEX_NULL);
        }
        if (!existIndex(indexName)) {
            log.error(TradeErrorEnum.SEARCH_DELETE_INDEX_ERROR.msg);
            new BusinessException(TradeErrorEnum.SEARCH_DELETE_INDEX_ERROR);
        }

        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        // 删除
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
        return delete.isAcknowledged();
    }

    /**
     *  添加文档，如果id不变则更改
     * @throws IOException
     */
    @Override
    public RestStatus addDoc(String indexName, ESDataEntity data) throws IOException {
        if(RegexUtil.isNull(indexName) || RegexUtil.isNull(data.getId()) || RegexUtil.isNull(data.getData())){
            log.error(TradeErrorEnum.SEARCH_PARAMS_NULL.msg);
            new BusinessException(TradeErrorEnum.SEARCH_PARAMS_NULL);
        }
        if (!existIndex(indexName)){
            log.error(TradeErrorEnum.SEARCH_CREATE_DOC_ERROR.msg);
            new BusinessException(TradeErrorEnum.SEARCH_CREATE_DOC_ERROR);
        }

        // 创建请求
        IndexRequest request = new IndexRequest(indexName);
        // 规则 put /{index}/_doc/{id}
        request.id(data.getId());
        request.timeout(TimeValue.timeValueSeconds(1));
        // 将我们的数据放入请求 json
        request.source(data.getData(), XContentType.JSON);
        // 客户端发送请求 , 获取响应的结果
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        return indexResponse.status(); // 对应我们命令返回的状态 CREATED
    }

    /**
     * 批量添加文档
     * @param indexName
     * @param list
     * @return
     * @throws IOException
     */
    @Override
    public RestStatus addDocBatch(String indexName, List<ESDataEntity> list) throws IOException{
        if(RegexUtil.isNull(indexName) || RegexUtil.isNull(list)){
            log.error(TradeErrorEnum.SEARCH_PARAMS_NULL.msg);
            new BusinessException(TradeErrorEnum.SEARCH_PARAMS_NULL);
        }

        BulkRequest request = new BulkRequest();
        list.forEach(item -> request.add(new IndexRequest(indexName).id(item.getId())
                .source(item.getData(), XContentType.JSON)));
        BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
        return responses.status();
    }


    /**
     *  判断文档是否存在
     * @param indexName
     * @param data
     * @return
     * @throws IOException
     */
    @Override
    public boolean existDoc(String indexName, ESDataEntity data) throws IOException {
        if(RegexUtil.isNull(indexName) || RegexUtil.isNull(data.getId())){
            log.error(TradeErrorEnum.SEARCH_PARAMS_NULL.msg);
            new BusinessException(TradeErrorEnum.SEARCH_PARAMS_NULL);
        }

        GetRequest getRequest = new GetRequest(indexName, data.getId());
        // 不获取返回的 _source 的上下文了
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        return client.exists(getRequest, RequestOptions.DEFAULT);
    }

    /**
     *  获取文档信息
     * @param indexName
     * @param data
     * @return
     * @throws IOException
     */
    @Override
    public String getDoc(String indexName, ESDataEntity data) throws IOException {
        if(RegexUtil.isNull(indexName) || RegexUtil.isNull(data.getId())){
            log.error(TradeErrorEnum.SEARCH_PARAMS_NULL.msg);
            new BusinessException(TradeErrorEnum.SEARCH_PARAMS_NULL);
        }

        GetRequest getRequest = new GetRequest(indexName, data.getId());
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        return getResponse.getSourceAsString();// 打印文档的内容
    }

    /**
     *  更新文档的信息
     * @throws IOException
     */
    @Override
    public RestStatus updateDoc(String indexName, ESDataEntity data) throws IOException {
        if(RegexUtil.isNull(indexName) || RegexUtil.isNull(data.getId()) || RegexUtil.isNull(data.getData())){
            log.error(TradeErrorEnum.SEARCH_PARAMS_NULL.msg);
            new BusinessException(TradeErrorEnum.SEARCH_PARAMS_NULL);
        }

        UpdateRequest updateRequest = new UpdateRequest(indexName, data.getId());
        updateRequest.timeout("1s");
        updateRequest.doc(data.getData(), XContentType.JSON);
        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
        return updateResponse.status();
    }


    /**
     *  删除文档记录
     * @param indexName
     * @param data
     * @throws IOException
     */
    @Override
    public RestStatus deleteDoc(String indexName, ESDataEntity data) throws IOException {
        if(RegexUtil.isNull(indexName) || RegexUtil.isNull(data.getId())){
            log.error(TradeErrorEnum.SEARCH_PARAMS_NULL.msg);
            new BusinessException(TradeErrorEnum.SEARCH_PARAMS_NULL);
        }
        if(!existIndex(indexName)){
            log.error(TradeErrorEnum.SEARCH_DELETE_INDEX_ERROR.msg);
            new BusinessException(TradeErrorEnum.SEARCH_DELETE_INDEX_ERROR);
        }

        DeleteRequest request = new DeleteRequest(indexName, data.getId());
        request.timeout("1s");
        DeleteResponse deleteResponse = client.delete(request, RequestOptions.DEFAULT);
        return deleteResponse.status();
    }

    /**
     *  查询
     * @throws IOException
     *  // SearchRequest 搜索请求
     * // SearchSourceBuilder 条件构造
     * // HighlightBuilder 构建高亮
     * // TermQueryBuilder 精确查询
     * // MatchAllQueryBuilder
     * // xxx QueryBuilder 对应我们刚才看到的命令！
     */
    @Override
    public <T> List<T> search(ESQueryVO vo) throws IOException {
        if(RegexUtil.isNull(vo)){
            log.error(TradeErrorEnum.SEARCH_PARAMS_NULL.msg);
            new BusinessException(TradeErrorEnum.SEARCH_PARAMS_NULL);
        }
        SearchRequest searchRequest = new SearchRequest(vo.getIndexName());

        Class<?> clazz = ElasticUtil.getClazz(vo.getClassName());
        Map<String, Object> params = vo.getQuery().get("match");
        Set<String> keys = params.keySet();
        MatchQueryBuilder queryBuilder = null;
        for(String kys : keys){
            queryBuilder = QueryBuilders.matchQuery(kys, params.get(kys));
        }
        if(RegexUtil.isNull(queryBuilder)) {
            log.error(TradeErrorEnum.SEARCH_QUERY_PARAMS_NULL.msg);
            new BusinessException(TradeErrorEnum.SEARCH_QUERY_PARAMS_NULL);
        }
        SearchSourceBuilder searchSourceBuilder = ElasticUtil.initSearchSourceBuilder(queryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = searchResponse.getHits().getHits();
        List<T> res = new ArrayList<>(hits.length);
        for (SearchHit documentFields : hits) {
            res.add(JSON.parseObject(documentFields.getSourceAsString(), (Type) clazz));
        }
        return res;
    }

}
