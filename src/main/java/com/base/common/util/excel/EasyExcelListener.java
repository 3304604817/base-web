package com.base.common.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public class EasyExcelListener<T> extends AnalysisEventListener<T> {

    private EasyOperaInterface easyOperInterface;

    // 每次读取的最大数据条数
    private static final int MAX_READ_COUNT = 100;

    // 存要导入的数据
    List<Object> list = new ArrayList<>(128);

    public EasyExcelListener(EasyOperaInterface easyOperInterface) {
        this.easyOperInterface = easyOperInterface;
    }

    @Override
    public void invoke(T data, AnalysisContext analysisContext) {
        list.add(data);
        // 每次只保存 100 条数据，防止数据过多进入 List 导致 OOM
        if (list.size() > MAX_READ_COUNT) {
            // 保存数据
            easyOperInterface.easySave(list);
            // 清空list
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 保存剩余数据数据
        easyOperInterface.easySave(list);
    }
}
