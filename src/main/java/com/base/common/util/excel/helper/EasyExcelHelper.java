package com.base.common.util.excel.helper;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.base.common.util.excel.EasyExcelBatchListener;
import com.base.common.util.excel.EasyExcelListener;
import com.base.common.util.excel.EasyOperaInterface;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class EasyExcelHelper<T> {

    private static Logger logger = LoggerFactory.getLogger(EasyExcelHelper.class);

    public static EasyExcelHelper getInstance(){
        return new EasyExcelHelper();
    }

    /**
     * 导出固定列数数据
     * @param response
     * @param fileName
     * @param sheetName
     * @param excelModel 要导出的 Excel 模板
     * @param list
     */
    public void easyExport(HttpServletResponse response, String fileName, String sheetName, Class excelModel, List<T> list){
        try {
            // 设置响应体内容
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");

            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), excelModel).sheet(sheetName).doWrite(list);
        } catch (Exception e) {
            logger.info("导出成功");
            e.printStackTrace();
        }
    }

    /**
     * 导出：列动态
     * @param response
     * @param fileName
     * @param sheetName
     * @param columnNameHeads 要导出的 Excel 列名合集
     * @param list
     */
    public void easyDynamicExport(HttpServletResponse response, String fileName, String sheetName, List<List<String>> columnNameHeads, List<List<T>> list){
        try {
            // 设置响应体内容
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");

            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream()).head(columnNameHeads).sheet(sheetName).doWrite(list);
        } catch (Exception e) {
            logger.info("导出成功");
            e.printStackTrace();
        }
    }

    /**
     * 导入固定列数
     * 数据每次执行100条（详见 EasyExcelListener 和 EasyExcelBatchListener）
     * @param sheetNo 导入的Sheet页 1、2、3...
     * @param file
     * @param easyOperInterface 导入逻辑具体实现
     */
    public void easyImport(Integer sheetNo, Class excelModel, EasyOperaInterface easyOperInterface, MultipartFile file){
        try {
            // 获取后缀名
            String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            if(!suffixName.equals(".xls") && !suffixName.equals(".xlsx")){
                logger.error("文件格式异常");
            }

            // 读取 Excel 第一个 sheet 页
            EasyExcel.read(file.getInputStream(), excelModel, new EasyExcelListener<>(easyOperInterface))
                    .excelType(suffixName.equals(".xls") ? ExcelTypeEnum.XLS : ExcelTypeEnum.XLSX)
                    .sheet(sheetNo).doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入固定列数
     * 一次性执行所有数据导入（详见 EasyExcelListener 和 EasyExcelBatchListener）
     * @param sheetNo 导入的Sheet页 1、2、3...
     * @param file
     * @param easyOperInterface 导入逻辑具体实现
     */
    public void easyBatchImport(Integer sheetNo, Class excelModel, EasyOperaInterface easyOperInterface, MultipartFile file){
        try {
            // 获取后缀名
            String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            if(!suffixName.equals(".xls") && !suffixName.equals(".xlsx")){
                logger.error("文件格式异常");
            }

            // 读取 Excel 第一个 sheet 页
            EasyExcel.read(file.getInputStream(), excelModel, new EasyExcelBatchListener<>(easyOperInterface))
                    .excelType(suffixName.equals(".xls") ? ExcelTypeEnum.XLS : ExcelTypeEnum.XLSX)
                    .sheet(sheetNo).doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入不固定的列数
     * 数据每次执行100条（详见 EasyExcelListener 和 EasyExcelBatchListener）
     * @param sheetNo
     * @param easyOperInterface
     * @param file
     */
    public void easyDynamicImport(Integer sheetNo, EasyOperaInterface easyOperInterface, MultipartFile file){
        try {
            // 获取后缀名
            String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            if(!suffixName.equals(".xls") && !suffixName.equals(".xlsx")){
                logger.error("文件格式异常");
            }

            // 读取 Excel 第一个 sheet 页
            EasyExcel.read(file.getInputStream(), new EasyExcelListener<>(easyOperInterface)).headRowNumber(0)
                    .excelType(suffixName.equals(".xls") ? ExcelTypeEnum.XLS : ExcelTypeEnum.XLSX)
                    .sheet(sheetNo).doReadSync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入不固定的列数
     * 一次性执行所有数据导入（详见 EasyExcelListener 和 EasyExcelBatchListener）
     * @param sheetNo
     * @param easyOperInterface
     * @param file
     */
    public void easyDynamicBatchImport(Integer sheetNo, EasyOperaInterface easyOperInterface, MultipartFile file){
        try {
            // 获取后缀名
            String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            if(!suffixName.equals(".xls") && !suffixName.equals(".xlsx")){
                logger.error("文件格式异常");
            }

            // 读取 Excel 第一个 sheet 页
            EasyExcel.read(file.getInputStream(), new EasyExcelBatchListener<>(easyOperInterface)).headRowNumber(0)
                    .excelType(suffixName.equals(".xls") ? ExcelTypeEnum.XLS : ExcelTypeEnum.XLSX)
                    .sheet(sheetNo).doReadSync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
