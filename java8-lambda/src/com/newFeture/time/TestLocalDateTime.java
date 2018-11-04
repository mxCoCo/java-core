package com.newFeture.time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

public class TestLocalDateTime {
    //1、LocalDateTime,LocalDate,LocalTime
    @Test
    public void test1(){
        LocalDateTime ldt=LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2=LocalDateTime.of(2018,1,1,4,5,6);
        System.out.println(ldt2);

        LocalDateTime ldt3=ldt.plusMonths(2).minusYears(1);
        System.out.println(ldt3);
    }

    //2、instant：时间戳(以Unix元年:1970年-1月-1日 00:00:00到某个时间日之间的毫秒值)
    @Test
    public void test2(){
        Instant instant = Instant.now();//默认获取UTC时区(差8小时)
        Instant instant1 = Instant.ofEpochSecond(60);
        System.out.println(instant);
        System.out.println(instant.toEpochMilli());
        System.out.println(instant1);

        OffsetDateTime odt = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);
    }

    /**
     * 3、Duration:计算两个时间之间的间隔
     *    Period:计算两个日期之间的间隔
     */
    @Test
    public void test3(){
        Instant ins1=Instant.now();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant ins2=Instant.now();
        Duration duration = Duration.between(ins1, ins2);
        System.out.println(duration.toMillis());
        System.out.println("-----------------------------");
        LocalTime lt1=LocalTime.now();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime lt2=LocalTime.now();
        Duration duration2 = Duration.between(lt1, lt2);
        System.out.println(duration2.toMillis());
    }
    @Test
    public void test4(){
        LocalDate ld1=LocalDate.of(2017,8,6);
        LocalDate ld2=LocalDate.now();
        Period period = Period.between(ld1, ld2);
        System.out.println(period);
        System.out.println("---------------------");
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }

    //TemporalAdjuster:时间校验器
    @Test
    public void test5(){
        LocalDateTime ldt1=LocalDateTime.now();
        System.out.println("当前时间："+ldt1);
        LocalDateTime ldt2 = ldt1.withDayOfMonth(10);
        System.out.println("设定天数后的时间："+ldt2);
        LocalDateTime ldt3 = ldt1.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        System.out.println("当前时间为准的下个周六："+ldt3);

        //自定义下一个工作日
        LocalDateTime ldt5 = ldt1.with((l) -> {
            LocalDateTime ldt4 = (LocalDateTime) l;
            DayOfWeek dayOfWeek = ldt4.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return ldt4.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);
            }
        });
        System.out.println("下个工作日："+ldt5);
    }

    //DateTimeFormatter:格式化时间和日期
    @Test
    public void test6(){
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt=LocalDateTime.now();

        String strDate = ldt.format(dtf);
        System.out.println(strDate);
        System.out.println("------------------------");

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String strDate2 = ldt.format(dtf2);
        System.out.println(strDate2);
        System.out.println("------------------------");

        LocalDateTime newDate = ldt.parse(strDate2, dtf2);
        System.out.println(newDate);
    }

    //ZonedDateTime,ZonedDate,ZonedTime
    @Test
    public void test7(){
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        zoneIds.forEach(System.out::println);
    }
    @Test
    public void test8(){
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Singapore"));
        System.out.println(localDateTime);

        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Singapore"));
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("Asia/Singapore"));
        System.out.println(zdt);
    }
}
