package com.cy.ruoyi.tool.demo;

import com.cy.ruoyi.common.core.annotation.EnableCyFeignClients;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCyFeignClients
@EnableCircuitBreaker
@EnableWebMvc
@EnableHystrixDashboard
@EnableHystrix
@MapperScan("com.cy.ruoyi.*.*.mapper")
@ComponentScan(basePackages = "com.cy.ruoyi.*")
@Configuration
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
