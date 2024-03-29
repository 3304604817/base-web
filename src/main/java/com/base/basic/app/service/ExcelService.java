package com.base.basic.app.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExcelService {

    void mergeTwoLanguage(HttpServletResponse response, MultipartFile langeOneFile, MultipartFile langeTwoFile) throws IOException;
}
