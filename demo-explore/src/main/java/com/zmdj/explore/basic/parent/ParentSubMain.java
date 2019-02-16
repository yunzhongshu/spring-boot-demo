package com.zmdj.explore.basic.parent;

/**
 * @author zhangyunyun create on 2019/2/15
 */
public class ParentSubMain {

    public static void main(String[] args) {

        new SubClass();

        new SubClass();

        new SubClass().new SubInnerClass().innerMethod();
    }

}
