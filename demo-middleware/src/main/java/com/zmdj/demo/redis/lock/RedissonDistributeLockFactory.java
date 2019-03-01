package com.zmdj.demo.redis.lock;


import com.zmdj.demo.lock.DistributeLockFactory;

/**
 * @author zhangyunyun create on 2019/2/25
 */
public class RedissonDistributeLockFactory implements DistributeLockFactory{



    @Override
    public boolean lock(String lockName) {
        return false;
    }

    @Override
    public boolean tryLock(String lockName) {
        return false;
    }

    @Override
    public boolean unlock(String lockName) {
        return false;
    }
}
