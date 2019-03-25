package com.zmdj.explore.arts;

/**
 * @author zhangyunyun create on 2019/3/24
 */
public interface Member {

    boolean accept(DataType event);

    void handleData(DataType event);
}
