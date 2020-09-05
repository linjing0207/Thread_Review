package com.linjing.state;

//观察测试线程的状态
public class TestState {


    public static void main(String[] args) throws InterruptedException {
        //新生状态
        Thread thread = new Thread(()-> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("-----------------");
        });

        //观察状态
        Thread.State state = thread.getState();
        System.out.println(state);

        //观察启动后
        thread.start(); //启动线程
        System.out.println(state = thread.getState()); //Runnable

        while (state != Thread.State.TERMINATED){ //只要线程不终止，就一直输出
            Thread.sleep(100);
            System.out.println(state = thread.getState()); //更新输出状态
        }
//        thread.start(); //会报错IllegalThreadStateException，死亡之后的线程不能再启动了。

    }
}
