package com.cy.ruoyi.demo.provider.app.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis-plus配置
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.cy.ruoyi.demo.provider.*.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor().setCountSqlParser(new JsqlParserCountOptimize(true));
    }

//    /**
//     * 自定义SQL注入器
//     */
//    @Bean
//    public LogicSqlInjector logicSqlInjector(){
//        return new LogicSqlInjector();
//    }

//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer(){
//        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
//        //可以通过环境变量获取你的mapper路径,这样mapper扫描可以通过配置文件配置了
//        scannerConfigurer.setBasePackage("com.cy.user.*.dao");
//        return scannerConfigurer;
//    }

//    /**
//     * 添加OracleJDBC的索引配置
//     * @return
//     */
//    @Bean
//    public OracleKeyGenerator oracleKeyGenerator(){
//        return new OracleKeyGenerator();
//    }

}
