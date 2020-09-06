package com.linjing.highlevel;

import java.util.concurrent.*;

//总结回顾线程的创建
public class ThreadReview {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new MyThread1().start(); //继承Thread类可直接实例化后运行。
        new Thread(new MyThread2()).start(); //实现runnable接口的, 需要写一个代理类对象

        //可以使用线程池
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<Integer> result = service.submit(new MyThread3());
        System.out.println(result.get());
        service.shutdown();

        //也可使用FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread3());
        new Thread(futureTask).start();
        Integer i = futureTask.get();
        System.out.println(i);

    }
}


//1. 继承Thread类
class MyThread1 extends Thread {
    @Override public void run() {
        System.out.println("MyThread1");
    }
}


//2. 实现Runnable接口
class MyThread2 implements Runnable {
    @Override public void run() {
        System.out.println("MyThread2");
    }
}


//1. 实现Callable接口
class MyThread3 implements Callable<Integer> {

    @Override public Integer call() throws Exception {
        System.out.println("MyThread3");
        return 100;
    }
}
