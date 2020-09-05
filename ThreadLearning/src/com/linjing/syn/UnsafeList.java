package com.linjing.syn;

import java.util.ArrayList;
import java.util.List;

//线程不安全的集合
public class UnsafeList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                synchronized (list) { //锁住list
                    list.add(Thread.currentThread().getName());
                }

            }).start();
        }
        //把两个元素添加到了同一个位置。
        System.out.println(list.size());
    }
}
