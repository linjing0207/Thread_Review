package com.linjing.thread;

//线程创建方式1：继承Thread类
public class TestThread extends Thread{

    //重写run方法
    @Override public void run() {
        System.out.println("hello");
    }

    public static void main(String[] args) {
        //创建Thread类
        TestThread t = new TestThread();
        t.start();
    }

}
