package com.base.basic.domain.entity.v0.base;

import lombok.Data;

@Data
public class BaseResponseEntity {
    public static String CODE_200 = "200";
    public static String CODE_302 = "302";

    public static String STATUS_SUCCESS = "sucess";
    public static String STATUS_FAIL = "fail";

    private String code;
    private String status;
    private String errMsg;
    private Object body;

    private BaseResponseEntity(){}

    public BaseResponseEntity(String code, String status){
        this.code = code;
        this.status = status;
    }

    public BaseResponseEntity(String code, String status, String errMsg){
        this.code = code;
        this.status = status;
        this.errMsg = errMsg;
    }

    public BaseResponseEntity(String code, String status, Object body){
        this.code = code;
        this.status = status;
        this.body = body;
    }
}
