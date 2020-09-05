package com.linjing.thread;

//模拟龟兔赛跑
public class Race implements Runnable{

    private static String winner;
    @Override public void run() {
        for (int i = 1; i <= 100; i++) {
            //模拟兔子睡觉，每10步，就休息2s
            if(Thread.currentThread().getName().equals("兔子") && i % 10 == 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //判断比赛是否结束
            if(isGameOver(i))
                break;
            System.out.println(Thread.currentThread().getName()+"跑了"+i+"步");
        }
    }

    //判断比赛是否结束
    public boolean isGameOver(int steps){
        if(winner != null) return true;
        if(steps == 100){
            winner = Thread.currentThread().getName();
            System.out.println("winner is " + winner);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Race race =  new Race();
        new Thread(race, "兔子").start();
        new Thread(race, "乌龟").start();
    }
}
