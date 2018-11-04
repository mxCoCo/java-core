package com.newFeture.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 一、lambda 表达式的基础语法：java8中引入了一个新的操作符 "->" 该操作符称为箭头操作符或lambda操作符
 *                      箭头操作符将lambda表达式才分为两部分
 *  左侧：lambda 表达式的参数列表
 *  右侧：lambda 表达式中所执行的功能 lambda体
 *
 *  语法格式：无参数 无返回值
 *          () -> System.Out.Println("hello lambda");
 *  语法格式2：有参数 无返回值,若只有一个参数，小括号可以不写
 *          (x) -> System.Out.Println(x);
 *  语法格式3：有多个参数 有返回值,并且方法体中有多条语句
 *          Comparator<Integer> com=(x,y) -> {
             System.out.println("函数式接口");
             return x-y;
            };
 *  语法格式4：若lambda表达式中的语句体只有一行，花括号{}和return都可以省略
 *  语法格式5：lambda表达式中参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，
 *             数据类型，即"类型推断"
 *  上联：左右遇一括号省
 *  下联：左侧推断类型省
 *  横批：能省则省
 *
 *  二、lambda 表达式需要"函数式接口"的支持
 *  函数式接口：接口中只有一个抽象方法的接口，称为函数式接口，可以使用注解@FunctionalInterface修饰
 *              可以检查是否为函数式接口
 */
public class TestLambda2 {

    @Test
    public void test1(){
        Runnable r1=new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        };
        r1.run();
        System.out.println("------------------------------");
        Runnable r2=() -> System.out.println("hello lambda");
        r2.run();
    }

    @Test
    public void test2(){
        Consumer cons=(x) -> System.out.println(x);
        cons.accept("i am consumer");
    }

    @Test
    public void test3(){
        Comparator<Integer> com=(x,y) -> {
            System.out.println("函数式接口");
            return x-y;
        };
    }

    //需求：对一个数进行运算
    @Test
    public void test4(){
        int result=operation(10,x ->x*x);
        System.out.println(result);
    }
    public int operation(int num,MyFuncInterface myFuncInterface){
        return myFuncInterface.getValue(num);
    }
}
