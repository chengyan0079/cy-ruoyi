package com.cy.ruoyi.mall.search.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ESHostConstants
 */
@Component
@ConfigurationProperties(prefix = "es")
@Data
public class ESHostConstants {

    @Value("${es.schme}")
    private String schme;

    @Value("${es.node.host}")
    private String host;

    @Value("${es.node.port}")
    private int port;

}
