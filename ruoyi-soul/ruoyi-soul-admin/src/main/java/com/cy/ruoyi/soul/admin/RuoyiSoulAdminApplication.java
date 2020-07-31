package com.cy.ruoyi.soul.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RuoyiSoulAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuoyiSoulAdminApplication.class, args);
    }

}
