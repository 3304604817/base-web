package com.base.basic.app.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface EasyExcelService {

    /**
     * 导出数据表
     * @param response
     * @return
     */
    String exportTable(HttpServletResponse response, String tableSchema, String tableName);

    /**
     * 导入
     * @param file
     * @return
     */
    String importData(MultipartFile file);
}
