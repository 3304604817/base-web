package com.base.common.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public class EasyExcelBatchListener<T> extends AnalysisEventListener<T> {
    private EasyOperaInterface easyOperInterface;

    // 存要导入的数据
    List<Object> list = new ArrayList<>(128);

    public EasyExcelBatchListener(EasyOperaInterface easyOperInterface) {
        this.easyOperInterface = easyOperInterface;
    }

    @Override
    public void invoke(T data, AnalysisContext analysisContext) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 保存数据逻辑
        easyOperInterface.easySave(list);
    }
}
