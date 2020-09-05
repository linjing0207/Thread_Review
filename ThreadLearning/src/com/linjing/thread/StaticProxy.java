package com.linjing.thread;

//静态代理模式总结:Thread底部的实现原理
//真实对象和代理对象都要实现同一个接口
//代理对象要代理真实角色

//好处：代理对象可以做很多真实对象做不了的事情；真实对象专注做自己的事情

public class StaticProxy {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("I love you.")).start();
        new WeddingCompany(new You()).HappyMarry();

    }
}

interface Marry{
    void HappyMarry();
}

class You implements Marry{
    @Override public void HappyMarry() {
        System.out.println("你结婚啦！");
    }
}

class WeddingCompany implements Marry{
    private Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override public void HappyMarry() {
       prepare();
       target.HappyMarry(); //调用接口方法。
       finish();
    }

    public void prepare(){
        System.out.println("结婚前，准备布置。");
    }

    public void finish(){
        System.out.println("结婚后，收尾款。");
    }
}
