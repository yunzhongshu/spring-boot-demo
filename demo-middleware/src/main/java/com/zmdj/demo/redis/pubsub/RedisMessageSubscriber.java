package com.zmdj.demo.redis.pubsub;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.lang.Nullable;

/**
 * @author zhangyunyun create on 2019/2/7
 */
public class RedisMessageSubscriber implements MessageListener {

    @Override
    public void onMessage(Message message, @Nullable byte[] pattern) {
        System.out.println("Message received: " + message.toString());
    }
}
