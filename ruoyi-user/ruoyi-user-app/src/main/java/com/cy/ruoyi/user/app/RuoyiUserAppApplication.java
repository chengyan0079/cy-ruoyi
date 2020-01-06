package com.cy.ruoyi.user.app;

import com.cy.ruoyi.common.core.annotation.EnableCyFeignClients;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCyFeignClients
@MapperScan("com.cy.ruoyi.user.*.mapper")
public class RuoyiUserAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuoyiUserAppApplication.class, args);
    }

}
