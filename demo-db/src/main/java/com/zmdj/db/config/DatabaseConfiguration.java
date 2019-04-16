package com.zmdj.db.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import io.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;

/**
 * @author zhangyunyun create on 2019/4/15
 */
@Configuration
public class DatabaseConfiguration {

    @Bean
    public DataSource getShardingDataSource() throws SQLException, IOException{

        File file = new File(this.getClass().getResource("/").getFile() + "sharding.yml");
        return YamlShardingDataSourceFactory.createDataSource(file);

    }

}
