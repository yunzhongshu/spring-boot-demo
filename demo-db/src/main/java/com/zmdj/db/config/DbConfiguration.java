//package com.zmdj.db.config;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.context.annotation.Primary;
//
//import java.sql.SQLException;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
//import javax.sql.DataSource;
//
//import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
//import io.shardingsphere.api.config.rule.TableRuleConfiguration;
//import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
//import io.shardingsphere.core.keygen.DefaultKeyGenerator;
//import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
//
///**
// * @author zhangyunyun create on 2019/4/14
// */
//@Configuration
//@Import({DsConfiguration.class})
//public class DbConfiguration {
//
//    @Autowired
//    @Qualifier("dsOneDataSource")
//    private DataSource dsOneDataSource;
//
//    @Autowired
//    @Qualifier("dsTwoDataSource")
//    private DataSource dsTwoDataSource;
//
//    @Bean
//    @Primary
//    DataSource getShardingDatasource() throws SQLException {
//        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
//        shardingRuleConfiguration.setBindingTableGroups(Arrays.asList("t_order"));
//        shardingRuleConfiguration.getTableRuleConfigs().add(getOrderTableRuleConfig());
//        shardingRuleConfiguration.setDefaultDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
//        shardingRuleConfiguration.setDefaultTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("id", "t_order${id % 2}"));
//        Map<String, Object> configMap = new HashMap<>();
//        return ShardingDataSourceFactory.createDataSource(createDataSource(), shardingRuleConfiguration, configMap, null);
//    }
//
//    TableRuleConfiguration getOrderTableRuleConfig() {
//        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
//        orderTableRuleConfig.setLogicTable("t_order");
//        orderTableRuleConfig.setActualDataNodes("ds${0..1}.t_order${0..1}");
//        orderTableRuleConfig.setKeyGeneratorColumnName("id");
//        orderTableRuleConfig.setKeyGenerator(new DefaultKeyGenerator());
//        return orderTableRuleConfig;
//    }
//
//
//    Map<String, DataSource> createDataSource() {
//        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
//        dataSourceMap.put("ds0", dsOneDataSource);
//        dataSourceMap.put("ds1", dsTwoDataSource);
//        return dataSourceMap;
//    }
//
//}
