package com.zmdj.demo.redis.pubsub;

/**
 * message publisher
 *
 * @author zhangyunyun create on 2019/2/7
 */
public interface MessagePublisher {

    void publish(String message);
}
