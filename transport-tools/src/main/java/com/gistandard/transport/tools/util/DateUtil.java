package com.gistandard.transport.tools.util;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author yujie
 * @ClassName DateUtil
 * @Description
 * @Version 1.0
 * @Date 2015-12-25
 */
public class DateUtil {

    public static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static String DATETIME_FORMAT2 = "yyyy-MM-dd HH:mm";
    private static String CST_DATE_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";

    private static String DATE_FORMAT = "yyyy-MM-dd";
    private static String DATE_FORMAT2 = "yyyyMMdd";
    public static Calendar calendar = Calendar.getInstance();

    public static Date createDate(int year, int month, int date) {
        calendar.set(year, month - 1, date);
        return calendar.getTime();
    }

    @SuppressWarnings("deprecation")
    public static String format(Date date) {
        return DateUtil.getYear(date) + "-" + DateUtil.getMonth(date) + "-" + DateUtil.getDate(date) + " " + date.getHours() + ":"
                + date.getMinutes() + ":" + date.getSeconds();
    }

    /**
     * 获取日期中的年
     *
     * @param date 日期
     * @return 年份
     */
    public static int getYear(Date date) {
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取日期中的月
     *
     * @param date 日期
     * @return 月份 (1~12)
     */
    public static int getMonth(Date date) {
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期(月)
     *
     * @param date 日期
     * @return 天
     */
    public static int getDate(Date date) {
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 获取星期天 (1(星期日)~7(星期六)
     *
     * @param date 日期
     * @return 天
     */
    public static int getDay(Date date) {
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return day;
    }

    public static Date clearTime(Date date) {
        Date d = DateUtil.createDate(DateUtil.getYear(date), DateUtil.getMonth(date), DateUtil.getDate(date));
        return d;
    }

    public static Date clone(Object date) {
        return clone((Date) date);
    }

    public static Date clone(Date date) {
        return new Date(date.getTime());
    }

    /**
     * 获取当前系统时间的格式化字符串
     *
     * @return String 形如yyyy-MM-dd hh:mm:ss的日期串
     */
    public static String getFormatCurrentTime() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(DATETIME_FORMAT);
        return df.format(date);
    }

    /**
     * 获取当前系统日期的格式化字符串
     *
     * @return String 形如yyyy-MM-dd的日期串
     */
    public static String getFormatCurrentDate() {
        return getFormatDate(new Date());
    }

    public static String getFormatCurrentDate2(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT2);
        if (date == null) {
            return "";
        } else {
            return df.format(date);
        }
    }

    /**
     * 返回格式化后的日期（不包含时间）
     *
     * @param date 要格式化的Date对象
     * @return 如果Date对象为null, 则返回一个空串，否则返回形如yyyy-MM-dd的字符串
     */
    public static String getFormatDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
        if (date == null) {
            return "";
        } else {
            return df.format(date);
        }
    }

    /**
     * 返回格式化后的日期（包含时间）
     *
     * @param date 要格式化的Date对象
     * @return 如果Date对象为null, 则返回一个空串，否则返回形如yyyy-MM-dd HH:mm:ss的字符串
     */
    public static String getFormatDate2(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATETIME_FORMAT);
        if (date == null) {
            return "";
        } else {
            return df.format(date);
        }
    }

