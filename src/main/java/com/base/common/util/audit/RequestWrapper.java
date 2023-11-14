package com.base.common.util.audit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author gaoyang
 * 解决流数据无法重复读取的问题
 * 重写 HttpServletRequestWrapper 把 request保存下来，然后通过过滤器把保存下来的request再填充进去，这样就可以多次读取request
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    private static final Logger logger = LoggerFactory.getLogger(RequestWrapper.class);

    private String body = "";

    public String getBody(){
        return this.body;
    }

    public RequestWrapper(HttpServletRequest request) {
        super(request);
        try {
            String contentType = request.getContentType();
            // 判断当前请求数据类型是否为表单提交
            if (!StringUtils.isEmpty(contentType) && (contentType.contains(MediaType.MULTIPART_FORM_DATA_VALUE) || contentType.contains(MediaType.APPLICATION_FORM_URLENCODED_VALUE))) {
                String bodyString = "";
                Map<String, String[]> parameterMap = request.getParameterMap();
                if (!CollectionUtils.isEmpty(parameterMap)) {
                    bodyString = parameterMap.entrySet().stream().map(x -> {
                        String[] values = x.getValue();
                        return x.getKey() + "=" + (values != null ? (values.length == 1 ? values[0] : Arrays.toString(values)) : null);
                    }).collect(Collectors.joining("&"));
                }
                this.body = new String(bodyString.getBytes(), request.getCharacterEncoding());
            }else {
                ServletInputStream is = request.getInputStream();
                byte[] bodyBytes = StreamUtils.copyToByteArray(is);
                this.body = new String(bodyBytes, request.getCharacterEncoding());
            }

        }catch (IOException ex){
            logger.error("ex {}", ex);
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        /**
         * 重写 getInputStream 方法，直接获取 RequestWrapper 中的 body
         */
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.body.getBytes());
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
        return servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }
}
