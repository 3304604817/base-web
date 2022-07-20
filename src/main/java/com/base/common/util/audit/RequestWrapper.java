package com.base.common.util.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

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
            ServletInputStream is = request.getInputStream();
            byte[] bodyBytes = StreamUtils.copyToByteArray(is);
            this.body = new String(bodyBytes, request.getCharacterEncoding());
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
