package com.zmdj.explore.arts;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyunyun create on 2019/3/24
 */
public class DataBus {

    private List<Member> members = new ArrayList<>();

    public void publish(DataType data) {
        for (Member member : members) {
            member.handleData(data);
        }

    }

    public void subscribe(Member member) {
        members.add(member);
    }

    public void unSubScribe(Member member) {

        for (Member member1 : members) {
            if (member1.equals(member)) {
                members.remove(member);
            }
        }

    }

    // ...
}
