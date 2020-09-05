package com.linjing.state;

import com.linjing.thread.Ticket;

//模拟网络延时:放大问题的发生性
public class TestSleep1 implements Runnable{
    private int ticketNums = 10;
    //实现run方法
    @Override public void run() {
        while(true){
            if(ticketNums <= 0)
                break;

            //模拟延时，若不模拟延时，CPU太快了，把资源全分给其中一个线程，抢光了所有的票。
            try {
                Thread.sleep(100);
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
