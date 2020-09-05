package com.linjing.thread;


//线程创建方式2：实现Runnable接口
//由于java单继承，推荐使用Runnable接口; 方便同一个对象被多个线程使用。
public class TestRunnable implements Runnable{

    //实现run方法
    @Override public void run() {
        System.out.println("hello");
    }

    public static void main(String[] args) {
        //TestThread2 t 是Runnable接口的实现对象
        TestRunnable t = new TestRunnable();
        //创建代理类对象，通过线程对象来开启线程
        new Thread(t).start(); //new Thread(目标对象).start()

    }

}
