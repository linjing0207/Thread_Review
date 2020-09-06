package com.linjing.highlevel;

public class JMMDemo {
    //volatile保证可见性
    private volatile static int num;
    //    private static AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) {
        //        fun1();
        fun2();
    }

    //1. volatile保证可见性
    //    public static void fun1() {
    //        new Thread(() -> { //该线程对主内存的变化不知道
    //            while (num == 0) {
    //
    //            }
    //        }).start();
    //        try {
    //            TimeUnit.SECONDS.sleep(1);
    //        } catch (InterruptedException e) {
    //            e.printStackTrace();
    //        }
    //        num = 1;
    //        System.out.println("结束");
    //    }

    //volatile不保证原子性
    public static void fun2() {
        //理论上, num = 20000
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) { //main, gc
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " " + num);
    }

    //加上synchronized一定会成功。
    public static void add() {
        num++; //不是原子操作
        //        num.getAndIncrement(); //改成AtomicInteger 原子类型
    }

}
