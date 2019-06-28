package com.nuc.zp.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    /**
     * 得到目标周的某一天
     *
     * @param index    （0-6分别表示周一至周六）
     * @param interval （-1表示上周，0表示本周，1表示下周，以此类推）
     * @return
     */
    private static Date getWeektargetday(int index, int interval) {
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

    /**
     * 获取目标日期的间隔天
     *
     * @param date     目标日期
     * @param interval 间隔天数
     * @return
     */
    private static Date getIntervalOneDay(Date date, int interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, interval);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取目标日期0点
     *
     * @param current 目标日期时间戳
     * @return
     */
    private static long getTargetDayZeroPoint(long current) {
        if (current == 0) {
            return 0;
        }
        return (current - (current + TimeZone.getDefault().getRawOffset()) % (1000 * 3600 * 24)) / 1000;
    }


    /**
     * 获取目标日期23：59：59
     *
     * @param current 目标日期
     * @return
     */
    private static long getTargetDay235959Point(long current) {
        if (current == 0) {
            return 0;
        }
        return ((current - (current + TimeZone.getDefault().getRawOffset()) % (1000 * 3600 * 24)) +
                24 * 60 * 60 * 1000 - 1) / 1000;
    }


    public static void main(String[] args) {
        System.out.println(getWeektargetday(0, 0));//获取本周周一
        System.out.println(getIntervalOneDay(new Date(), 1));//明天
        System.out.println(getTargetDayZeroPoint(System.currentTimeMillis()));//获取今日0点
        System.out.println(getTargetDay235959Point(System.currentTimeMillis()));//获取今日23：59：59
    }
}
