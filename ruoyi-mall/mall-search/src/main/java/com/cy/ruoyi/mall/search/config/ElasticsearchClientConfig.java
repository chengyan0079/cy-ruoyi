package com.cy.ruoyi.mall.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchClientConfig {

    @Autowired
    ESHostConstants esHostConstants;

    @Bean
    public RestHighLevelClient restHighLevelClient(){

        RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                new HttpHost(esHostConstants.getHost(), esHostConstants.getPort(), esHostConstants.getSchme())
            )
        );
        return client;
    }
}
