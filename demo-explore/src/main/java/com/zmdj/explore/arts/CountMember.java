package com.zmdj.explore.arts;

/**
 * @author zhangyunyun create on 2019/3/24
 */
public class CountMember implements Member {

    @Override
    public boolean accept(DataType event) {
        return event instanceof MessageData;
    }

    @Override
    public void handleData(DataType event) {

        if (accept(event)) {
            process(event);
        }

    }

    public void process(DataType dataType) {
        //...
    }
}
