package com.zmdj.demo.redis.trans;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Set;

import javax.annotation.Resource;

/**
 * redis 事务 例子
 * @author zhangyunyun create on 2019/2/10
 */
@Service
public class RedisTransactionExample {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    public void testTransaction() {


        stringRedisTemplate.execute(new SessionCallback<Object>() {

            @Nullable
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {

                operations.multi();
                // start transaction

                ValueOperations valueOperations = operations.opsForValue();

                valueOperations.increment("foo");

                valueOperations.increment("bar");

                operations.exec();

                // when error execute  operations.discard();
                return null;
            }
        });


        // implement zpop
        stringRedisTemplate.execute(new SessionCallback<Object>() {

            @Nullable
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                String key = "zset";
                operations.watch(key);

                Set<Object> elements = operations.opsForZSet().range(key, 0, 0);

                operations.multi();
                operations.opsForZSet().remove(key, elements);
                operations.exec();
                return elements;
            }
        });

    }

}
