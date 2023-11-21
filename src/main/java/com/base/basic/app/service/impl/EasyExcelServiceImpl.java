package com.base.basic.app.service.impl;

import com.base.basic.app.service.DataBaseService;
import com.base.basic.app.service.EasyExcelService;
import com.base.basic.app.service.UserService;
import com.base.basic.domain.entity.v0.IamUser;
import com.base.basic.domain.exc.UserExcelModel;
import com.base.basic.domain.vo.v0.ColumnVO;
import com.base.basic.infra.mapper.DataBaseMapper;
import com.base.basic.infra.mapper.UserMapper;
import com.base.common.util.excel.helper.EasyExcelHelper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class EasyExcelServiceImpl implements EasyExcelService {

    @Autowired
    @SuppressWarnings("all")
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private DataBaseService dataBaseService;
    @Autowired
    @SuppressWarnings("all")
    private DataBaseMapper dataBaseMapper;

    @Override
    public String exportTable(HttpServletResponse response, String tableName){
        // 获取需要导出的数据
        List<Map<String,Object>> tableData = dataBaseService.tableData(tableName, "1 = 1");

        /**
         * 要导出的 Excle 头行
         */
        List<List<String>> columnNameHeads = new ArrayList<>(32);
        if(tableData.size() > 0){
            for(String key:tableData.get(0).keySet()){
                columnNameHeads.add(Lists.newArrayList(key));
            }
        }else {
            dataBaseMapper.columnList(tableName)
                    .stream()
                    .map(ColumnVO::getColumnName)
                    .forEach(columnName -> columnNameHeads.add(Lists.newArrayList(columnName)));
        }

        List<List<Object>> tableDataObj = new ArrayList<>(32);
        for(Map<String,Object> data:tableData){
            List<Object> dataList = new ArrayList<>(32);
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                dataList.add(entry.getValue());
            }
            tableDataObj.add(dataList);
        }
        // 导出
        EasyExcelHelper.getInstance().easyDynamicExport(response, "数据库表", "表"+tableName, columnNameHeads, tableDataObj);
        return "导出成功";
    }

    @Override
    public String importData(MultipartFile file){
        EasyExcelHelper.getInstance().easyImport(0, UserExcelModel.class, userService, file);
        return "导入成功";
    }
}
