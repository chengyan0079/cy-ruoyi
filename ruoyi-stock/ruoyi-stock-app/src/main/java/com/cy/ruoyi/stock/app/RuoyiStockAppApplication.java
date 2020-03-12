package com.cy.ruoyi.stock.app;

import com.cy.ruoyi.common.core.annotation.EnableCyFeignClients;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCyFeignClients
@EnableCircuitBreaker
@EnableWebMvc
@EnableHystrixDashboard
@EnableHystrix
@MapperScan("com.cy.ruoyi.stock.*.mapper")
@ComponentScan(basePackages = "com.cy.ruoyi.*")
public class RuoyiStockAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuoyiStockAppApplication.class, args);
    }

}
