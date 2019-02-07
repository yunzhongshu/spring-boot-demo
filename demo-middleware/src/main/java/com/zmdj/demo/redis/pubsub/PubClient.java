package com.zmdj.demo.redis.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author zhangyunyun create on 2019/2/7
 */
@Component
public class PubClient {

    @Autowired
    private RedisMessagePublisher redisMessagePublisher;

    public void publishMessage() {
        String message = "Message " + UUID.randomUUID();
        redisMessagePublisher.publish(message);
    }

}
