package com.newFeture.stream;

import com.newFeture.lambda.User;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 终止操作
 */
public class TestStreamAPI4 {
    List<User> users= Arrays.asList(
            new User(1,"zs",23, User.Status.FREE),
            new User(2,"ls",18, User.Status.BUSY),
            new User(3,"ww",21, User.Status.VACATION),
            new User(4,"zm",24, User.Status.BUSY),
            new User(4,"zl",25, User.Status.FREE),
            new User(4,"zl",25, User.Status.FREE));
    /**
     * 查找与匹配
     * allMatch---检查是否匹配所有元素
     * anyMatch---检查是否至少匹配一个元素
     * noneMatch---检查是否没有匹配所有元素
     * findFirst---返回第一个元素
     * findAny---返回当前流中的任意元素
     * count---返回流中元素的总个数
     * max---返回流中最大值
     * min---返回流中最小值
     */
    @Test
    public void test1(){
        //allMatch---检查是否匹配所有元素
        boolean b = users.stream()
                .allMatch((u) -> u.getStatus().equals(User.Status.BUSY));
        System.out.println(b);
        //anyMatch---检查是否至少匹配一个元素
        boolean b2 = users.stream()
                .anyMatch((u) -> u.getStatus().equals(User.Status.BUSY));
        System.out.println(b2);
        //noneMatch---检查是否没有匹配所有元素
        boolean b3 = users.stream()
                .noneMatch((u) -> u.getStatus().equals(User.Status.BUSY));
        System.out.println(b3);
        //findFirst---返回第一个元素
        Optional<User> optional = users.stream()
                .sorted((e1, e2) -> e1.getAge().compareTo(e2.getAge()))
                .findFirst();
        System.out.println(optional.get()+"\n");
        //findAny---返回当前流中的任意元素
        Optional<User> opt2 = users.parallelStream()
                .filter((e)->e.getStatus().equals(User.Status.FREE))
                .findAny();
        System.out.println(opt2.get());
    }

    @Test
    public void test2(){
        long count = users.stream()
                .count();
        System.out.println(count);

        Optional<User> opt1 = users.stream()
                .max((x1, x2) -> Integer.compare(x1.getAge(), x2.getAge()));
        System.out.println(opt1.get());

        Optional<Integer> opt2 = users.stream()
                .map(User::getAge)
                .min(Integer::compare);
        System.out.println(opt2.get());
    }

    /**
     * 归约
     * reduce(T identity,BinaryOperator)/reduce(BinaryOperator)---可以将流中元素反复结合起来，得到一个值
     */
    @Test
    public void test3(){
        List<Integer> list=Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);
        System.out.println("-------------------");
        Optional<Integer> opt = users.stream()
                .map(User::getAge)
                .reduce(Integer::sum);
        System.out.println(opt.get());
    }

    /**
     * 收集
     * collect---将流转换为其他形式，接收一个Collector接口的实现，用于给stream中的元素做汇总的方法
     */
    @Test
    public void test4(){
        List<String> list = users.stream()
                .map(User::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("----------------------");
        Set<String> set = users.stream()
                .map(User::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("----------------------");
        LinkedHashSet<String> linkedHashSet = users.stream()
                .map(User::getName)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        linkedHashSet.forEach(System.out::println);
        System.out.println("----------------------");
        //总数
        Long count = users.stream()
                .collect(Collectors.counting());
        System.out.println("总人数为："+count);
        System.out.println("----------------------");
        //平均值
        Double age = users.stream()
                .collect(Collectors.averagingInt(User::getAge));
        System.out.println("平均年龄为："+age);
    }

    //分组:分组结果为map
    @Test
    public void test5(){
        Map<User.Status, List<User>> map = users.stream()
                .collect(Collectors.groupingBy(User::getStatus));
        System.out.println(map);
    }
    //多级分组:分组结果为map
    @Test
    public void test6(){
        Map<User.Status, Map<String, List<User>>> map = users.stream()
                .collect(Collectors.groupingBy(User::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() < 20) {
                        return "青年";
                    } else if (e.getAge() < 50) {
                        return "壮年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(map);
    }
    //分区
    @Test
    public void test7(){
        Map<Boolean, List<User>> map = users.stream()
                .collect(Collectors.partitioningBy((e) -> e.getAge() > 20));
        System.out.println(map);
    }
    @Test
    public void test8(){
        IntSummaryStatistics iss = users.stream()
                .collect(Collectors.summarizingInt(User::getAge));
        System.out.println(iss.getSum());
        System.out.println(iss.getAverage());
        System.out.println(iss.getMax());
        System.out.println(iss.getMin());
    }
    @Test
    public void test9(){
        String s = users.stream()
                .map(User::getName)
                .collect(Collectors.joining());//joining(","),joining(",","^","$")
        System.out.println(s);
    }
}
