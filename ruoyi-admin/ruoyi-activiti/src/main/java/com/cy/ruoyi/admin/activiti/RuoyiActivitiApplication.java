package com.cy.ruoyi.admin.activiti;

import com.cy.ruoyi.common.core.annotation.EnableCyFeignClients;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = org.activiti.spring.boot.SecurityAutoConfiguration.class)
@EnableDiscoveryClient
@EnableCyFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableHystrix
@MapperScan("com.cy.ruoyi.admin.activiti.mapper")
@ComponentScan(basePackages = "com.cy.ruoyi.*")
public class RuoyiActivitiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuoyiActivitiApplication.class, args);
    }

}
