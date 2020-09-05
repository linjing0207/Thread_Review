package com.linjing.thread;


//多个线程，同时操作同一个对象
//买火车票的例子

//发现问题：多个线程操作同一个资源的情况下，线程不安全，数据紊乱。
public class Ticket implements Runnable{

    private int ticketNums = 10;
    //实现run方法
    @Override public void run() {
        while(true){
            if(ticketNums <= 0)
                break;

            //模拟延时，若不模拟延时，CPU太快了，把资源全分给其中一个线程，抢光了所有的票。
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread() + ": 拿到了第" + ticketNums-- + "张票");
        }
    }

    public static void main(String[] args) {
        //TestThread2 t 是Runnable接口的实现对象
        Ticket t = new Ticket();
        //创建代理类对象，通过线程对象来开启线程

        new Thread(t, "小猪佩奇").start();
        new Thread(t,"弟弟乔治").start();
        new Thread(t,"爸爸").start();

    }

}
