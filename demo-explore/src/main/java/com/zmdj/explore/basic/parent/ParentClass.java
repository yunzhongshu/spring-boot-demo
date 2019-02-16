package com.zmdj.explore.basic.parent;

/**
 * @author zhangyunyun create on 2019/2/15
 */
public class ParentClass {

    public ParentClass() {
        System.out.println("Parent constructor with no arguments executed.");
    }

    public ParentClass(String str) {
        System.out.println(String.format("Parent constructor with argument [%s] executed.", str));
    }

}
