package com.liu.constant;

import com.liu.base.BaseResult;
import com.liu.enums.CommonResultEnum;
import com.liu.exception.ServiceException;

import java.io.Serializable;

/**
 * @author zizai
 * @since 2022/9/1 13:10
 **/
public class ResultData implements Serializable {

    private static final long serialVersionUID = 2L;

    /**
     * 状态
     *
     * @see CommonResultEnum
     */
    private String status;

    /**
     * 信息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    /**
     * constructors
     */

    private ResultData() {
    }

    private ResultData(BaseResult baseResult) {
        this.status = baseResult.getCode();
        this.message = baseResult.getMessage();
    }

    private ResultData(String code, String message) {
        this.status = code;
        this.message = message;
    }

    private ResultData(ServiceException e) {
        this.status = e.getErrorCode();
        this.message = e.getMessage();
    }

    private ResultData(BaseResult baseResult, Object data) {
        this.status = baseResult.getCode();
        this.message = baseResult.getMessage();
        this.data = data;
    }

    private ResultData(String code, String message, Object data) {
        this.status = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 公共方法
     */

    public boolean success() {
        return CommonResultEnum.COMMON_SUCCESS.getCode().equals(status);
    }

    public <T> T dataAsResultType() {
        return (T) data;
    }

    /**
     * 静态方法
     */

    public static ResultData ok() {
        return new ResultData(CommonResultEnum.COMMON_SUCCESS);
    }

    public static ResultData ok(Object data) {
        return new ResultData(CommonResultEnum.COMMON_SUCCESS, data);
    }

    public static ResultData ok(BaseResult baseResult) {
        return new ResultData(baseResult);
    }

    public static ResultData ok(String code, String message) {
        return new ResultData(code, message);
    }

    public static ResultData ok(BaseResult baseResult, Object data) {
        return new ResultData(baseResult, data);
    }

    public static ResultData ok(String code, String message, Object data) {
        return new ResultData(code, message, data);
    }

    public static ResultData fail() {
        return new ResultData(CommonResultEnum.COMMON_SYS_ERROR);
    }

    public static ResultData fail(ServiceException e) {
        return new ResultData(e);
    }

    public static ResultData fail(BaseResult baseResult) {
        return new ResultData(baseResult);
    }

    public static ResultData fail(String code, String message) {
        return new ResultData(code, message);
    }

    public static ResultData fail(Object data) {
        return new ResultData(CommonResultEnum.COMMON_SYS_ERROR, data);
    }

    public static ResultData fail(BaseResult baseResult, Object data) {
        return new ResultData(baseResult, data);
    }

    public static ResultData fail(BaseResult baseResult, String message) {
        return new ResultData(baseResult.getCode(), message);
    }

    /**
     * getters and setters
     */

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{data:" + this.data +
                ",status:" + this.status +
                ",message:" + this.message +
                "}";
    }
}
