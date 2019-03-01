package com.zmdj.demo.lock;

/**
 * 分布式锁 接口类
 * @author zhangyunyun create on 2019/2/25
 */
public interface DistributeLockFactory {

    boolean lock(String lockName);

    boolean tryLock(String lockName);

    boolean unlock(String lockName);

}
