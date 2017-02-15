package com.example.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * function:关于时间转换的工具类
 * Email：yangchaozhi@outlook.com
 * @author vinko on 2017/2/7.
 */

public class TimeUtil {

    public static String getDate(long millisecond, String dateFormat) {
        Date date = new Date(millisecond);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String d = sdf.format(date);
        return d;
    }

    /**
     * 得到n天前(后)的日期 负数为n天前，正数为n天后
     *
     * @param days
     *            前后天数
     * @param dateFormat
     * @author 杨朝智
     * @return
     */
    public static String getOffsetDay(int days, String dateFormat) {
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DAY_OF_MONTH, days);

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        return sdf.format(cal.getTime());
    }

    /**
     * 得到n个月前(后)的日期 负数为n个月前，正数为n个月后
     *
     * @param months
     *            前后月数
     * @param dateFormat
     * @author 杨朝智
     * @return
     */
    public static String getOffsetMonth(int months, String dateFormat) {
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.MONTH, months);

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        return sdf.format(cal.getTime());
    }

    /**
     * 根据一个时间，返回指定时间当周周日的日期
     *
     * @param curDate
     *            用户指定时间  2016/10/10
     * @param dateFormat
     *            返回的时间格式  yyyy/MM/dd
     * @return
     */
    public static String getCurSatDay2Str(String curDate, String dateFormat) {

        SimpleDateFormat sdf=new SimpleDateFormat(dateFormat); //设置时间格式
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(curDate));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if(1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        cal.setFirstDayOfWeek(Calendar.SUNDAY);//设置一个星期的第一天
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        String imptimeBegin = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = sdf.format(cal.getTime());
        return imptimeEnd;
    }

    /**
     * 特定格式的String转Date
     *
     * @param format
     *            格式
     * @param dateString
     *            日期的String形式
     * @return
     */
    public static Date getStr2Date(String format, String dateString) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {

        }
        return date;
    }

    /**
     * Date转特定格式的String
     *
     * @param format
     *            格式
     * @param date
     *            日期
     * @return
     */
    public static String getDate2Str(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String str = sdf.format(date);
        return str;
    }

    /**
     * 两个时间之间的天数
     *
     * @param date1
     *            建议传较大日期
     * @param date2
     *            建议传较小日期
     * @param format
     *            统一的string格式
     * @return
     */
    public static long getTwoDateDistanceDays(String date1, String date2, String format) {
        if (date1 == null || date1.equals(""))
            return 0;
        if (date2 == null || date2.equals(""))
            return 0;
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat(format);
        Date date = null;
        Date mydate = null;
        try {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (Exception e) {
        }
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 时间转换 10:00:00
     **/
    public static String formatTime(String s) {
        if (s == null || s.length() < 1) {
            return "";
        } else {
            String m = s.replace(":", "");
            StringBuffer str = new StringBuffer(m);
            str.insert(2, ":");
            str.insert(5, ":");
            return str.toString();
        }
    }

    /**
     * 返回指定日期的星期数
     *
     * @param dateStr
     *            8位的日期格式 如：20160810
     * @return
     */
    public static String getWeekByDateStr(String dateStr) {

        final String dayNames[] = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        try {
            date = sdfInput.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return dayNames[dayOfWeek - 1];
    }

    /**
     * 获取当前日期
     * @return
     */
    public static String getTodayDateFormSystm(){
        return getTodayDateFormSystm("yyyy/MM/dd");
    }

    /**
     * 获取当前日期
     * @param sdf SimpleDateFormat 格式
     * @return
     */
    public static String getTodayDateFormSystm(String sdf){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(sdf);
        return format.format(date);
    }


    public static String getThisDateStr(){
        String thisDate = new java.sql.Date(new Date().getTime()).toString();
        return getTime(thisDate);
    }

    /**
     * calcThisWeek:本周
     *
     * @param calendar
     */
    public static String getThisWeekStartDateStr(GregorianCalendar calendar) {
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String d = new java.sql.Date(calendar.getTime().getTime()).toString();
        return getTime(d);
    }

    /**
     * calcThisMonth:本月
     *
     * @param calendar
     */
    public static String getThisMonthStartDateStr(GregorianCalendar calendar) {
        int dayOfMonth = calendar.get(GregorianCalendar.DATE);
        calendar.add(GregorianCalendar.DATE, -dayOfMonth + 1);
        String c = new java.sql.Date(calendar.getTime().getTime()).toString();
        return getTime(c);
    }

    /**
     * cal3Month:近三月
     *
     * @param calendar
     */
    public static String get3MonthStartDateStr(GregorianCalendar calendar) {
        calendar.set(calendar.get(GregorianCalendar.YEAR), calendar.get(GregorianCalendar.MONTH) - 3,
                calendar.get(GregorianCalendar.DATE));
        String b = new java.sql.Date(calendar.getTime().getTime()).toString();
        return getTime(b);
    }

    /**
     * cal3Month:近六月
     *
     * @param calendar
     */
    public static String get6MonthStartDateStr(GregorianCalendar calendar) {
        calendar.set(calendar.get(GregorianCalendar.YEAR), calendar.get(GregorianCalendar.MONTH) - 6,
                calendar.get(GregorianCalendar.DATE));
        String a = new java.sql.Date(calendar.getTime().getTime()).toString();
        return getTime(a);
    }

    /**
     * gettime:(时间拼接)
     */
    public static String getTime(String va1) {
        String year = va1.split("-")[0];
        String month = va1.split("-")[1];
        String day = va1.split("-")[2];
        return year + month + day;
    }

    public static String formatDate(String dateStr) {
        String year = dateStr.split("/")[0];
        String month = dateStr.split("/")[1];
        String day = dateStr.split("/")[2];
        return year + "-" + month + "-" + day;
    }

    public static String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String d = sdf.format(date);
        return d;
    }

    public static String getCurDayAfterDate(String currDate){
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date=new SimpleDateFormat("yyyy/MM/dd").parse(currDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE,day);
        String afterDayStr = new SimpleDateFormat("yyyy/MM/dd").format(c.getTime());
        return afterDayStr;
    }

    /**
     * 获取距现在某一小时的时刻
     *
     * @param format 格式化时间的格式
     * @param h 距现在的小时 例如：h=-1为上一个小时，h=1为下一个小时
     * @return  获取距现在某一小时的时刻
     */
    public static String getNextHour(String format, int h) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date();
        date.setTime(date.getTime() + h * 60 * 60 * 1000);
        return sdf.format(date);

    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return  提取字符串日期
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 按用户格式字符串距离今天的天数
     *
     * @param date 日期字符串
     * @param format 日期格式
     * @return  按用户格式字符串距离今天的天数
     */
    public static int countDays(String date, String format) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date, format));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;

    }

    public static String getLastYearCurDayStr() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return (c.get(Calendar.YEAR)-1)  + (c.get(Calendar.MONTH) + 1) + "-" +
                c.get(Calendar.DAY_OF_MONTH) ;
    }

}
