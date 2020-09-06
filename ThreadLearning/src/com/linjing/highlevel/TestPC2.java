package com.linjing.highlevel;

//测试生产者消费者问题2：信号灯法，标识位
public class TestPC2 {
    public static void main(String[] args) {
        Program program = new Program();
        new Actor(program).start();
        new Audience(program).start();
    }
}


//生产者 --> 演员
class Actor extends Thread {
    Program program;

    public Actor(Program program) {
        this.program = program;
    }

    @Override public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) { //每两个小时放一次
                program.acting("香蜜沉沉烬如霜");
            } else {
                program.acting("广告");
            }
        }

    }
}


//消费者 --> 观众
class Audience extends Thread {
    Program program;

    public Audience(Program program) {
        this.program = program;
    }

    @Override public void run() {
        for (int i = 0; i < 20; i++) {
            program.watching();
        }
    }
}


//产品 --> 节目
class Program {
    //演员拍戏，观众等待(T)；观众看剧，演员等待(F)。
    String content; //表演的内容
    boolean flag = true;

    //表演
    public synchronized void acting(String content) {
        if (!flag) { //False:观众看剧，演员需要等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("演员:表演了" + content);
        this.content = content; //更新内容

        //演员拍好了，就通知观众观看
        this.notifyAll();
        flag = !flag;
    }

    //观看
    public synchronized void watching() {
        if (flag) { //True: 演员拍戏，观众需要等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观众:观看了" + content);
        //观众观看了，就通知演员去拍戏
        this.notifyAll();
        flag = !flag;
    }
}
