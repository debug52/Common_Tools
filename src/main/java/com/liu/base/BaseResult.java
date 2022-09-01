package com.liu.base;

import org.apache.commons.lang3.ArrayUtils;

import java.text.MessageFormat;

/**
 * @author zizai
 * @since 2022/9/1 11:22
 * 返回对象接口
 **/
public interface BaseResult {
    String getCode();

    String getMessage();

    default String getMessage(String... params) {
        if (ArrayUtils.isEmpty(params)) {
            return getMessage();
        }

        return MessageFormat.format(this.getMessage(), params);
    }
}
