package com.newFeture.stream;

import com.newFeture.lambda.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class WorkStreamAPI {
    /**
     * 1、给定一个列表，如何返回一个由每个数的平方构成的列表
     * [1,2,3,4]--->[1,4,9,16]
     */
    @Test
    public void test1(){
        Integer[] nums=new Integer[]{1,2,3,4,5};
        Arrays.stream(nums)
                .map((x)->x*x)
                .forEach(System.out::println);
    }
    /**
     * 2、怎么用map和reduce方法数一数流中有多少个User呢？
     */
    List<User> users= Arrays.asList(
            new User(1,"zs",23, User.Status.FREE),
            new User(2,"ls",18, User.Status.BUSY),
            new User(3,"ww",21, User.Status.VACATION),
            new User(4,"zm",24, User.Status.BUSY),
            new User(4,"zl",25, User.Status.FREE),
            new User(4,"zl",25, User.Status.FREE));
    @Test
    public void test2(){
        Optional<Integer> opt = users.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);
        System.out.println(opt.get());
    }
}
