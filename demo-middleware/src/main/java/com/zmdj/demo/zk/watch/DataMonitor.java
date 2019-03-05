package com.zmdj.demo.zk.watch;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.Arrays;

/**
 * @author zhangyunyun create on 2019/3/4
 */
public class DataMonitor implements Watcher, AsyncCallback.StatCallback{

    ZooKeeper zk;

    String znode;

    Watcher chainWatcher;

    boolean dead;

    DataMonitorListener listener;

    byte[] prevData;

    public DataMonitor(ZooKeeper zk, String znode, Watcher chainWatcher, DataMonitorListener listener) {
        this.zk = zk;
        this.znode = znode;
        this.chainWatcher = chainWatcher;
        this.listener = listener;
        zk.exists(znode, true, this, null);
    }


    public interface DataMonitorListener {
        /**
         * The existence status of the node has changed.
         */
        void exists(byte[] data);

        /**
         * The ZooKeeper session is no longer valid.
         *
         * @param rc
         *                the ZooKeeper reason code
         */
        void closing(int rc);
    }



    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        boolean exists;
        switch (rc) {
            case KeeperException.Code.Ok:
                exists = true;
                break;
            case KeeperException.Code.NoNode:
                exists = false;
                break;
            case KeeperException.Code.SessionExpired:
            case KeeperException.Code.NoAuth:
                dead = true;
                listener.closing(rc);
                return;
            default:
                // Retry errors
                zk.exists(znode, true, this, null);
                return;
        }

        byte[] b = null;
        if (exists) {
            try {
                b = zk.getData(znode, false,null);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                return;
            }
        }
        // not equals between b and prevData
        if ((b == null && b != prevData) || (b != null && !Arrays.equals(prevData, b))) {
            listener.exists(b);
            prevData = b;
        }


    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        String path = watchedEvent.getPath();
        if (watchedEvent.getType() == Event.EventType.None) {
            switch (watchedEvent.getState()) {
                case SyncConnected:
                    // do nothing
                    break;
                case Expired:
                    dead = true;
                    listener.closing(KeeperException.Code.SessionExpired);
                    break;
            }

        } else {
            if (path != null && path.equals(znode)) {
                zk.exists(znode, true, this, null);
            }
        }
        if (chainWatcher != null) {
            chainWatcher.process(watchedEvent);
        }
    }
}
