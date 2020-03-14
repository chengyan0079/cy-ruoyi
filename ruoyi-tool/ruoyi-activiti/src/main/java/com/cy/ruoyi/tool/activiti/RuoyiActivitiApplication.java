package com.cy.ruoyi.tool.activiti;

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

@SpringBootApplication(exclude = org.activiti.spring.boot.SecurityAutoConfiguration.class)
@EnableDiscoveryClient
@EnableCyFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableHystrix
@MapperScan("com.cy.ruoyi.tool.activiti.mapper")
@ComponentScan(basePackages = "com.cy.ruoyi.*")
public class RuoyiActivitiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuoyiActivitiApplication.class, args);
    }

}
