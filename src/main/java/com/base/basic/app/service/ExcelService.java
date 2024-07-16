package com.base.basic.app.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExcelService {

    /**
     * 将两种语言的Word进行合并
     * @param response
     * @param langeOneFile
     * @param langeTwoFile
     * @throws IOException
     */
    void mergeTwoLanguage(HttpServletResponse response, MultipartFile langeOneFile, MultipartFile langeTwoFile) throws IOException;

    /**
     * 数据转化
     * @param response
     * @param file
     * @throws IOException
     */
    void dataConver(HttpServletResponse response, MultipartFile file, Integer sheetIndex) throws IOException;
}
