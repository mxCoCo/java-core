package com.newFeture.lambda;

import org.junit.Test;
import java.util.Comparator;
import java.util.TreeSet;

public class TestLambda1 {

    /**
     * 原来的匿名内部类
     */
    @Test
    public void test1(){
        Comparator<Integer> com=new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                return o1-o2;
            }
        };
        TreeSet<Integer> tree=new TreeSet<>(com);
    }

    /**
     * lambda表达式
     */
    @Test
    public void test2(){
        Comparator<Integer> com=(x,y) -> x-y;
        TreeSet<Integer> tree=new TreeSet<>(com);
    }

}
