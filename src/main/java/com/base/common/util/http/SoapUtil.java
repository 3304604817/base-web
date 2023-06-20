package com.base.common.util.http;

import okhttp3.*;

import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class SoapUtil {

    /**
     * 常见错误：出站 SOAP 消息失败，错误代码为“VersionMismatch”
     * 这是由于配置的 ServiceNow 出站 SOAP 消息和接收消息的端点之间的 SOAP 版本不匹配。
     * SOAP 1.1 = xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
     * SOAP 1.2 = xmlns:soap="http://www.w3.org/2003/05/soap-envelope/"
     */

    /**
     * @param soapUrl 接口地址
     * @param Username 账号
     * @param Password 密码
     * @param content 消息体
     */
    public static Response send(String soapUrl, String Username, String Password, String content) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        return SoapUtil.processRequest(client, soapUrl, Username, Password, content);
    }

    /**
     *
     * @param soapUrl 接口地址
     * @param Username 账号
     * @param Password 密码
     * @param content 消息体
     * @param readTimeout 读取超时时间
     * @param writeTimeout 写的超时时间
     * @param connectTimeout 连接超时时间
     * @return
     * @throws IOException
     */
    public static Response send(String soapUrl, String Username, String Password, String content, long readTimeout, long writeTimeout, long connectTimeout) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout,TimeUnit.SECONDS)
                .connectTimeout(connectTimeout,TimeUnit.SECONDS)
                .build();
        return SoapUtil.processRequest(client, soapUrl, Username, Password, content);
    }

    /**
     * 处理接口调用
     */
    private static Response processRequest(OkHttpClient client, String soapUrl, String Username, String Password, String content) throws IOException {
        MediaType mediaType = MediaType.parse("text/xml");
        RequestBody body = RequestBody.create(mediaType, content);
        String authorization =  Base64.getEncoder().encodeToString(
                new StringBuilder(Username).append(":").append(Password).toString().getBytes()
        );
        Request request = new Request.Builder()
                .url(soapUrl)
                .method("POST", body)
                .addHeader("Content-Type", "text/xml")
                .addHeader("Authorization", "Basic " + authorization)
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }
}
