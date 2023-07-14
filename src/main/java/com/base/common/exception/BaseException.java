package com.base.common.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {

    /**
     * 请求状态默认500服务器内部错误
     */
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

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
