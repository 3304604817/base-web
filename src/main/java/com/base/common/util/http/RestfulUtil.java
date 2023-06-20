package com.base.common.util.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * @author gaoyang
 */
public class RestfulUtil {

    public static final String GET_METHOD = "GET";
    public static final String POST_METHOD = "POST";
    public static final String PUT_METHOD = "PUT";
    public static final String DELETE_METHOD = "DELETE";

    public static final String UTF8_CHARSET = "UTF-8";
    public static final String GBK_CHARSET = "GBK";

    /**
     * 设置连接超时，毫秒
     */
    public static final int CONNECT_TIME_OUT = 10 * 1000;
    /**
     * 设置读取超，毫秒
     */
    public static final int READ_TIMEOUT = 10 * 1000;

    /**
     * GET请求
     * @param path 请求路径
     * @param uriVariables 请求参数
     * @param headerVariables 请求头
     * @param charsetName InputStreamReader转换流字符集名字
     * @return
     */
    public static RestfulResponse httpGet(String path, Map<String, ?> uriVariables, Map<String, String> headerVariables, String charsetName) {
        // 传入参数的校验
        RestfulUtil.check(path, GET_METHOD);

        HttpURLConnection conn = null;
        InputStream in = null;
        BufferedReader br = null;
        int code = -1;
        StringBuffer response = new StringBuffer();
        try {
            URL url = new URL(
                    RestfulUtil.appendUriVariables(new StringBuffer(path), uriVariables).toString()
            );
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(CONNECT_TIME_OUT);
            conn.setReadTimeout(READ_TIMEOUT);
            // 请求方式 GET/POST/PUT/DELETE
            conn.setRequestMethod(GET_METHOD);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Connection", "keep-alive");
            RestfulUtil.appendHeaderVariables(conn, headerVariables);
            // 发起请求并返回请求状态
            code = conn.getResponseCode();
            in = conn.getInputStream();
            if(null != in){
                br = new BufferedReader(new InputStreamReader(in, charsetName));
                String line = null;
                while (null != (line = br.readLine())){
                    response.append(line);
                }
            }
        }catch (IOException e){
            System.out.println(e);
        }finally {
            // 关闭连接
            if(null != br){
                try {
                    br.close();
                }catch (IOException e){
                    System.out.println(e);
                }
            }
            if(null != in){
                try {
                    in.close();
                }catch (IOException e){
                    System.out.println(e);
                }
            }
        }
        return new RestfulResponse(code, response.toString());
    }

    /**
     * POST请求
     * @param path 请求路径
     * @param uriVariables 请求参数
     * @param headerVariables 请求头
     * @param body 请求体 {"name":"张三"} 这种格式的字符串
     * @param charsetName InputStreamReader转换流字符集名字
     * @return
     */
    public static RestfulResponse httpPost(String path, Map<String, ?> uriVariables, Map<String, String> headerVariables, String body, String charsetName) {
        // 传入参数的校验
        RestfulUtil.check(path, POST_METHOD);

        HttpURLConnection conn = null;
        InputStream in = null;
        BufferedReader br = null;
        OutputStream out = null;
        int code = -1;
        StringBuffer response = new StringBuffer();
        try {
            URL url = new URL(
                    RestfulUtil.appendUriVariables(new StringBuffer(path), uriVariables).toString()
            );
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(CONNECT_TIME_OUT);
            conn.setReadTimeout(READ_TIMEOUT);
            // 请求方式 GET/POST/PUT/DELETE
            conn.setRequestMethod(POST_METHOD);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Connection", "keep-alive");
            RestfulUtil.appendHeaderVariables(conn, headerVariables);
            if(null != body){
                // 允许向服务器提交数据
                conn.setDoOutput(true);
                // 允许向服务器读取数据
                conn.setDoInput(true);
                // POST请求需关闭缓存
                conn.setUseCaches(false);
                // 提交表单
                if(null != body){
                    out = conn.getOutputStream();
                    // 写入内存
                    out.write(body.getBytes());
                    out.flush();
                }
            }
            // 发起请求并返回请求状态
            code = conn.getResponseCode();
            in = conn.getInputStream();
            if(null != in){
                br = new BufferedReader(new InputStreamReader(in, charsetName));
                String line = null;
                while (null != (line = br.readLine())){
                    response.append(line);
                }
            }
        }catch (IOException e){
            System.out.println(e);
        }finally {
            // 关闭连接
            if(null != br){
                try {
                    br.close();
                }catch (IOException e){
                    System.out.println(e);
                }
            }
            if(null != in){
                try {
                    in.close();
                }catch (IOException e){
                    System.out.println(e);
                }
            }
            if(null != out){
                try {
                    out.close();
                }catch (IOException e){
                    System.out.println(e);
                }
            }
        }
        return new RestfulResponse(code, response.toString());
    }

