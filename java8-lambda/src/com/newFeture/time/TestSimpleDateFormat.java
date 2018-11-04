package com.newFeture.time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class TestSimpleDateFormat {
    public static void main(String[] args) throws Exception{
        /*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Callable<Date> call=new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                //return sdf.parse("2018-01-06");
                return DateFormatThreadLocal.convert("2018-01-06");
            }
        };
        ExecutorService pool= Executors.newFixedThreadPool(10);
        List<Future<Date>> futures=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            futures.add(pool.submit(call));
        }
        for (Future f:
             futures) {
            System.out.println(f.get());
        }
        pool.shutdown();*/

        DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Callable<LocalDate> call=new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("2018-01-06",df);
            }
        };
        ExecutorService pool= Executors.newFixedThreadPool(10);
        List<Future<LocalDate>> futures=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            futures.add(pool.submit(call));
        }
        for (Future f:
                futures) {
            System.out.println(f.get());
        }
        pool.shutdown();
    }
}
