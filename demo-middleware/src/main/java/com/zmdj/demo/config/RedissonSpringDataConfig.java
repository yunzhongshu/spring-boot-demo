package com.zmdj.demo.config;

import org.redisson.api.RedissonClient;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangyunyun create on 2019/2/14
 */
@Configuration
public class RedissonSpringDataConfig {

    public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redissonClient) {
        return new RedissonConnectionFactory(redissonClient);
    }

}
