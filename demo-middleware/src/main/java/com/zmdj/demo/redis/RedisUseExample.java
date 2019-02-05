package com.zmdj.demo.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
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
public class RedisUseExample {

    @Resource
    private StringRedisTemplate redisTemplate;

    public void stringExample(String key, String value) {

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

    public void hashesExample() {

        HashOperations hashOperations = redisTemplate.opsForHash();

        String key = "user:1000";

        // hmset user:1000 username antirez birthyear 1977 verified 1
        hashOperations.putAll(key, new HashMap(){
            {
                put("username", "antirez");
                put("birthyear", 1977);
                put("verified", 1);
            }
        });

        // hget user:1000 username
        String userName = hashOperations.get(key, "username").toString();

        // hget user:1000 username birthyear verified
        List<String> valueList = hashOperations.multiGet(key, new ArrayList(){
            {
                add("username");
                add("birthyear");
                add("verified");
            }
        });

        // hgetall user:1000
        valueList = hashOperations.values(key);

        // hincrby user:1000 birthyear 10
        hashOperations.increment(key, "birthyear", 10);

    }


    public void listExample() {

        ListOperations listOperations = redisTemplate.opsForList();

        String key = "list";

        // lpush list value1
        listOperations.leftPush(key, "value1");
        // lpush list value2 value3
        listOperations.leftPush(key, "value2", "value3");

        // lpush list value4, value5, value6
        listOperations.leftPushAll(key, new String[] {"value4", "value5", "value6"});

        // rpush
        listOperations.rightPush(key, "value7");

        // ... much the same as lpush
        long start = 0;
        long end = 100;
        // lrange list 0 100
        List<String> valueList = listOperations.range(key, start, end);

        // rpop list
        Object value = listOperations.rightPop(key);

        // brpop list 10
        value = listOperations.rightPop(key, 10000L, TimeUnit.MILLISECONDS);

        // lpop list
        value = listOperations.leftPop(key);

        // blpop list 10
        value = listOperations.leftPop(key, 10000L, TimeUnit.MILLISECONDS);

        // rpoplpush list list2
        value = listOperations.rightPopAndLeftPush(key, "list2");

        // brpoplpush list list2 10
        value = listOperations.rightPopAndLeftPush(key, "list2", 10000L, TimeUnit.MILLISECONDS);

        // rpushx list value1
        Long ret = listOperations.rightPushIfPresent(key, "value1");

        // lpushx list value2
        ret = listOperations.leftPushIfPresent(key, "value2");

        // ltrim list 0 5
        listOperations.trim(key, 0, 5);

        // llen list
        listOperations.size(key);

    }

    public void setExample() {

        String key = "myset";

        SetOperations setOperations = redisTemplate.opsForSet();

        // sadd myset value1 value2
        setOperations.add(key, "value1", "value2");

        // smembers myset
        Set<String> valueSet = setOperations.members(key);

        // ismember myset
        boolean ret = setOperations.isMember(key, "value1");

        // sinter myset myset1
        valueSet = setOperations.intersect(key, "myset1");

        // sinterstore myset myset1 myset2
        setOperations.intersectAndStore(key, "myset1", "myset2");

        // spop myset 3
        List<String> valueList = setOperations.pop(key, 3);

        // srandmember myset 3
        valueList = setOperations.randomMembers(key, 3);

    }

}
