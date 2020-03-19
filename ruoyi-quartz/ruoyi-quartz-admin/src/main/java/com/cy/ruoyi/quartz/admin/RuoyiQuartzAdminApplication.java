package com.cy.ruoyi.quartz.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.cy.ruoyi.quartz.admin.dao")
@ComponentScan(basePackages = "com.cy.ruoyi.*")
public class RuoyiQuartzAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuoyiQuartzAdminApplication.class, args);
    }

}
