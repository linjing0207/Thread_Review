package com.linjing.lamda;

public class TestLambda2 {



    public static void main(String[] args) {
        ILove love = null;

        love = (int a) -> { System.out.println("I love you." + a);};
        //简化1：参数类型
        love = (a)-> {System.out.println("I love you." + a);};
        //简化2：简化括号
        love = a -> {System.out.println("I love you." + a);};
        //简化3：简化花括号
        love = a -> System.out.println("I love you." + a);

        //lambda表达式总结:
            //只有一行代码的情况下，才能简化成一行;如果有多行，就用代码块包裹。"{}"
            //前提是必须是函数式接口。
            //多个参数也可以去掉参数类型，要去掉就都去掉，但必须加上括号。"()"
        love.love(520);
    }

}

interface ILove{
    void love(int a);
}


