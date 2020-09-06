package com.linjing.highlevel;

import java.util.concurrent.locks.ReentrantLock;

//测试Lock锁
public class TestLock {
    public static void main(String[] args) {
        TestLock2 tl = new TestLock2();
        new Thread(tl).start();
        new Thread(tl).start();
        new Thread(tl).start();
    }
}


class TestLock2 implements Runnable {

    int ticketNums = 10;

    //定义lock锁
    private final ReentrantLock lock = new ReentrantLock();

    @Override public void run() {
        while (true) {
            try {
                //显式地:加锁
                lock.lock();
                if (ticketNums > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ticketNums--);
                } else
                    break;
            } finally {
                //显式地:解锁
                lock.unlock();
            }

        }
    }
}
