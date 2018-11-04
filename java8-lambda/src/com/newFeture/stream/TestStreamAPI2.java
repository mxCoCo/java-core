package com.newFeture.stream;

import com.newFeture.lambda.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一、Stream的三个操作步骤
 *  1.创建Stream
 *  2.中间操作
 *  3.终止操作(终端操作)
 */
public class TestStreamAPI2 {
    List<User> users=Arrays.asList(
            new User(1,"zs",23),
            new User(2,"ls",18),
            new User(3,"ww",21),
            new User(4,"zl",24),
            new User(4,"zl",24));

    //中间操作
    /**
     * 筛选和切片
     * filter---接收lambda，从流中排除某些元素
     * limit---截断流，使其元素不超过给定数量
     * skip(n)---跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流。与limit(n)互补
     * distinct---筛选，通过流所生成元素的hashCode()和equals()去除重复元素
     */

    //内部迭代：迭代操作由Stream API 完成
    @Test
    public void test1(){
        Stream<User> userStream = users.stream();
        //中间操作(只有终止操作执行才会执行此中间操作)
        Stream<User> newStream = userStream.filter((u) -> {
            System.out.println("中间操作执行");
            return u.getAge() > 20;
        });
        //终止操作
        newStream.forEach(System.out::println);
    }

    //外部迭代
    @Test
    public void test2(){
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test3(){
        users.stream().filter((u)->{
                    System.out.println("成功过滤");
                    return u.getAge()>20;
                })
                .limit(2)//只要获取到两条数据，过滤操作将不再往下继续执行
                .forEach(System.out::println);
    }

    @Test
    public void test4(){
        users.stream().filter((u)-> u.getAge()>20)
                .skip(1)//跳过第一条数据,与limit互补
                .distinct()
                .forEach(System.out::println);
    }


}
