package com.linjing.thread;

import java.util.concurrent.*;

//线程创建方式3：实现Callable接口
//好处：可以定义返回值，可以抛出异常
public class TestCallable implements Callable<Integer> {
    private int age;

    public TestCallable(int age) {
        this.age = age;
    }

    //重写call方法
    @Override public Integer call() {
        return age;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable(11);
        TestCallable t2 = new TestCallable(18);
        TestCallable t3 = new TestCallable(21);

        //创建执行服务
        ExecutorService service = Executors.newFixedThreadPool(3);

        //提交执行：通过服务提交线程
        Future<Integer> r1 = service.submit(t1);
        Future<Integer> r2 = service.submit(t2);
        Future<Integer> r3 = service.submit(t3);

        //获取结果
        int rs1 = r1.get();
        int rs2 = r2.get();
        int rs3 = r3.get();

        System.out.println(rs1);
        System.out.println(rs2);
        System.out.println(rs3);
        //关闭服务
        service.shutdown();
    }


}
