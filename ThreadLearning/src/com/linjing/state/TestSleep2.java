package com.linjing.state;

import java.text.SimpleDateFormat;
import java.util.Date;

//模拟倒计时
public class TestSleep2 {

    //模拟倒计时
    public static void countDown() throws InterruptedException {
        int num = 10;
        while (true){
            Thread.sleep(1000);
            System.out.println(num--);
            if (num <= 0)
                break;;
        }
    }


    public static void main(String[] args) {
//        try {
//            countDown();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        while (true){
            //打印系统当前时间
            Date startTime = new Date(System.currentTimeMillis()); //获取系统当前时间
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(startTime));
        }
    }
}