    /**
     * PUT请求
     * @param path 请求路径
     * @param uriVariables 请求参数
     * @param headerVariables 请求头
     * @param body 请求体 {"name":"张三"} 这种格式的字符串
     * @param charsetName InputStreamReader转换流字符集名字
     * @return
     */
    public static RestfulResponse httpPut(String path, Map<String, ?> uriVariables, Map<String, String> headerVariables, String body, String charsetName) {
        // 传入参数的校验
        RestfulUtil.check(path, PUT_METHOD);

        HttpURLConnection conn = null;
        InputStream in = null;
        BufferedReader br = null;
        OutputStream out = null;
        int code = -1;
        StringBuffer response = new StringBuffer();
        try {
            URL url = new URL(
                    RestfulUtil.appendUriVariables(new StringBuffer(path), uriVariables).toString()
            );
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(CONNECT_TIME_OUT);
            conn.setReadTimeout(READ_TIMEOUT);
            // 请求方式 GET/POST/PUT/DELETE
            conn.setRequestMethod(PUT_METHOD);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Connection", "keep-alive");
            RestfulUtil.appendHeaderVariables(conn, headerVariables);
            if(null != body){
                // 允许向服务器提交数据
                conn.setDoOutput(true);
                // 允许向服务器读取数据
                conn.setDoInput(true);
                // POST请求需关闭缓存
                conn.setUseCaches(false);
                // 提交表单
                if(null != body){
                    out = conn.getOutputStream();
                    // 写入内存
                    out.write(body.getBytes());
                    out.flush();
                }
            }
            // 发起请求并返回请求状态
            code = conn.getResponseCode();
            in = conn.getInputStream();
            if(null != in){
                br = new BufferedReader(new InputStreamReader(in, charsetName));
                String line = null;
                while (null != (line = br.readLine())){
                    response.append(line);
                }
            }
        }catch (IOException e){
            System.out.println(e);
        }finally {
            // 关闭连接
            // 关闭连接
            if(null != br){
                try {
                    br.close();
                }catch (IOException e){
                    System.out.println(e);
                }
            }
            if(null != in){
                try {
                    in.close();
                }catch (IOException e){
                    System.out.println(e);
                }
            }
            if(null != out){
                try {
                    out.close();
                }catch (IOException e){
                    System.out.println(e);
                }
            }
        }
        return new RestfulResponse(code, response.toString());
    }

    /**
     * 传入参数校验
     * @param method GET/POST/PUT/DELETE
     */
    private static void check(String path, String method){
        try {
            if(path == null || path.length() == 0){
                throw new Exception("请求路径不允许为空");
            }
            if(!(GET_METHOD.equals(method) || POST_METHOD.equals(method) || PUT_METHOD.equals(method) || DELETE_METHOD.equals(method))){
                throw new Exception("不允许的请求方式");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * URL 拼接，一般 GET 请求会用到
     * @param pathBuffer 请求地址
     * @param uriVariables 需要拼接到URL上的请求参数
     * @return
     */
    private static StringBuffer appendUriVariables(StringBuffer pathBuffer, Map<String, ?> uriVariables){
        if(null == uriVariables){
            return pathBuffer;
        }else{
            pathBuffer.append("?");
        }
        for(Map.Entry<String, ?> entry : uriVariables.entrySet()){
            if(null != entry.getValue()){
                pathBuffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        pathBuffer.deleteCharAt(pathBuffer.length() - 1);
        System.out.println(new StringBuilder("request uri:").append(pathBuffer));
        return pathBuffer;
    }

    /**
     * 拼接请求头 Headers
     * @param conn
     * @param headerVariables
     * @return
     */
    private static void appendHeaderVariables(HttpURLConnection conn, Map<String, String> headerVariables){
        if(null == headerVariables){
            return ;
        }
        for(Map.Entry<String, String> entry : headerVariables.entrySet()){
            if(null != entry.getValue()){
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        return ;
    }
}
