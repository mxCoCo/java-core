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
public class TestStreamAPI3 {
    List<User> users=Arrays.asList(
            new User(1,"zs",23),
            new User(2,"ls",18),
            new User(3,"ww",21),
            new User(4,"zm",24),
            new User(4,"zl",24));

    //中间操作
    /**
     * 映射
     *     map---接收lambda，将元素转换为其它形式或提取信息，接收一个函数作为参数，
     *          该函数会被应用到每个元素上，并将其映射成一个新的元素
     *     flatMap---接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有的流连接成一个流
     */

    @Test
    public void test1(){
        List<String> list=Arrays.asList("aa","bb","cc","dd","ee");
        list.stream().
                map(String::toUpperCase)
                .forEach(System.out::println);
        System.out.println("----------------");
        users.stream().
                map(User::getName)
                .forEach(System.out::println);
        System.out.println("----------------");
        //filterCharacter("asdf").forEach(System.out::println);
        Stream<Stream<Character>> stream = list.stream()
                .map(TestStreamAPI3::filterCharacter);//{{a,a},{b,b},...}
        stream.forEach((sm)->sm.forEach(System.out::println));
        System.out.println("----------------");
        final Stream<Character> characterStream = list.stream()
                .flatMap(TestStreamAPI3::filterCharacter);//{a,a,b,b,...}
        characterStream.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list=new ArrayList<>();
        for (Character c:
             str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    /**
     * 排序
     *  sorted();---自然排序
     *  sorted(Comparator com);---定制排序、
     */
    @Test
    public void test2(){
        List<String> list=Arrays.asList("dd","aa","ee","bb","cc");
        list.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("-----------------");
        users.stream()
                .sorted((v1,v2)->{
                    if(v1.getAge()==v2.getAge()){
                        return v1.getName().compareTo(v2.getName());
                    }else {
                        return v1.getAge().compareTo(v2.getAge());
                    }
                }).forEach(System.out::println);

    }



}
