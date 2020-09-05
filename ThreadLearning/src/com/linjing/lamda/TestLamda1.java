package com.linjing.lamda;

/**
 推导lambda表达式
 */
class TestLambda1 {

    //3.静态内部类
    static class Like2 implements ILike{
        @Override public void lambda() {
            System.out.println("I like lambda2.");
        }
    }

    public static void main(String[] args) {
        //实现类
        ILike like = new Like1();
        like.lambda();

        //静态内部类
        like = new Like2();
        like.lambda();

        //4.局部内部类
        class Like3 implements ILike{
            @Override public void lambda() {
                System.out.println("I like lambda3.");
            }
        }
        like = new Like3();
        like.lambda();

        //5.匿名内部类，没有类的名称，必须借助接口或者父类
        like = new ILike() {
            @Override public void lambda() {
                System.out.println("I like lambda4.");
            }
        };
        like.lambda();

        //6.jdk1.8:用lambda简化
        like = () -> System.out.println("I like lambda4.");
        like.lambda();


    }
}

//1.定义一个函数式接口
interface ILike{
    void lambda();
}

//2.外部类实现一个接口
class Like1 implements ILike{
    @Override public void lambda() {
        System.out.println("I like lambda1.");
    }
}
