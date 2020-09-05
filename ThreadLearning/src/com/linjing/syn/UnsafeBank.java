package com.linjing.syn;

//不安全的取钱
//两个人去银行取钱
public class UnsafeBank {

    public static void main(String[] args) {
        Account account = new Account(1000, "日常账户");

        Drawing d1 = new Drawing(account, 50, "海绵宝宝");
        Drawing d2 = new Drawing(account, 100, "派大星");

        d1.start();
        d2.start();
    }
}


//账户
class Account {
    int balance; //余额
    String name; //卡名

    public Account(int balance, String name) {
        this.balance = balance;
        this.name = name;
    }
}


//银行：模拟取款
class Drawing extends Thread {
    Account account; //账户
    int drawingMoney; //要取多少钱
    int nowMoney; //手头多少钱

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    //取钱
    //synchronized默认锁的是this,当前对象
    @Override public void run() {
        //锁的对象是变化的量，需要增删改的对象
        synchronized (account) { //同步块可以锁任何对象
            //判断能不能取
            if (account.balance - drawingMoney < 0) {
                System.out.println(Thread.currentThread().getName() + " : 余额不足，取不了");
                return;
            }
            //sleep可以放大问题的发生性
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //卡内余额
            account.balance = account.balance - drawingMoney;
            //手里的钱
            nowMoney += drawingMoney;

            System.out.println(account.name + "余额为:" + account.balance);
            //Thread.currentThread().getName() = this.getName()
            //this就是当前Drawing这个线程
            System.out.println(this.getName() + "手里的钱:" + nowMoney);
        }
    }
}
