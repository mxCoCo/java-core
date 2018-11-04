package com.newFeture.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatThreadLocal {
    private final static ThreadLocal<DateFormat> df=new ThreadLocal<DateFormat>(){
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static Date convert(String source)throws Exception{
        return df.get().parse(source);
    }
}
