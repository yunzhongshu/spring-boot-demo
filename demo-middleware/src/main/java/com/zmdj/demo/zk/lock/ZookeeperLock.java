package com.zmdj.demo.zk.lock;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author zhangyunyun create on 2019/3/4
 */
public class ZookeeperLock implements Lock, Watcher {

    private ZooKeeper zk;
    private String root = "/locks";
    private String lockName;
    private String myZnode;
    private int sessionTimeout = 30000;
    private List<Exception> exceptions = new ArrayList<Exception>();

    public ZookeeperLock(String config, String lockName) {
        this.lockName = lockName;
        try {
            zk = new ZooKeeper(config, sessionTimeout, this);
            Stat stat = zk.exists(root, false);
            if (stat == null) {
                zk.create(root, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            exceptions.add(e);
        } catch (KeeperException e) {
            exceptions.add(e);
        } catch (InterruptedException e) {
            exceptions.add(e);
        }
    }


    @Override
    public void lock() {
        if (exceptions.size() > 0) {
//            throw new LockException(exceptions.get(0));
        }
        if (!tryLock()) {
//            throw new LockException("Please try late!");
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        this.lock();
    }

    @Override
    public boolean tryLock() {
        try {
            myZnode = zk.create(root + "/" + lockName, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return tryLock();
    }

    @Override
    public void unlock() {
        try {
            zk.delete(myZnode, -1);
            myZnode = null;
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void process(WatchedEvent event) {

    }
}