    /**
     * 返回格式化后的日期（包含时间）
     *
     * @param date 要格式化的Date对象
     * @return 如果Date对象为null, 则返回一个空串，否则返回形如yyyy-MM-dd HH:mm的字符串
     */
    public static String getFormatDate3(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATETIME_FORMAT2);
        if (date == null) {
            return "";
        } else {
            return df.format(date);
        }
    }

    /**
     * 将一个形如 yyyy-MM-dd hh:mm:ss 表示日期的字符串解析为Date对象表示的日期
     *
     * @param dateStr 一个表示日期的字符串
     * @return 如果字符串为null或者空串则返回空串，否则返回解析字符串后得到的 Date对象。
     */
    public static Date parseDate(String dateStr) {
        if (dateStr == null || dateStr.equals("")) {
            return null;
        } else {
            SimpleDateFormat df = new SimpleDateFormat(DATETIME_FORMAT);
            ParsePosition pp = new ParsePosition(0);
            return df.parse(dateStr, pp);
        }

    }

    /**
     * 将一个形如EEE MMM dd HH:mm:ss z yyyy 表示日期的CST字符串解析为Date对象表示的日期
     *
     * @param dateStr 一个表示日期的CST字符串
     * @return 如果字符串为null或者空串则返回空串，否则返回解析字符串后得到的 Date对象。
     */
    public static Date parseCSTDate(String dateStr) {
        if (dateStr == null || dateStr.equals("")) {
            return null;
        } else {
            SimpleDateFormat df = new SimpleDateFormat(CST_DATE_FORMAT, Locale.US);
            ParsePosition pp = new ParsePosition(0);
            return df.parse(dateStr, pp);
        }

    }

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * -------------------------------------------主要是日期加减-------------------------------------------------------
     */
    public static final String C_TIME_PATTON_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String C_DATE_PATTON_DEFAULT = "yyyy-MM-dd";
    public static final int C_ONE_SECOND = 1000;
    public static final int C_ONE_MINUTE = 60 * C_ONE_SECOND;
    public static final long C_ONE_HOUR = 60 * C_ONE_MINUTE;
    public static final long C_ONE_DAY = 24 * C_ONE_HOUR;

    // 计算当前月份的最大天数
    public static int findMaxDayInMonth() {
        return findMaxDayInMonth(0, 0);
    }

    // 计算指定日期月份的最大天数
    public static int findMaxDayInMonth(Date date) {
        if (date == null) {
            return 0;
        }
        return findMaxDayInMonth(date2Calendar(date));
    }

    // 计算指定日历月份的最大天数
    public static int findMaxDayInMonth(Calendar calendar) {
        if (calendar == null) {
            return 0;
        }
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    // 计算当前年某月份的最大天数
    public static int findMaxDayInMonth(int month) {
        return findMaxDayInMonth(0, month);
    }

    // 计算某年某月份的最大天数
    public static int findMaxDayInMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        if (year > 0)
            calendar.set(Calendar.YEAR, year);
        if (month > 0)
            calendar.set(Calendar.MONTH, month - 1);
        return findMaxDayInMonth(calendar);
    }

    // Calendar 转换为 Date
    public static Date calendar2Date(Calendar calendar) {
        if (calendar == null)
            return null;
        return calendar.getTime();
    }

    // Date 转换为 Calendar
    public static Calendar date2Calendar(Date date) {
        if (date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    // 拿到默认格式的SimpleDateFormat
    public static SimpleDateFormat getSimpleDateFormat() {
        return getSimpleDateFormat(null);
    }

    // 拿到指定输出格式的SimpleDateFormat
    public static SimpleDateFormat getSimpleDateFormat(String format) {
        SimpleDateFormat sdf;
        if (format == null) {
            sdf = new SimpleDateFormat(C_TIME_PATTON_DEFAULT);
        } else {
            sdf = new SimpleDateFormat(format);
        }
        return sdf;
    }

    // 转换当前时间为默认格式
    public static String formatDate2Str() {
        return formatDate2Str(new Date());
    }

    // 转换指定时间为默认格式
    public static String formatDate2Str(Date date) {
        return formatDate2Str(date, C_DATE_PATTON_DEFAULT);
    }

    // 转换指定时间为指定格式
    public static String formatDate2Str(Date date, String format) {
        if (date == null)
            return null;
        if (format == null || format.equals(""))
            format = C_TIME_PATTON_DEFAULT;
        SimpleDateFormat sdf = getSimpleDateFormat(format);
        return sdf.format(date);
    }

    @SuppressWarnings("finally")
    public static Date formatDate2StrWithException(String dateStr) {
        Date resultTime = null;
        if (dateStr == null) {
            return resultTime;
        }

        try {
            SimpleDateFormat sdfDate = getSimpleDateFormat(C_DATE_PATTON_DEFAULT);
            resultTime = sdfDate.parse(dateStr, new ParsePosition(0));
        } catch (Exception e) {
            SimpleDateFormat sdfTime = getSimpleDateFormat(C_TIME_PATTON_DEFAULT);
            resultTime = sdfTime.parse(dateStr, new ParsePosition(0));
        } finally {
            return resultTime;
        }
    }

    // 转换默认格式的时间为Date
    public static Date formatStr2Date(String dateStr) {
        return formatStr2Date(dateStr, null);
    }

    // 转换指定格式的时间为Date
    public static Date formatStr2Date(String dateStr, String format) {
        if (dateStr == null)
            return null;

        if (format == null || format.equals(""))
            format = C_DATE_PATTON_DEFAULT;
        SimpleDateFormat sdf = getSimpleDateFormat(format);
        return sdf.parse(dateStr, new ParsePosition(0));
    }

    // 转换默认格式的时间为指定格式时间
    public static String formatDefault2Define(String dateStr, String defineFormat) {
        return formatSource2Target(dateStr, C_TIME_PATTON_DEFAULT, defineFormat);
    }

    // 转换源格式的时间为目标格式时间
    public static String formatSource2Target(String dateStr, String sourceFormat, String targetFormat) {
        Date date = formatStr2Date(dateStr, sourceFormat);
        return formatDate2Str(date, targetFormat);
    }

    // 计算当天是该年中的第几周
    public static int findWeeksNoInYear() {
        return findWeeksNoInYear(new Date());
    }

    // 计算指定日期是该年中的第几周
    public static int findWeeksNoInYear(Date date) {
        if (date == null)
            return 0;
        return findWeeksNoInYear(date2Calendar(date));
    }

    // 计算指定日历是该年中的第几周
    public static int findWeeksNoInYear(Calendar calendar) {
        if (calendar == null)
            return 0;
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    // 计算一年中的第几星期是几号
    public static Date findDateInWeekOnYear(int year, int weekInYear, int dayInWeek) {
        Calendar calendar = Calendar.getInstance();
        if (year > 0) {
            calendar.set(Calendar.YEAR, year);
        }
        calendar.set(Calendar.WEEK_OF_YEAR, weekInYear);
        calendar.set(Calendar.DAY_OF_WEEK, dayInWeek);

        return calendar.getTime();
    }

    // 当前周的第一天（星期天）
    public static String findFirstDayInCurrentWeek() {
        Date now = new Date();
        int weekNo = findWeeksNoInYear(now);
        int year = date2Calendar(now).get(Calendar.YEAR);
        return formatDate2Str(findDateInWeekOnYear(year, weekNo, Calendar.SUNDAY));
    }

    // 当前周的最后一天（星期六）
    public static String findLastDayInCurrentWeek() {
        Date now = new Date();
        int weekNo = findWeeksNoInYear(now);
        int year = date2Calendar(now).get(Calendar.YEAR);
        return formatDate2Str(findDateInWeekOnYear(year, weekNo, Calendar.SATURDAY));
    }

    //获取当前周的某天 如星期六  Calendar.SATURDAY
    public static String findDayInCurrentWeek(int dayInWeek) {
        Date now = new Date();
        int weekNo = findWeeksNoInYear(now);
        int year = date2Calendar(now).get(Calendar.YEAR);
        return formatDate2Str(findDateInWeekOnYear(year, weekNo, dayInWeek));
    }

    // 相对于当前日期的前几天(days < 0)或者后几天(days > 0)
    public static Date dayBefore2Date(int days) {
        Date date = new Date();
        return dayBefore2Object(days, null, date);
    }

    // 相对于当前日期的前几天(days < 0)或者后几天(days > 0)
    public static String dayBefore2Str(int days) {
        String string = formatDate2Str();
        return dayBefore2Object(days, null, string);
    }

    // 相对于当前日期的前几天(days < 0)或者后几天(days > 0)
    @SuppressWarnings("unchecked")
    public static <T extends Object> T dayBefore2Object(int days, String format, T instance) {
        Calendar calendar = Calendar.getInstance();
        if (days == 0) {
            return (T) getFormatCurrentDate();
        }

        if (format == null || format.equals("")) {
            format = C_DATE_PATTON_DEFAULT;
        }

        calendar.add(Calendar.DATE, days);
        if (instance instanceof Date) {
            return (T) calendar.getTime();
        } else if (instance instanceof String) {
            return (T) formatDate2Str(calendar2Date(calendar), format);
        }
        return null;
    }

    // 相对于指定日期的前几天(days < 0)或者后几天(days > 0)
    public static Date defineDayBefore2Date(Date date, int days) {
        Date dateInstance = new Date();
        return defineDayBefore2Object(date, days, null, dateInstance);
    }

    // 相对于指定日期的前几天(days < 0)或者后几天(days > 0)
    public static String defineDayBefore2Str(Date date, int days) {
        String stringInstance = formatDate2Str();
        return defineDayBefore2Object(date, days, C_DATE_PATTON_DEFAULT, stringInstance);
    }

    // 相对于指定日期的前几天(days < 0)或者后几天(days > 0)
    @SuppressWarnings("unchecked")
    public static <T extends Object> T defineDayBefore2Object(Date date, int days, String format, T instance) {
        if (date == null) {
            return null;
        }

        if (format == null || format.equals("")) {
            format = C_TIME_PATTON_DEFAULT;
        }

        Calendar calendar = date2Calendar(date);
        calendar.add(Calendar.DATE, days);
        if (instance instanceof Date) {
            return (T) calendar.getTime();
        } else if (instance instanceof String) {
            return (T) formatDate2Str(calendar2Date(calendar), format);
        }
        return null;
    }

    // 相对于当前日期的前几月(months < 0)或者后几月(months > 0)时间
    public static Date monthBefore2Date(int months) {
        Date date = new Date();
        return monthBefore2Object(months, null, date);
    }

    // 相对于当前日期的前几月(months < 0)或者后几月(months > 0)时间
    public static String monthBefore2Str(int months) {
        String string = formatDate2Str();
        return monthBefore2Object(months, null, string);
    }

    // 相对于当前日期的前几月(months < 0)或者后几月(months > 0)时间
    @SuppressWarnings("unchecked")
    public static <T extends Object> T monthBefore2Object(int months, String format, T instance) {
        if (months == 0) {
            return null;
        }

        if (format == null || format.equals("")) {
            format = C_DATE_PATTON_DEFAULT;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, months);

        if (instance instanceof Date) {
            return (T) calendar.getTime();
        } else if (instance instanceof String) {
            return (T) formatDate2Str(calendar2Date(calendar), format);
        }

        return null;
    }

    // 相对于指定日期的前几月(months < 0)或者后几月(months > 0)时间
    public static Date defineMonthBefore2Date(Date date, int months) {
        Date dateInstance = new Date();
        return defineMonthBefore2Object(date, months, null, dateInstance);
    }

    // 相对于指定日期的前几月(months < 0)或者后几月(months > 0)时间
    public static String defineMonthBefore2Str(Date date, int months) {
        String stringInstance = formatDate2Str();
        return defineMonthBefore2Object(date, months, null, stringInstance);
    }

    // 相对于指定日期的前几月(months < 0)或者后几月(months > 0)时间
    @SuppressWarnings("unchecked")
    public static <T extends Object> T defineMonthBefore2Object(Date date, int months, String format, T instance) {
        if (months == 0) {
            return null;
        }

        if (format == null || format.equals("")) {
            format = C_TIME_PATTON_DEFAULT;
        }

        Calendar calendar = date2Calendar(date);
        calendar.add(Calendar.MONTH, months);

        if (instance instanceof Date) {
            return (T) calendar.getTime();
        } else if (instance instanceof String) {
            return (T) formatDate2Str(calendar2Date(calendar), format);
        }

        return null;
    }


    // 计算两个日期直接差的天数
    public static int caculate2Days(Date firstDate, Date secondDate) {
        if (null != firstDate && null != secondDate) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(firstDate);
            int dayNum1 = calendar.get(Calendar.DAY_OF_YEAR);
            calendar.setTime(secondDate);
            int dayNum2 = calendar.get(Calendar.DAY_OF_YEAR);
            return dayNum1 - dayNum2;
        } else {
            return 0;
        }
    }

    //计算两个日期相差天数（返回相对差值，而非绝对值，firstDate早于secondDate返回负数，否则整数）
    public static int caculate2DaysRelative(Date firstDate, Date secondDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstDate);
        long calendarNum1 = calendar.getTimeInMillis();
        calendar.setTime(secondDate);
        long calendarNum2 = calendar.getTimeInMillis();
        return (int) ((calendarNum1 - calendarNum2) / C_ONE_DAY);
//		if (null != firstDate && null != secondDate )
//		{
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(firstDate);
//			int dayNum1 = calendar.get(Calendar.DAY_OF_YEAR);
//			calendar.setTime(secondDate);
//			int dayNum2 = calendar.get(Calendar.DAY_OF_YEAR);
//			return dayNum1 - dayNum2;
//		}
//		else
//		{
//			return 0;
//		}
    }

    // 计算两个日期直接差的天数
    public static int caculate2Days(Calendar firstCalendar, Calendar secondCalendar) {
        if (firstCalendar.after(secondCalendar)) {
            Calendar calendar = firstCalendar;
            firstCalendar = secondCalendar;
            secondCalendar = calendar;
        }

        long calendarNum1 = firstCalendar.getTimeInMillis();
        long calendarNum2 = secondCalendar.getTimeInMillis();
        return Math.abs((int) ((calendarNum1 - calendarNum2) / C_ONE_DAY));
    }

    /**
     * 获取昨天指定日期格式
     *
     * @return yestoday
     */
    public static String formatYestoday2Str() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date yestoday = calendar.getTime();
        return formatDate2Str(yestoday, C_DATE_PATTON_DEFAULT);
    }

    /**
     * 获取指定月份前一月份的第一天
     */
    public static String getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.add(Calendar.MONTH, -1);
        return formatDate2Str(calendar.getTime(), C_DATE_PATTON_DEFAULT);
    }

    /**
     * 获取指定月份前一月份的最后一天
     */
    public static String getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.add(Calendar.DATE, -1);
        return formatDate2Str(calendar.getTime(), C_DATE_PATTON_DEFAULT);
    }

    public static String getFirstDayOfCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int firstDay = calendar.getActualMinimum(Calendar.DATE);
        calendar.set(Calendar.DATE, firstDay);
        return formatDate2Str(calendar.getTime(), C_DATE_PATTON_DEFAULT);
    }

    public static String getLastDayOfCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int lastDay = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DATE, lastDay);
        return formatDate2Str(calendar.getTime(), C_DATE_PATTON_DEFAULT);
    }

    /**
     * 本周第几天，从周一开始
     *
     * @param day
     * @return
     */
    public static Date getDayInWeek(int day) {
        int mondayPlus = 0;
        Calendar cd = Calendar.getInstance();
        if (cd.get(Calendar.DAY_OF_WEEK) == 1) {
            mondayPlus = day - 7;

        } else {
            mondayPlus = day - cd.get(Calendar.DAY_OF_WEEK) + 1;

        }
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        return monday;

    }

    /**
     * 判断指定日期是否位于某个时间段内
     *
     * @param date      目标日期
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static boolean betweenTimeZone(Date date, Date beginDate, Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long currentMills = calendar.getTimeInMillis();
        calendar.setTime(beginDate);
        long beginMills = calendar.getTimeInMillis();
        calendar.setTime(endDate);
        long endMills = calendar.getTimeInMillis();
        return currentMills >= beginMills && currentMills <= endMills;
    }

    /**
     * 把日期格式的时间转换成webservice需要的时间
     * @param date
     */
    public static XMLGregorianCalendar getXMLGregorianCalendar(Date date) throws Exception
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        DatatypeFactory dtf = DatatypeFactory.newInstance();
        return dtf.newXMLGregorianCalendar(
                calendar.get(calendar.YEAR),
                calendar.get(calendar.MONTH)+1,
                calendar.get(calendar.DAY_OF_MONTH),
                calendar.get(calendar.HOUR),
                calendar.get(calendar.MINUTE),
                calendar.get(calendar.SECOND),
                calendar.get(calendar.MILLISECOND),
                calendar.get(calendar.ZONE_OFFSET)/(1000*60));
    }

    /**
     * 设置年份转换成webservice需要的时间
     * @param year
     */
    public static XMLGregorianCalendar getXMLGregorianCalendar(int year) throws Exception
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, 1, 1);
        DatatypeFactory dtf = DatatypeFactory.newInstance();
        return dtf.newXMLGregorianCalendar(
                calendar.get(calendar.YEAR),
                calendar.get(calendar.MONTH)+1,
                calendar.get(calendar.DAY_OF_MONTH),
                calendar.get(calendar.HOUR),
                calendar.get(calendar.MINUTE),
                calendar.get(calendar.SECOND),
                calendar.get(calendar.MILLISECOND),
                calendar.get(calendar.ZONE_OFFSET)/(1000*60));
    }
}
