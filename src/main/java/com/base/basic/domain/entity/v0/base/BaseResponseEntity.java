package com.base.basic.domain.entity.v0.base;


public class BaseResponseEntity {
    public static String CODE_200 = "200";
    public static String CODE_302 = "302";

    public static String STATUS_SUCCESS = "sucess";
    public static String STATUS_FAIL = "fail";

    private String code;
    private String status;
    private Object body;

    private BaseResponseEntity(){}

    public BaseResponseEntity(String code, String status){
        this.code = code;
        this.status = status;
    }

    public BaseResponseEntity(String code, String status, Object body){
        this.code = code;
        this.status = status;
        this.body = body;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
