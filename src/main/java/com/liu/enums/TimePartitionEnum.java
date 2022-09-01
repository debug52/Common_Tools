package com.liu.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zizai
 * @since 2022/9/1 13:12
 **/
public enum TimePartitionEnum {


    YEAR("year", "年", "yyyy", Calendar.YEAR),
    MONTH("month", "月", "yyyy-MM", Calendar.MONTH),
    DAY("day", "日", "yyyy-MM-dd", Calendar.DAY_OF_MONTH),
    HOUR("hour", "小时", "yyyy-MM-dd HH", Calendar.HOUR_OF_DAY),
    MINUTE("minute", "分","yyyy-MM-dd HH:mm", Calendar.MINUTE);

    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 格式
     */
    private String pattern;

    /**
     * Calendar类常量
     */
    private int calendarConst;

    /* 公共方法 */

    /**
     * 根据code获取对象
     */
    public static TimePartitionEnum getByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }

        for (TimePartitionEnum timePartition: TimePartitionEnum.values()) {
            if (StringUtils.equals(code, timePartition.getCode())) {
                return timePartition;
            }
        }

        return null;
    }

    /**
     * 获取时间片类型列表
     * @return
     */
    public static Map<String, String> findTimePartitionList() {
        Map<String, String> map = new LinkedHashMap<>();
        for (TimePartitionEnum timePartitionEnum : TimePartitionEnum.values()) {
            map.put(timePartitionEnum.getCode(), timePartitionEnum.getName());
        }
        return map;
    }

    /* constructors */

    TimePartitionEnum(String code, String name, String pattern, int calendarConst) {
        this.code = code;
        this.name = name;
        this.pattern = pattern;
        this.calendarConst = calendarConst;
    }

    /* getters */

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getPattern() {
        return pattern;
    }

    public int getCalendarConst() {
        return calendarConst;
    }

}
