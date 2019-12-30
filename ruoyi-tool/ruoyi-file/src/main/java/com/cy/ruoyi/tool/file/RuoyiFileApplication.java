package com.cy.ruoyi.tool.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RuoyiFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuoyiFileApplication.class, args);
    }

}
