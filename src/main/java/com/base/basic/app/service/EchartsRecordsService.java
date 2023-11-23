package com.base.basic.app.service;

import com.base.basic.domain.vo.v0.EchartsRecordsVO;
import com.base.common.util.excel.EasyOperaInterface;
import org.springframework.web.multipart.MultipartFile;

public interface EchartsRecordsService extends EasyOperaInterface {

    String importData(MultipartFile file);
}
