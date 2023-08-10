package com.base.basic.app.service;

import javax.servlet.http.HttpServletResponse;

public interface FileService {

    /**
     * 基于当前硬盘路径下载文件
     * @param response
     * @param path
     */
    void downloadDiskPath(HttpServletResponse response, String path);

    /**
     * 基于网络地址下载文件
     * @param response
     * @param netAddress
     * @param filename
     */
    void downloadNetwork(HttpServletResponse response, String netAddress, String filename);
}
