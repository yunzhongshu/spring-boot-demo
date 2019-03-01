package com.zmdj.demo;

import com.zmdj.demo.config.RedissonSpringDataConfig;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@ContextConfiguration(
		initializers = YamlFileApplicationContextInitializer.class,
		classes = {
				RedissonSpringDataConfig.class,
				RedisAutoConfiguration.class
		}
)
@SpringBootTest
public abstract class DemoMiddlewareApplicationTests {

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@Resource
	private RedissonClient redissonClient;


}

