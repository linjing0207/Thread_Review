package com.linjing.state;

//测试join方法，想象成插队
public class TestJoin implements Runnable{

    public static void main(String[] args) throws InterruptedException {

        //启动我们的线程
        TestJoin tj = new TestJoin();
        Thread thread = new Thread(tj);
        thread.start();

        //主线程跑500次
        for (int i = 0; i < 500; i++) {
            if (i == 200)
                 thread.join();//当i=200时，副线程插队，主线程阻塞，等待副线程执行完
            System.out.println("main" + i);
        }
    }

    @Override public void run() {
        //副线程跑1000次
        for (int i = 0; i < 1000; i++) {
            System.out.println("线程VIP来了" + i);
        }
    }

    //main跑到199次，vip线程跑1000次，然后main再跑完
}
