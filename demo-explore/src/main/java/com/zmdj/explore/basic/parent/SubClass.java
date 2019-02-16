package com.zmdj.explore.basic.parent;

/**
 * @author zhangyunyun create on 2019/2/15
 */
public class SubClass extends ParentClass {

    static {
        // 不管new 多少个对象，这里只执行一次

        System.out.println("SubClass static block executed.");
    }

    public SubClass() {
        // super(); 这里注释掉也会执行ParentClass的无参构造函数
        System.out.println("SubClass constructor with no arguments executed.");
    }

    // 重载 ====================

    public void doSomething() {

    }

    public void doSomething(String str1) {

    }

    class SubInnerClass {

        public void innerMethod() {
            // SubClass.this reference to the out object who create it
            SubClass.this.doSomething();

            System.out.println("SubInnerClass execute innerMethod.");
        }

    }

}
