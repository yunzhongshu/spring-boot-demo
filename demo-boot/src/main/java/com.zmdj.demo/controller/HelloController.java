package com.zmdj.demo.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

/**
 * @author zhangyunyun
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedissonClient redissonClient;

    @GetMapping("/v1")
    public String helloWorld() {

        logger.info("This is a INFO log.");
        logger.warn("This is a WARN log.");
        logger.error("This is a ERROR log.");
        return "Hello world!";
    }

    @GetMapping("/cache")
    public String testCache() {
        RLock rLock = redissonClient.getLock("lock");
        try {

            if (rLock != null) {
                rLock.tryLock(30, TimeUnit.SECONDS);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.valueOf(rLock);
    }

    @GetMapping("/cache1")
    public String testCache1() {
        ValueOperations valueOperations = stringRedisTemplate.opsForValue();


        Boolean ret = valueOperations.setIfAbsent("lock", String.valueOf(System.currentTimeMillis()), 30, TimeUnit.SECONDS);
//        Boolean ret = valueOperations.setIfAbsent("lock", String.valueOf(System.currentTimeMillis()));
        return String.valueOf(ret);
    }
}
