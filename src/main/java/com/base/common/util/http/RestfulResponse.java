package com.base.common.util.http;

public class RestfulResponse {
    /**
     * 接口调用状态
     */
    private int code;

    /**
     * 接口返回报文
     */
    private String responseBody;

    public RestfulResponse() {}
    public RestfulResponse(int code, String responseBody) {
        this.code = code;
        this.responseBody = responseBody;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}
