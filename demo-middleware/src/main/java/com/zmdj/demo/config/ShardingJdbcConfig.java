package com.zmdj.demo.config;

import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

/**
 * @author zhangyunyun create on 2019/4/11
 */
@Configuration
public class ShardingJdbcConfig {

    private DataSource dataSource1;

    private DataSource datasource2;


    private static KeyGeneratorConfiguration getKeyGeneratorConfiguration() {
        return new KeyGeneratorConfiguration("", "order_id");
    }

    TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("t_order");

        result.setKeyGeneratorConfig(getKeyGeneratorConfiguration());

        return result;
    }


    Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("ds0", dataSource1);
        result.put("ds1", datasource2);
        return result;
    }

}
