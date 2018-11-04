package com.newFeture.lambda;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、方法引用：若lambda体中的内容有方法已经实现了，我们可以使用“方法引用”
 *          （可以理解为方法引用是lambda表达式的另外一种表现形式）
 *  主要有三种语法格式：
 *      对象::实例方法名
 *      类::静态方法名
 *      类::实例方法名
 *
 *  注意：
 *      1、Lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致
 *      2、若lambda表达式参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method
 *  二、构造器引用：
 *      格式：
 *      ClassName::new
 *      注意：需要调用的构造器的参数列表要与函数式接口中的抽象方法保持一致
 *  三、数组引用：
 *      Type[]::new
 */
public class TestMethodRef {

    //对象::实例方法名
    @Test
    public void test1(){
        Consumer<String> con=(x)-> System.out.println(x);

        PrintStream ps=System.out;
        Consumer<String> con1=ps::println;
        con1.accept("小娃");
    }

    @Test
    public void test2(){
        User user=new User();
        user.setAge(12);
        Supplier<Integer> sup=()->user.getAge();
        Integer age = sup.get();
        System.out.println("年龄："+age);

        Supplier<Integer> sup2=user::getAge;
        System.out.println("年龄："+sup2.get());
    }

    //类::静态方法名
    @Test
    public void test3(){
        Comparator<Integer> com=(x,y)->Integer.compare(x,y);
        Comparator<Integer> com2=Integer::compare;
    }

    //类::实例方法名
    @Test
    public void test4(){
        BiPredicate<String,String> pre=(x,y)->x.equals(y);
        BiPredicate<String,String> pre2=String::equals;
    }

    //构造器引用
    @Test
    public void test5(){
        Supplier<User> sup=()->new User();
        //构造器引用方式
        Supplier<User> sup2=User::new;
        User user = sup2.get();
        System.out.println(user);
    }
    @Test
    public void test6(){
        Function<Integer,User> fun=(x)->new User(x);
        Function<Integer,User> fun2=User::new;
    }

    //数组引用
    @Test
    public void test7(){
        Function<Integer,String[]> fun=(x)->new String[x];
        Function<Integer,String[]> fun2=String[]::new;
        String[] str = fun2.apply(20);
        System.out.println(str.length);
    }
}
