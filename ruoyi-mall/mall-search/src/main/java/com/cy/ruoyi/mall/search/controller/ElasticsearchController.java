package com.cy.ruoyi.mall.search.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cy.ruoyi.common.utils.enums.TradeErrorEnum;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.common.utils.util.RegexUtil;
import com.cy.ruoyi.mall.search.base.ESDataEntity;
import com.cy.ruoyi.mall.search.base.ESDataEntity;
import com.cy.ruoyi.mall.search.service.ESRestHighLevelApiService;
import com.cy.ruoyi.mall.search.vo.ESDataVO;
import com.cy.ruoyi.mall.search.vo.ESListDataVO;
import com.cy.ruoyi.mall.search.vo.ESQueryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.elasticsearch.rest.RestStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("search")
@Api(value = "ElasticsearchController",description = "ES测试")
public class ElasticsearchController {

    private static final Log log = LogFactory.get();

    @Reference(validation = "true", version = "${dubbo.provider.ESRestHighLevelApiService.version}")
    private ESRestHighLevelApiService restHighLevelApiService;

    @PostMapping("/createIndex/{indexName}")
    @ApiOperation(value = "创建索引")
//    @Transactional
//    @SentinelResource("/echo/{msg}")
    public R createIndex(@PathVariable String indexName)
    {
        try {
            if(restHighLevelApiService.createIndex(indexName)){
                return R.ok("创建成功！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.error(TradeErrorEnum.SEARCH_CREATE_FAIL);
    }

    @PostMapping("/deleteIndex/{indexName}")
    @ApiOperation(value = "删除索引")
//    @Transactional
//    @SentinelResource("/echo/{msg}")
    public R deleteIndex(@PathVariable String indexName)
    {
        try {
            if(restHighLevelApiService.deleteIndex(indexName)){
                return R.ok("删除索引成功！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.error(TradeErrorEnum.SEARCH_DELETE_INDEX_FAIL);
    }

    @GetMapping("/existIndex/{indexName}")
    @ApiOperation(value = "查询是否存在索引")
//    @Transactional
    //    @SentinelResource("/echo/{msg}")
    public R existIndex(@PathVariable String indexName){
        try {
            return restHighLevelApiService.existIndex(indexName) == true ? R.ok("该索引已存在！") : R.ok("该索引不存在！");
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.error(TradeErrorEnum.SEARCH_QUERY_FAIL);
    }

    @PostMapping("/addDoc")
    @ApiOperation(value = "添加文档")
//    @Transactional
//    @SentinelResource("/echo/{msg}")
    public R addDoc(@RequestBody ESDataVO vo){
        if(RegexUtil.isNull(vo)){
            return R.error(TradeErrorEnum.SEARCH_PARAMS_NULL);
        }
        ESDataEntity entity = new ESDataEntity();
        entity.setId(vo.getDataEntity().getId());
        entity.setData(vo.getDataEntity().getData());

        try {
            RestStatus restStatus = restHighLevelApiService.addDoc(vo.getIndexName(), entity);
            return R.ok(restStatus, "添加文档成功！");
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.error(TradeErrorEnum.SEARCH_CREATE_DOC_FAIL);
    }

    @PostMapping("/getDoc")
    @ApiOperation(value = "按id查询文档")
//    @Transactional
    public R getDoc(@RequestBody ESDataVO vo){
        try {
            if(RegexUtil.isNull(vo)){
                return R.error(TradeErrorEnum.SEARCH_PARAMS_NULL);
            }
            ESDataEntity entity = new ESDataEntity();
            entity.setId(vo.getDataEntity().getId());

            String result = restHighLevelApiService.getDoc(vo.getIndexName(), entity);
            return R.ok(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.error(TradeErrorEnum.SEARCH_QUERY_FAIL);
    }


    @PostMapping("/deleteDoc")
    @ApiOperation(value = "按id删除文档")
//    @Transactional
    //    @SentinelResource("/echo/{msg}")
    public R deleteDoc(@RequestBody ESDataVO vo){
        try {
            RestStatus restStatus = restHighLevelApiService.deleteDoc(vo.getIndexName(), vo.getDataEntity());
            return R.ok(restStatus, "删除文档成功！");
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.error(TradeErrorEnum.SEARCH_DELETE_DOC_FAIL);
    }


    @PostMapping("/addDocBatch")
    @ApiOperation(value = "批量添加文档")
//    @Transactional
    //    @SentinelResource("/echo/{msg}")
    public R addDocBatch(@RequestBody ESListDataVO vo){
        try{
            RestStatus restStatus = restHighLevelApiService.addDocBatch(vo.getIndexName(), vo.getList());
            return R.ok(restStatus, "批量添加文档成功！");
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.error(TradeErrorEnum.SEARCH_BATCH_CREATE_DOC_FAIL);
    }

    @PostMapping("/search")
    @ApiOperation(value = "按条件查询文档")
//    @Transactional
    public R search(@RequestBody ESQueryVO vo){
        try {
            List<?> data = restHighLevelApiService.search(vo);
            return R.ok(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  R.error(TradeErrorEnum.SEARCH_QUERY_FAIL);
    }

}
