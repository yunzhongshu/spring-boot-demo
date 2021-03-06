package com.zmdj.demo.redis.datatypes;

import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.HyperLogLogOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
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
public class RedisDataTypesExample {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void stringExample(String key, String value) {

        // set key value
        stringRedisTemplate.opsForValue().set(key, value);

        // set key value px 10000

        stringRedisTemplate.opsForValue().set(key, value, 10000L, TimeUnit.MILLISECONDS);

        // set key value ex 10
        stringRedisTemplate.opsForValue().set(key, value, 10, TimeUnit.SECONDS);
        stringRedisTemplate.opsForValue().set(key, value, Duration.ofSeconds(10));

        // set key value nx
        stringRedisTemplate.opsForValue().setIfAbsent(key, value);

        // set key value nx ex 10
        stringRedisTemplate.opsForValue().setIfAbsent(key, value, 10, TimeUnit.SECONDS);
        stringRedisTemplate.opsForValue().setIfAbsent(key, value, Duration.ofSeconds(10));

        // set key value xx
        stringRedisTemplate.opsForValue().setIfPresent(key, value);

        // set key value xx ex 10
        stringRedisTemplate.opsForValue().setIfPresent(key, value, 10, TimeUnit.SECONDS);
        stringRedisTemplate.opsForValue().setIfPresent(key, value, Duration.ofSeconds(10));

        // get key
        stringRedisTemplate.opsForValue().get(key);

        // incr key
        stringRedisTemplate.opsForValue().increment(key);

        // incrby key 2
        stringRedisTemplate.opsForValue().increment(key, 2);

        // decr key
        stringRedisTemplate.opsForValue().decrement(key);

        // decr key 2
        stringRedisTemplate.opsForValue().decrement(key, 2);

        // getset key value; set a key to a newvalue, returning the old value as the result.
        String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value);

        // mset key1 value1 key2 value2
        String key2 = "key2";
        String value2 = "value2";
        stringRedisTemplate.opsForValue().multiSet(new HashMap<String, String>(){
            {
                put(key, value);
                put(key2, value2);
            }
        });

        // mget key1 key2
        stringRedisTemplate.opsForValue().multiGet(new ArrayList<String>(){
            {
                add(key);
                add(key2);
            }
        });

        // del key
        stringRedisTemplate.delete(key);

        // type key
        stringRedisTemplate.type(key);

        // expire key 10
        stringRedisTemplate.expire(key, 10, TimeUnit.SECONDS);

        // persist key
        stringRedisTemplate.persist(key);

    }

    public void hashesExample() {

        HashOperations hashOperations = stringRedisTemplate.opsForHash();

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

        ListOperations listOperations = stringRedisTemplate.opsForList();

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

        SetOperations setOperations = stringRedisTemplate.opsForSet();

        // sadd myset value1 value2
        setOperations.add(key, "value1", "value2");

        // scard myset
        setOperations.size(key);

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


    public void sortedSetExample() {
        String key = "myzset";

        ZSetOperations zSetOperations = stringRedisTemplate.opsForZSet();

        // zadd myzset value1 10001
        boolean ret = zSetOperations.add(key, "value1", 1001d);

        // zrange myzset 0 5
        Set<String> valueSet = zSetOperations.range(key, 0, 5);

        // zrangebyscore myzset 1001 1004
        valueSet = zSetOperations.rangeByScore(key, 1001d, 1004d);

        // zrangebyscore myzset 1001 1004 withscores
        Set<ZSetOperations.TypedTuple> typedTupleSet = zSetOperations.rangeByScoreWithScores(key, 1001d, 1004d);

        // zrevrange myzset 0 5
        zSetOperations.reverseRange(key, 0, 5);

        // zrangebylex myzset (A E
        zSetOperations.rangeByLex(key, new RedisZSetCommands.Range().gt("A").lte("E"));

        // zrank myzset value1
        long rank = zSetOperations.rank(key, "value1");

        // zrevrank myzset value1
        rank = zSetOperations.reverseRank(key, "value1");


    }

    public void bitmapExample() {

        String key = "bitmap";

        ValueOperations valueOperations = stringRedisTemplate.opsForValue();
        // setbit 100 1
        valueOperations.setBit(key, 100, true);

        // getbit 55
        boolean bit = valueOperations.getBit(key, 55);

        // bitcount key
        String value = valueOperations.get(key).toString();
        stringRedisTemplate.execute((RedisCallback) con ->
            con.bitCount(value.getBytes())
        );

        // bitop and dest dest1 dest2
        byte[] dest = new byte[1024];
        stringRedisTemplate.execute((RedisCallback) con ->
            con.bitOp(RedisStringCommands.BitOperation.AND, dest, "value1".getBytes(), "value2".getBytes())
        );
    }

    public void hyperLogLogsExample() {

        String key = "hll";

        HyperLogLogOperations hyperLogLogOperations = stringRedisTemplate.opsForHyperLogLog();

        // pfadd hll a b c
        hyperLogLogOperations.add(key, "a", "b", "c");

        // pfcounnt hll
        hyperLogLogOperations.size(key);


    }


    public void geoExample() {

        String key = "geo";

        GeoOperations geoOperations = stringRedisTemplate.opsForGeo();

        RedisGeoCommands.GeoLocation location1 = new RedisGeoCommands.GeoLocation("location1", new Point(111, 60));

        RedisGeoCommands.GeoLocation location2 = new RedisGeoCommands.GeoLocation("location2", new Point(181, 50));

        // geoadd geo 60 111 "location1"
        geoOperations.add(key, location1);

        geoOperations.add(key, location2);

        // geodist geo location1 location2
        geoOperations.distance(key, location1, location2);

        // geopos geo location1 location2
        geoOperations.position(key, location1, location2);
    }




}
