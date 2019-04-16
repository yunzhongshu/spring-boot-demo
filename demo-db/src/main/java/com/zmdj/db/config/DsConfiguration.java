//package com.zmdj.db.config;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//
//import javax.sql.DataSource;
//
///**
// * @author zhangyunyun create on 2019/4/15
// */
//@Configuration
//public class DsConfiguration {
//
//    @Bean(name = "dsOneDataSource")
//    @ConfigurationProperties("spring.datasource.druid.one")
//    public DataSource dsOneDataSource() {
//        DataSource dataSource = DruidDataSourceBuilder.create().build();
//        return dataSource;
//    }
//
//    @Bean(name = "dsTwoDataSource")
//    @ConfigurationProperties("spring.datasource.druid.two")
//    public DataSource dsTwoDataSource() {
//        DataSource dataSource = DruidDataSourceBuilder.create().build();
//        return dataSource;
//    }
//}
