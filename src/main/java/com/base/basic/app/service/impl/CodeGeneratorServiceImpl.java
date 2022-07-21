package com.base.basic.app.service.impl;

import com.base.basic.app.service.CodeGeneratorService;
import com.base.basic.domain.vo.v0.ColumnVO;
import com.base.basic.domain.vo.v0.TableVO;
import com.base.basic.infra.mapper.CodeGeneratorMapper;
import com.base.common.util.convert.StringConvertUtil;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class CodeGeneratorServiceImpl implements CodeGeneratorService {

    Logger logger = LoggerFactory.getLogger(CodeGeneratorServiceImpl.class);

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
            String className = StringConvertUtil.pascalCase(tableVO.getTableName());

            // 获取字段信息
            // 主键列
            ColumnVO pkColumnVO = new ColumnVO();
            List<ColumnVO> columnVOS = codeGeneratorMapper.columnList(tableVO.getTableName());
            for(ColumnVO columnVO:columnVOS){
                columnVO.setCamelColumnName(StringConvertUtil.camelCase(columnVO.getColumnName()));
                columnVO.setPascalColumnName(StringConvertUtil.pascalCase(columnVO.getColumnName()));
                if("PRI".equals(columnVO.getColumnKey())){
                    pkColumnVO = columnVO;
                }
            }



            Map<String, Object> map = new HashMap<>();
            map.put("package", "org.base");
            map.put("columns", columnVOS);
            map.put("tableName", tableVO.getTableName());
            map.put("className", className);
            map.put("pk", pkColumnVO);
            VelocityContext context = new VelocityContext(map);

            List<String> templates = new ArrayList<String>();
            templates.add("templates/end/mvc/Entity.java.vm");
            for (String template : templates) {
                // 渲染模板
                StringWriter sw = new StringWriter();
                Template tpl = Velocity.getTemplate(template, "UTF-8");
                tpl.merge(context, sw);

                try {
                    // 添加到zip
                    zip.putNextEntry(new ZipEntry("111"));
                    IOUtils.write(sw.toString(), zip, "UTF-8");
                    IOUtils.closeQuietly(sw);
                    zip.closeEntry();
                } catch (IOException e) {
                    logger.error("{}", e);
                }
            }
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}
