package com.andersonblough.tackle.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Bill Anderson-Blough (bill.andersonblough@gmail.com)
 */
public class BADateUtil {

    public static Date addDays(Date date, int days) {
        Calendar cal = getCalendar(date);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
    }

    public static Date sunday(Date date) {
        Calendar cal = getCalendar(date);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DAY_OF_YEAR, 1 - day);
        return cal.getTime();
    }

    public static Date firstDayOfWeek(Date date) {
        Calendar cal = getCalendar(date);
        while (cal.get(Calendar.DAY_OF_WEEK) > Calendar.SUNDAY) {
            cal.add(Calendar.DAY_OF_YEAR, -1);
        }
        return cal.getTime();
    }

    public static Date lastDayOfWeek(Date date) {
        Calendar cal = getCalendar(date);
        while (cal.get(Calendar.DAY_OF_WEEK) < Calendar.SATURDAY) {
            cal.add(Calendar.DAY_OF_YEAR, 1);
        }
        return cal.getTime();
    }

    public static String dayOfWeek(Date date) {
        Calendar calendar = getCalendar(date);
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault());
    }

    public static String dayOfMonth(Date date) {
        Calendar calendar = getCalendar(date);
        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }

    public static int weekday(Date date) {
        Calendar calendar = getCalendar(date);
        return (calendar.get(Calendar.DAY_OF_WEEK) - 1);
    }

    public static Date nextWeek(Date date) {
        Calendar cal = getCalendar(date);
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        return cal.getTime();
    }

    public static Date previousWeek(Date date) {
        Calendar cal = getCalendar(date);
        cal.add(Calendar.WEEK_OF_YEAR, -1);
        return cal.getTime();
    }

    public static Date getStartOfWeek(Date date) {
        Calendar calendar = getCalendar(date);
        calendar.add(Calendar.DAY_OF_YEAR, -(calendar.get(Calendar.DAY_OF_WEEK) - 1));
        return getStartOfDay(calendar.getTime());
    }

    public static Date getEndOfWeek(Date date) {
        Calendar calendar = getCalendar(date);
        calendar.add(Calendar.DAY_OF_YEAR, (7 - calendar.get(Calendar.DAY_OF_WEEK)));
        return getEndOfDay(calendar.getTime());
    }

    public static Date getStartOfDay(Date date) {
        Calendar calendar = getCalendar(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndOfDay(Date date) {
        Calendar calendar = getCalendar(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static boolean isLastWeek(Date currentDate, Date newDate) {
        Date sunday = getStartOfWeek(currentDate);
        return newDate.before(sunday);
    }

    public static boolean isNextWeek(Date currentDate, Date newDate) {
        Date saturday = getEndOfWeek(currentDate);
        return newDate.after(saturday);
    }

    private static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}
