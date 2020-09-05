package com.linjing.state;

//测试守护线程
//上帝守护你
public class TestDaemon {

    public static void main(String[] args) {
        God god = new God();
        You u = new You();

        Thread t =  new Thread(god);
        t.setDaemon(true); //默认false表示用户线程，正常线程都是用户线程

        t.start(); //上帝守护线程启动

        new Thread(u).start(); //你 线程启动
    }
}

//上帝:守护线程
class God implements Runnable{
    @Override public void run() {
        while (true) {
            System.out.println("God bless you.");
        }
    }
}

//你:真实线程
class You implements Runnable{
    @Override public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("每天都开心！");
        }
        System.out.println("-----------离开了-------");
    }
}
