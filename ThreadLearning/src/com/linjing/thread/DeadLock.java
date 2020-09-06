package com.linjing.thread;

//死锁：多个线程互相抱着对方需要的资源，然后形成僵持。
public class DeadLock {
    public static void main(String[] args) {
        Makeup g1 = new Makeup(0, "佩奇");
        Makeup g2 = new Makeup(1, "乔治");
        g1.start();
        g2.start();
    }
}


class Lipstick {
}


class Mirror {
}


class Makeup extends Thread {

    //需要的资源只有一份，用static来保证只有一份。
    //否则new Makeup()的时候会，重新生成各自的lipstick和mirror,就不是同一份资源了。
    static final Lipstick lipstick = new Lipstick();
    static final Mirror mirror = new Mirror();

    int choice; //选择
    String name; //名字

    public Makeup(int choice, String name) {
        this.choice = choice;
        this.name = name;
    }

    @Override public void run() {
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //化妆:互相持有对方的锁，
    private void makeup() throws InterruptedException {
        if (choice == 0) {
            synchronized (lipstick) { //获得口红的锁
                System.out.println(this.name + "获得口红的锁");
                Thread.sleep(2000);
                synchronized (mirror) { //一秒钟后获得了镜子的锁
                    System.out.println(this.name + "获得了镜子的锁");
                }
            }
            //把同步块拿出来就好了。
            //            synchronized (mirror) { //一秒钟后获得了镜子的锁
            //                System.out.println(this.name + "获得了镜子的锁");
            //            }
        } else {
            synchronized (mirror) { //获得了镜子的锁
                System.out.println(this.name + "获得了镜子的锁");
                Thread.sleep(1000);
                synchronized (lipstick) { //一秒钟后获得口红的锁
                    System.out.println(this.name + "获得口红的锁");
                }
            }
        }
    }
}
