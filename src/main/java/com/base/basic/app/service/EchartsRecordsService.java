package com.base.basic.app.service;

import com.base.common.util.excel.EasyOperaInterface;
import org.springframework.web.multipart.MultipartFile;

public interface EchartsRecordsService extends EasyOperaInterface {

    void reviewChart();

    String importData(MultipartFile file);
}
