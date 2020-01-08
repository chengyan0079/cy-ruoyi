package com.cy.ruoyi.common.utils.constants;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * SwaggerConstants
 * @author chengyan
 * @date 2018/11/27 17:15
 */
@Component
@ConfigurationProperties(prefix = "swagger")
@Data
public class SwaggerConstants {

    @Value("${swagger.service.basePackage}")
    private String basePackage;

    @Value("${swagger.service.title}")
    private String title;

    @Value("${swagger.service.description}")
    private String description;

    @Value("${swagger.service.version}")
    private String version;


}
