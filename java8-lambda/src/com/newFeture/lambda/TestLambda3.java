package com.newFeture.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java8 内置的四大核心的函数式接口
 *
 * Consumer<T>:消费型接口
 *          void accept(T t);
 *
 * Supplier<T>:供给型接口
 *          T get();
 *
 * Function<T,R>:函数式接口
 *          R apply(T t);
 *
 * Predicate<T>:断言型接口
 *          boolean test(T t);
 */
public class TestLambda3 {

    /**
     *  Consumer<T>:消费型接口
     *    void accept(T t);
     */
    @Test
    public void test1(){
        consume(100,(mon) -> System.out.println("消费物品金额为："+mon));
    }
    public void consume(double money, Consumer<Double> cons){
        cons.accept(money);
    }

    /**
     * * Supplier<T>:供给型接口
     *     T get();
     */
    @Test
    public void test2(){
        List<Integer> nums = supply(10, () -> (int) (Math.random() * 100));
        for (Integer num:nums
             ) {
            System.out.println(num);
        }
    }
    public List<Integer> supply(int num, Supplier<Integer> sup){
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<num;i++){
            Integer value = sup.get();
            list.add(value);
        }
        return list;
    }

    /**
     * Function<T,R>:函数式接口
     *      R apply(T t);
     */
    @Test
    public void test3(){
        String result=operateStr("abc",(x)->x.toUpperCase());
        System.out.println(result);
    }
    public String operateStr(String str, Function<String,String> func){
        return func.apply(str);
    }

    /**
     * Predicate<T>:断言型接口
     *     boolean test(T t);
     */
    @Test
    public void test4(){
        List<String> strList= Arrays.asList("hello","world","zsan","ok","yes");
        List<String> resList=filterStr(strList,(s)->s.length()>=3);
        for (String str:
             resList) {
            System.out.println(str);
        }
    }
    public List<String> filterStr(List<String> strList, Predicate<String> pre){
        List<String> list=new ArrayList<>();
        for (String str:
             strList) {
            if(pre.test(str)){
                list.add(str);
            }
        }
        return list;
    }

}
