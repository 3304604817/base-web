package com.base.common.exception.handler;

import com.base.common.exception.BaseException;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity exceptionHandler(HttpServletRequest req, BaseException e){
        return new ResponseEntity(e.getErrorMsg(), HttpStatus.OK);
    }
}
