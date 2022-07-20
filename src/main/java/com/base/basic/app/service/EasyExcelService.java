package com.base.basic.app.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface EasyExcelService {

    /**
     * 导出
     * @param response
     * @return
     */
    String exportData(HttpServletResponse response);

    /**
     * 导入
     * @param file
     * @return
     */
    String importData(MultipartFile file);
}
