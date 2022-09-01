package com.liu.exception;

import com.liu.base.BaseResult;
import com.liu.constant.ResultData;

/**
 * @author zizai
 * @since 2022/9/1 11:19
 **/
public class ServiceException extends RuntimeException{


    private static final long serialVersionUID = 1L;

    private String errorCode;

    /**
     * constructors
     */

    public ServiceException() {
        super();
    }

    public ServiceException(BaseResult baseResult) {
        super(baseResult.getMessage());
        this.errorCode = baseResult.getCode();
    }

    public ServiceException(ResultData resultData) {
        super(resultData.getMessage());
        this.errorCode = resultData.getStatus();
    }

    public ServiceException(BaseResult baseResult, String... params) {
        super(baseResult.getMessage(params));
        this.errorCode = baseResult.getCode();
    }

    public ServiceException(String code, String message) {
        super(message);
        this.errorCode = code;
    }

    public ServiceException(BaseResult baseResult, Throwable cause) {
        super(baseResult.getMessage(), cause);
        this.errorCode = baseResult.getCode();
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(BaseResult baseResult, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(baseResult.getMessage(), cause, enableSuppression, writableStackTrace);
        this.errorCode = baseResult.getCode();
    }

    /**
     * getters and setters
     */

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
