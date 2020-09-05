package com.linjing.syn;

//不安全的买票
//线程不安全
public class UnsafeBuyTicket {

    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();
        new Thread(station, "派大星").start();
        new Thread(station, "海绵宝宝").start();
        new Thread(station, "蟹老板").start();
    }
}


class BuyTicket implements Runnable {

    //票数
    private int ticketNums = 20;
    boolean flag = true;  //外部停止标识

    @Override public void run() {
        //买票
        while (flag)
            buy();
    }

    //加上synchronized，变同步方法。
    private synchronized void buy() {
        //判断是否有票
        if (ticketNums <= 0) {
            flag = false;
            return;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //买票
        System.out.println(Thread.currentThread().getName() + "买到票" + ticketNums--);
    }
}
