package org.zt25.tools;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * 提供日期相关的通用方法
 *
 * <p>
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
public final class DateUtils {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final ZoneOffset BEIJING = ZoneOffset.of("+8");
    private static final int MILLISECONDS_OF_A_DAY = 86_400_000;
    public static final int MILLISECONDS_OF_A_MINUTE = 60_000;

    /**
     * 从一个字符串形式的日期数据中获得时间戳（毫秒）<p><p>
     * 日期格式必须为 <code>yyyy-MM-dd HH:mm:ss</code>，如 2018-08-08 18:08:08
     *
     * @param dateString 字符串形式的日期数据
     * @return 时间戳
     */
    public static long getEpochMilliFrom(String dateString) {
        if (dateString == null) {
            throw new IllegalArgumentException("日期不能为空");
        }
        dateString = dateString.trim();
        if (dateString.length() != 10 && dateString.length() != 19) {
            throw new IllegalArgumentException(String.format("日期(%s)的格式不正确，正确的格式应为yyyy-MM-dd或yyyy-MM-dd HH:mm:ss，如2018-08-08或2018-08-08 18:08:08", dateString));
        }
        if (dateString.length() == 10) {
            dateString += " 00:00:00";
        }
        try {
            return DATE_TIME_FORMATTER.parse(dateString, LocalDateTime::from).toEpochSecond(BEIJING) * 1000;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(String.format("日期(%s)的格式不正确，正确的格式应为yyyy-MM-dd或yyyy-MM-dd HH:mm:ss，如2018-08-08或2018-08-08 18:08:08", dateString));
        }
    }

    /**
     * 获得七天之前的时间戳<p><p>
     * 从现在的时间点开始算起，比如现在是2018-11-18 10:47:10，那么返回的就是2018-11-11 10:47:10的时间戳
     *
     * @return 七天之前的时间戳
     */
    public static long getTimestampOfSevenDaysAgo() {
        return minus(System.currentTimeMillis(), 7);
    }

    /**
     * 计算指定的时间戳之前<code>day</code>天的时间点的时间戳
     *
     * @param timestamp 时间戳，毫秒级
     * @param day       天数
     * @return 结果
     */
    public static long minus(long timestamp, int day) {
        return timestamp - day * MILLISECONDS_OF_A_DAY;
    }

    /**
     * 把时间戳转换为字符串形式的日期，日期格式为 <code>yyyy-MM-dd HH:mm:ss</code>
     *
     * @param epochMilli 时间戳
     * @return 格式化过后的日期字符串
     */
    public static String convertFrom(long epochMilli) {
        return DATE_TIME_FORMATTER.format(LocalDateTime.ofEpochSecond(epochMilli / 1000, 0, BEIJING));
    }

    /**
     * 把日期对象转换为字符串形式的日期，日期格式为 <code>yyyy-MM-dd HH:mm:ss</code>
     *
     * @param date 日期对象
     * @return 格式化过后的日期字符串
     */
    public static String convertFrom(Date date) {
        return DATE_TIME_FORMATTER.format(LocalDateTime.ofEpochSecond(date.getTime() / 1000, 0, BEIJING));
    }

    public static String getStringOfSevenDaysAgo() {
        return convertFrom(getTimestampOfSevenDaysAgo());
    }

    /**
     * 该类不期望被实例化,也不期望被继承
     */
    private DateUtils() {
        throw new AssertionError("该类不期望被实例化,也不期望被继承");
    }
}
