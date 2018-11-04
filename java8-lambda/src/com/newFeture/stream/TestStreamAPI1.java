package com.newFeture.stream;

import com.newFeture.lambda.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * 一、Stream的三个操作步骤
 *  1.创建Stream
 *  2.中间操作
 *  3.终止操作(终端操作)
 */
public class TestStreamAPI1 {
    //创建stream流
    @Test
    public void test1(){
        //1、可以通过Collection系列的集合提供的stream()或parallelStream()方法获得
        List<String> list=new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2、通过Arrays中的静态方法stream()获取数组流
        User[] users=new User[10];
        Stream<User> stream2 = Arrays.stream(users);

        //3、通过Stream类中的静态方法of()
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        //4、创建无限流
        //迭代
        Stream<Integer> stream4 = Stream.iterate(2, (x) -> x + 2);
        stream4.limit(20).forEach(System.out::println);

        //生成
        Stream.generate(()->Math.random()).limit(5).forEach(System.out::println);

    }
}
