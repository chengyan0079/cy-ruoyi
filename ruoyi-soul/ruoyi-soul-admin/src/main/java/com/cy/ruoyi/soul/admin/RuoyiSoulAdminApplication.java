package com.cy.ruoyi.soul.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.cy.ruoyi.soul.admin.mapper")
@ComponentScan(basePackages = "com.cy.ruoyi.soul.admin.*")
public class RuoyiSoulAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuoyiSoulAdminApplication.class, args);
    }

}
