package com.zmdj.explore.arts;

/**
 * @author zhangyunyun create on 2019/3/24
 */
public class MessageData implements DataType{

    private DataBus dataBus;

    public void send() {
        dataBus.publish(this);
    }

}
