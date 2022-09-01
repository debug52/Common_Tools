package com.liu.tools;

import com.liu.constant.Mark;
import com.liu.enums.CommonResultEnum;
import com.liu.enums.TimePartitionEnum;
import com.liu.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zizai
 * @since 2022/9/1 11:16
 * 日期转换工具类
 **/
public class DateUtils {


    /**
     * defult 时间格式  年月日
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    public static final String DEFAULT_MONTH_PATTERN = "yyyy-MM";

    public static final String DATE_PATTERN_8 = "yyyyMMdd";

    /**
     * defult 时间格式 年月日  时分秒
     */
    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyy年mm月dd日
     */
    public static final String DATETIME_PATTERN = "yyyy年MM月dd日";

    /**
     * 年份日期相关
     */
    private static final String YEAR_START_SUFFIX = "-01-01 00:00:00";
    private static final String YEAR_END_SUFFIX = "-12-31 23:59:59";
    public static final String TIME_BEGIN_SUFFIX = " 00:00:00";
    public static final String TIME_END_SUFFIX = " 23:59:59";
    private static final int TIME_INITIAL_VALUE = 0;

    /**
     * 字符串转换date，yyyy年MM月dd日
     */
    public static String parseStringDate(String dateString) {
        if (StringUtils.isBlank(dateString)) {
            return null;
        }
        Date date = DateUtils.parseDateTime(dateString);

        return new SimpleDateFormat(DATETIME_PATTERN).format(date);
    }

