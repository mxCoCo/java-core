package com.newFeture.forkjoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class TestForkJoin {

    @Test
    public void test1(){
        Instant start=Instant.now();

        ForkJoinPool pool=new ForkJoinPool();
        ForkJoinTask<Long> task=new ForkJoinCalculate(0,10000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);

        Instant end=Instant.now();
        System.out.println("耗时为："+ Duration.between(start,end).toMillis());

    }

    /**
     * java8并行流---parallel()
     */
    @Test
    public void test2(){
        Instant start=Instant.now();
        long sum = LongStream.rangeClosed(0, 10000L)
                .parallel()//并行流,sequential()串行流
                .reduce(0, Long::sum);
        System.out.println(sum);

        Instant end=Instant.now();
        System.out.println("耗时为："+ Duration.between(start,end).toMillis());
    }
}
