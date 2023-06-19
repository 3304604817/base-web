package com.base.common.exception;

public class BaseException extends RuntimeException {

    /**
     * 错误消息
     */
    private String errorMsg;

    public BaseException(String errorMsg){
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
