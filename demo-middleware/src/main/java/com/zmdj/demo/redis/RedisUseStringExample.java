package com.zmdj.demo.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

/**
 * \n
 * \n
 * \n
 *
 * @author zhangyunyun create on 2019/2/3
 */
@Service
public class RedisUseStringExample {

    @Resource
    private StringRedisTemplate redisTemplate;

    public void examples(String key, String value) {

        // set key value
        redisTemplate.opsForValue().set(key, value);

        // set key value px 10000

        redisTemplate.opsForValue().set(key, value, 10000L, TimeUnit.MILLISECONDS);

        // set key value ex 10
        redisTemplate.opsForValue().set(key, value, 10, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(10));

        // set key value nx
        redisTemplate.opsForValue().setIfAbsent(key, value);

        // set key value nx ex 10
        redisTemplate.opsForValue().setIfAbsent(key, value, 10, TimeUnit.SECONDS);
        redisTemplate.opsForValue().setIfAbsent(key, value, Duration.ofSeconds(10));

        // set key value xx
        redisTemplate.opsForValue().setIfPresent(key, value);

        // set key value xx ex 10
        redisTemplate.opsForValue().setIfPresent(key, value, 10, TimeUnit.SECONDS);
        redisTemplate.opsForValue().setIfPresent(key, value, Duration.ofSeconds(10));

        // get key
        redisTemplate.opsForValue().get(key);

        // incr key
        redisTemplate.opsForValue().increment(key);

        // incrby key 2
        redisTemplate.opsForValue().increment(key, 2);

        // decr key
        redisTemplate.opsForValue().decrement(key);

        // decr key 2
        redisTemplate.opsForValue().decrement(key, 2);

        // getset key value; set a key to a newvalue, returning the old value as the result.
        String oldValue = redisTemplate.opsForValue().getAndSet(key, value);

        // mset key1 value1 key2 value2
        String key2 = "key2";
        String value2 = "value2";
        redisTemplate.opsForValue().multiSet(new HashMap<String, String>(){
            {
                put(key, value);
                put(key2, value2);
            }
        });

        // mget key1 key2
        redisTemplate.opsForValue().multiGet(new ArrayList<String>(){
            {
                add(key);
                add(key2);
            }
        });

        // del key
        redisTemplate.delete(key);

        // type key
        redisTemplate.type(key);

        // expire key 10
        redisTemplate.expire(key, 10, TimeUnit.SECONDS);

        // persist key
        redisTemplate.persist(key);

    }


}
