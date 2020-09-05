package com.linjing.state;

//测试礼让线程
//礼让不一定成功，看cpu心情
public class TestYield {

    public static void main(String[] args) {
        MyYield my = new MyYield();
        new Thread(my, "a").start();
        new Thread(my, "b").start();
    }
}

class MyYield implements Runnable{

    @Override public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始执行");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "线程停止执行");
    }
}

    /*
    a线程礼让没成功，cpu又让a线程继续执行；之后才开始执行b线程

    a线程开始执行
    a线程停止执行
    b线程开始执行
    b线程停止执行
     */

    /*
    a线程礼让成功，cpu又让开始执行b线程；再次礼让，a线程重新抢到资源，

    a线程开始执行
    b线程开始执行
    a线程停止执行
    b线程停止执行
     */
