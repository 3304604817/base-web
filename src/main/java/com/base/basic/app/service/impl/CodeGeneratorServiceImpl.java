package com.base.basic.app.service.impl;

import com.base.basic.app.service.CodeGeneratorService;
import com.base.basic.domain.vo.v0.ColumnVO;
import com.base.basic.domain.vo.v0.TableVO;
import com.base.basic.infra.mapper.CodeGeneratorMapper;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;

@Service
public class CodeGeneratorServiceImpl implements CodeGeneratorService {

    @Autowired
    @SuppressWarnings("all")
    private CodeGeneratorMapper codeGeneratorMapper;

    @Override
    public PageInfo<TableVO> pageList(PageParmaters pageParmaters, TableVO tableVO){
        return PageHelper.startPage(pageParmaters.getPage(), pageParmaters.getLimit()).doSelectPageInfo(() -> codeGeneratorMapper.tableList(tableVO));
    }

    @Override
    public byte[] mvcGenerator(List<TableVO> tableVOS){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for(TableVO tableVO:tableVOS){
            String className = tableToClass(tableVO.getTableName());

            // 获取字段信息
            List<ColumnVO> columnVOS = codeGeneratorMapper.columnList(tableVO.getTableName());
            for(ColumnVO columnVO:columnVOS){
                columnVO.setColumnName(columnToJava(columnVO.getColumnName()));
            }


            System.out.println(columnVOS);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * 表名转类名
     * @param tableName
     * @return
     */
    public String tableToClass(String tableName){
        // 统一先转成小写
        tableName = tableName.toLowerCase();
        // 首字母转大写
        tableName = new StringBuilder(tableName.substring(0, 1).toUpperCase())
                .append(tableName.substring(1))
                .toString();
        // _ 转驼峰
        while (tableName.contains("_")){
            int index = tableName.indexOf('_');
            tableName = new StringBuilder(tableName.substring(0, index))
                    .append(tableName.substring(index + 1, index + 2).toUpperCase())
                    .append(tableName.substring(index + 2)).toString();
        }
        return tableName;
    }

    /**
     * 表名转类名
     * @param columnName
     * @return
     */
    public String columnToJava(String columnName){
        // 统一先转成小写
        columnName = columnName.toLowerCase();
        // _ 转驼峰
        while (columnName.contains("_")){
            int index = columnName.indexOf('_');
            columnName = new StringBuilder(columnName.substring(0, index))
                    .append(columnName.substring(index + 1, index + 2).toUpperCase())
                    .append(columnName.substring(index + 2)).toString();
        }
        return columnName;
    }
}
