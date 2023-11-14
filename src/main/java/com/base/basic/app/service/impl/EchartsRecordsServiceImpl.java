package com.base.basic.app.service.impl;

import com.base.basic.app.service.EchartsRecordsService;
import com.base.basic.domain.exc.UserExcelModel;
import com.base.common.util.excel.helper.EasyExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class EchartsRecordsServiceImpl implements EchartsRecordsService {

    @Autowired
    private EchartsRecordsService echartsRecordsService;

    @Override
    public void reviewChart(){

    }

    @Override
    public String importData(MultipartFile file){
        EasyExcelHelper.getInstance().easyDynamicImport(0, echartsRecordsService, file);
        return "导入成功";
    }

    /**
     * 重写导入方法
     * @param list
     */
    @Override
    public void easySave(List<Object> list) {
        System.out.println(list);
    }
}