    /**
     * 字符串格式转换Date
     */
    public static Date parseDateTime(String dateString, String parsePattern) throws ServiceException {
        SimpleDateFormat format = new SimpleDateFormat(parsePattern);

        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            throw new ServiceException(CommonResultEnum.COMMON_INVALID_DATE_FORMAT, dateString);
        }
    }

    /**
     * 字符串格式转换Date 年月日
     */
    public static Date parseDate(String dateString) {
        return parseDateTime(dateString, DEFAULT_DATE_PATTERN);
    }

    /**
     * 字符串格式转换Date 年月日时分秒
     */
    public static Date parseDateTime(String dateString) {
        return parseDateTime(dateString, DEFAULT_DATETIME_PATTERN);
    }

    /**
     * Date 转成指定pattern 格式字符串
     */
    public static String formatDateTime(Date date, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * Date 转成指定 默认 年月日 格式字符串
     */
    public static String formatDate(Date date) {
        return formatDateTime(date, DEFAULT_DATE_PATTERN);
    }

    /**
     * Date 转成指定 默认 年月日 时分秒 格式字符串
     */
    public static String formatDateTime(Date date) {
        return formatDateTime(date, DEFAULT_DATETIME_PATTERN);
    }

    /**
     * 获取当前日期
     */
    public static Date getSysDate() {
        return new Date();
    }

    /**
     * 当前日期之前的日期
     */
    public static String getBeforeDate(Date dateTime, int day) {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        Calendar c = Calendar.getInstance();
        c.setTime(dateTime);
        c.add(Calendar.DAY_OF_MONTH, -day);
        return format.format(c.getTime());
    }

    /**
     * 当前日期之前的日期
     */
    public static Date getBeforeMinute(Date dateTime, int minutes) {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        Calendar c = Calendar.getInstance();
        c.setTime(dateTime);
        c.add(Calendar.MINUTE, -minutes);
        return c.getTime();
    }


    /**
     * 当前日期之后的日期
     */
    public static String getAfterDate(Date dateTime, int day) {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        Calendar c = Calendar.getInstance();
        c.setTime(dateTime);
        c.add(Calendar.DAY_OF_MONTH, day);
        return format.format(c.getTime());
    }

    /**
     * 传入年，返回年份开始时间
     */
    public static Date getYearStartTime(int year) {
        String startTime = year + YEAR_START_SUFFIX;
        return parseDateTime(startTime, DEFAULT_DATETIME_PATTERN);
    }

    /**
     * 传入年，返回年份结束时间
     */
    public static Date getYearEndTime(int year) {
        String endTime = year + YEAR_END_SUFFIX;
        return parseDateTime(endTime, DEFAULT_DATETIME_PATTERN);
    }

    /**
     * 传入时间段，返回相差天数
     */
    public static int getTimeDifference(Date startTime, Date endTime) {
        final long DAY_MILLISECOND = 1000 * 3600 * 24;

        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        long start = cal.getTimeInMillis();
        cal.setTime(endTime);
        long end = cal.getTimeInMillis();
        long between_days = (end - start) / DAY_MILLISECOND;

        return (int) between_days;
    }

    /**
     * 传入时间段（String），返回相差天数
     */
    public static int getTimeDifference(String startDate, String endDate, String format) {
        Date startTime = parseDateTime(startDate, format);
        Date endTime = parseDateTime(endDate, format);
        return getTimeDifference(startTime, endTime);
    }

    /**
     * 获取某一天的开始时间,精确到毫秒
     */
    public static Date getDayStartTime(Date dayTime) throws ServiceException {
        if (null == dayTime) {
            throw new ServiceException(CommonResultEnum.COMMON_INVALID_DATA);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dayTime);
        calendar.set(Calendar.HOUR_OF_DAY, TIME_INITIAL_VALUE);
        calendar.set(Calendar.MINUTE, TIME_INITIAL_VALUE);
        calendar.set(Calendar.SECOND, TIME_INITIAL_VALUE);
        calendar.set(Calendar.MILLISECOND, TIME_INITIAL_VALUE);
        Date dayStartTime = calendar.getTime();

        return dayStartTime;
    }


    /**
     * 比较两个日期的大小
     */
    public static int compare(Date date1, Date date2) throws ServiceException {
        if (date1 == null && date2 == null) {
            throw new ServiceException(CommonResultEnum.COMMON_EMPTY_PARAM);
        }

        if (date1 == null) {
            return Mark.LESS_THAN;
        }

        if (date2 == null) {
            return Mark.GREATER_THAN;
        }

        if (date1.equals(date2)) {
            return Mark.EQUALS;
        }

        return date1.before(date2) ? Mark.LESS_THAN : Mark.GREATER_THAN;
    }

    /**
     * 传入日期，获取一年的第几天
     *
     * @param date
     */
    public static int getDaysInYear(LocalDate date) {

        if (null == date) {
            return Mark.EQUALS;
        }
        int yearDays = date.get(ChronoField.DAY_OF_YEAR);

        return yearDays;
    }


    /**
     * 获取日期区间所有年份
     */
    public static List<String> getTimePartitions(Date start, Date end, TimePartitionEnum timePartition) {
        if (start == null || end == null || timePartition == null) {
            throw new ServiceException(CommonResultEnum.COMMON_EMPTY_PARAM);
        }

        if (start.after(end)) {
            throw new ServiceException(CommonResultEnum.COMMON_INVALID_PARAM);
        }

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(start);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(end);

        String pattern = timePartition.getPattern();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        List<String> timePartitionList = new ArrayList<>();
        while (!startCal.after(endCal)) {
            timePartitionList.add(simpleDateFormat.format(startCal.getTime()));
            startCal.add(timePartition.getCalendarConst(), BigDecimal.ONE.intValue());
        }

        return timePartitionList;
    }

    /**
     * 计算儒历日期
     *
     * @param date 格式：2020/1/1或者2020-1-1
     * @return 整数，代表当前日期是当年第几天，2020-1-1的返回是2020001
     */
    public static int calculateRU(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int dayOfYear = instance.get(Calendar.DAY_OF_YEAR);
        int year = instance.get(Calendar.YEAR) * 1000;
        return year + dayOfYear;
    }

    /**
     * 根据年儒历日期返回日期
     *
     * @param YearAndDay，入参2020001
     * @throws ParseException
     * @return，返回2020-1-1
     */
    public static String CalculateDate(String YearAndDay) {
        Calendar instance = Calendar.getInstance();
        String year = YearAndDay.substring(Mark.FIRST_INDEX, 4);
        String days = YearAndDay.substring(4);
        instance.set(Calendar.YEAR, Integer.valueOf(year));
        instance.set(Calendar.DAY_OF_YEAR, Integer.valueOf(days));
        Date time = instance.getTime();
        return DateUtils.formatDate(time);
    }

    private static final String DATE_FORMAT_EXPRESSION =
            "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$";

    private static final String DATETIME_FORMAT_EXPRESSION =
            "((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)) ([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";

    /**
     * 格式校验
     *
     * @param date yyyy-MM-dd
     * @return
     */
    public static boolean isDate(String date) {
        Pattern pat = Pattern.compile(DATE_FORMAT_EXPRESSION);
        Matcher mat = pat.matcher(date);
        boolean dateType = mat.matches();
        return dateType;
    }

    /**
     * 格式验证
     *
     * @param date yyyy-MM-dd HH:mm:ss格式
     * @return
     */
    public static boolean isDateTime(String date) {
        Pattern pat = Pattern.compile(DATETIME_FORMAT_EXPRESSION);
        Matcher mat = pat.matcher(date);
        boolean dateType = mat.matches();
        return dateType;
    }

    /**
     * 获取指定格式的当前时间字符串
     *
     * @param timestamp 时间
     * @param format    时间格式
     * @return 返回指定格式的当前时间字符串
     */
    public static String getTimestampStr(Timestamp timestamp, String format) {
        if (format == null) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        // 获取格式化日期类
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // 获取当前时间的字符串格式
        String dateStr = sdf.format(timestamp);
        return dateStr;
    }

    /**
     * 获取指定格式的当前时间字符串
     *
     * @param format 时间格式
     * @return 返回指定格式的当前时间字符串
     */
    public static String getCurrentTimestampStr(String format) {
        if (format == null) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        // 获取当前时间
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        // 获取格式化日期类
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // 获取当前时间的字符串格式
        String dateStr = sdf.format(currentDate);
        return dateStr;
    }

    /**
     * 获取当年第一天
     *
     * @return
     */
    public static Timestamp getCurrentTimestampYear() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取一年中最早的一天
     *
     * @param millis
     * @return
     */
    public static Timestamp getYearBeginTime(long millis) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取一年中的最后一天
     *
     * @param millis
     * @return
     */
    public static Timestamp getYearEndTime(long millis) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        c.set(Calendar.MONTH, 11);
        // 设置日期为本月最大日期
        c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取日期最早一天
     *
     * @param millis
     * @return
     */
    public static Timestamp getMonthBeginTime(long millis) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return new Timestamp(c.getTimeInMillis());
    }

    public static Timestamp getMonthBeforeMonth(long millis, int sub) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        c.add(Calendar.MONTH, sub);
        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取日期最后一天
     *
     * @param millis
     * @return
     */
    public static Timestamp getMonthEndTime(long millis) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        // 设置日期为本月最大日期
        c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return new Timestamp(c.getTimeInMillis());
    }

    public static Timestamp getCurrentPastTimestamp(int past, int calendarType) {
        Long times = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(times);
        calendar.set(calendarType, calendar.get(calendarType) - past);
//        calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-past);

//        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static Timestamp getPastTimestamp(Long times, int past, int calendarType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(times);
        calendar.set(calendarType, calendar.get(calendarType) - past);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static Timestamp getPastTimestampBegin(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static Timestamp getPastTimestampBegin(long millis, int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取当天最早值
     *
     * @return
     */
    public static Timestamp getCurrentTimestampBegin() {
        return new Timestamp(getCurrentLongBegin());
    }

    public static long getCurrentLongBegin() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    /**
     * 获取当前最后时间
     *
     * @return
     */
    public static Timestamp getCurrentTimestampEnd() {
        return new Timestamp(getCurrentLongEnd());
    }

    public static long getCurrentLongEnd() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTimeInMillis();
    }

    public static long getTimeLongEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTimeInMillis();
    }

    public static Timestamp getStringTimestamp(String dateStr, String format) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        if (StringUtils.isBlank(format)) {
            format = DEFAULT_DATETIME_PATTERN;
        }
        if (dateStr.length() == 10) {
            format = DEFAULT_DATE_PATTERN;
        } else if (dateStr.length() == 16) {
            format = DEFAULT_MONTH_PATTERN;
        } else if (dateStr.length() == 19) {
            format = DEFAULT_DATETIME_PATTERN;
        }
        // 获取格式化日期类
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // 创建Date对象
        Date date = null;
        try {
            // 将dateStr转换成指定格式的时间
            date = sdf.parse(dateStr);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // 生成Timestamp
        Timestamp ts = new Timestamp(date.getTime());
        return ts;
    }



}
