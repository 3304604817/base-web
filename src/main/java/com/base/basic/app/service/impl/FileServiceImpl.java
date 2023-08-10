package com.base.basic.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.base.basic.api.controller.v0.HttpController;
import com.base.basic.app.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Service
public class FileServiceImpl implements FileService {
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public void downloadDiskPath(HttpServletResponse response, String path){
        try {
            // 读到流中
            InputStream inputStream = new FileInputStream(path); // 文件的存放路径
            response.reset();
            response.setContentType("application/octet-stream");
            String filename = new File(path).getName();
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] b = new byte[1024];
            int len;
            //从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
            while ((len = inputStream.read(b)) > 0) {
                outputStream.write(b, 0, len);
            }
            inputStream.close();
        }catch (IOException e){
            logger.error("downloadDiskPath {}", e);
        }
    }

    @Override
    public void downloadNetwork(HttpServletResponse response, String netAddress){
        try {
            URL url = new URL(netAddress);
            URLConnection conn = url.openConnection();
            InputStream inputStream = conn.getInputStream();
            String filename = new File(netAddress).getName();
            response.reset();
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
            ServletOutputStream outputStream = response.getOutputStream();

            byte[] b = new byte[1024];
            int len;
            //从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
            while ((len = inputStream.read(b)) > 0) {
                outputStream.write(b, 0, len);
            }
            inputStream.close();
        }catch (IOException e){
            logger.error("downloadNetwork {}", e);
        }
    }
}
