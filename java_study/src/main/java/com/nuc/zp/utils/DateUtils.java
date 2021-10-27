package com.nuc.zp.utils;

import javax.validation.constraints.NotBlank;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {

    private static final ThreadLocal<DateFormat> timeFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmss");
        }
    };

    private static final ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };


    public static String getCurrentTime() {
        return timeFormat.get().format(new Date());
    }

    /**
     * 获取上n个小时整点小时时间
     */
    public static String getLastHourTime(int n) {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.HOUR_OF_DAY, ca.get(Calendar.HOUR_OF_DAY) - n);
        return timeFormat.get().format(ca.getTime());
    }

    /**
     * 获取当前时间的整点小时时间
     */
    public static String getCurrHourTime() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        return timeFormat.get().format(ca.getTime());
    }

    /**
     * 获取当前时间的整点分钟时间
     */
    public static String getCurrMinuteTime() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.SECOND, 0);
        return timeFormat.get().format(ca.getTime());
    }

    /**
     * 获取上n个分钟整点分钟时间
     */
    public static String getLastMinuteTime(int n) {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MINUTE, ca.get(Calendar.MINUTE) - n);
        return timeFormat.get().format(ca.getTime());
    }


    /**
     * 获取两个时间节点间隔天数
     */
    public static long intervalDays(String startTime, String stopTime) {
        try {
            long start = dateFormat.get().parse(startTime).getTime();
            long end = dateFormat.get().parse(stopTime).getTime();
            return (end - start) / (1000L * 60 * 60 * 24);
        } catch (ParseException e) {
            throw new RuntimeException("时间转换失败");
        }
    }

    public static Date getWeektargetday(int index, int interval) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (0 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, 0);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day + index + 7 * interval);
        return cal.getTime();
    }

    public static Date getIntervalOneDay(Date date, int interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, interval);
        date = calendar.getTime();
        return date;
    }

    public static long getTargetDayZeroPoint(long current) {
        if (current == 0) {
            return 0;
        }
        return (current - (current + TimeZone.getDefault().getRawOffset()) % (1000 * 3600 * 24)) / 1000;
    }

    public static long getTargetDayLastPoint(long current) {
        if (current == 0) {
            return 0;
        }
        return ((current - (current + TimeZone.getDefault().getRawOffset()) % (1000 * 3600 * 24)) +
                24 * 60 * 60 * 1000 - 1) / 1000;
    }

    /**
     * get Hour Of Day
     */
    public static int getHourOfDay() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    /**
     * get Minute Of Day
     */
    public static int getMinuteOfDay() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    /**
     * get Second Of Day
     */
    public static int getSecondOfDay() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }

    /**
     * 时间戳转换
     */
    public static String timestampConvertStr(long timestamp) {
        return timeFormat.get().format(timestamp);
    }

}
