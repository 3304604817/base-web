package com.base.basic.app.service.impl;

import com.base.basic.app.service.ExcelService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;


@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public void mergeTwoLanguage(HttpServletResponse response, MultipartFile langeOneFile, MultipartFile langeTwoFile) throws IOException {
        String oneFileName = langeOneFile.getOriginalFilename();
        String twoFileName = langeTwoFile.getOriginalFilename();
        String oneSuffix = oneFileName.substring(oneFileName.lastIndexOf('.')).toLowerCase();
        String twoSuffix = twoFileName.substring(twoFileName.lastIndexOf('.')).toLowerCase();
        Workbook oneWorkbook = ".xls".equals(oneSuffix) ? new HSSFWorkbook(langeOneFile.getInputStream()) : new HSSFWorkbook(langeOneFile.getInputStream());
        Workbook twoWorkbook = ".xls".equals(twoSuffix) ? new HSSFWorkbook(langeTwoFile.getInputStream()) : new HSSFWorkbook(langeTwoFile.getInputStream());

        // 获取 Sheet 页数量
        int numberOfSheets = oneWorkbook.getNumberOfSheets();
        for(int sheetIndex = 0; sheetIndex < numberOfSheets; sheetIndex++){
            Sheet oneSheet = oneWorkbook.getSheetAt(sheetIndex);
            Sheet twoSheet = twoWorkbook.getSheetAt(sheetIndex);

            // 遍历 sheet 页行
            for(int i = 0; i < oneSheet.getPhysicalNumberOfRows(); i++){
                Row row1 = oneSheet.getRow(i);
                Row row2 = twoSheet.getRow(i);
                // 遍历行的每个单元格
                for(int j = 0; j < row1.getPhysicalNumberOfCells(); j++){
                    Cell cell1 = row1.getCell(j);
                    Cell cell2 = row2.getCell(j);

                    if(null != cell1){
                        if(cell1.getCellType() == CellType.NUMERIC){
                            // 数字不合并
//                            cell1.setCellValue(cell1.getNumericCellValue() + "\n" + cell2.getNumericCellValue());
                        }else if(cell1.getCellType() == CellType.STRING && StringUtils.isNotEmpty(cell1.getStringCellValue())){
                            cell1.setCellValue(cell1.getStringCellValue() + "\n" + cell2.getStringCellValue());
                        }
                    }
                }
            }
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=Excel.xlsx");
        try (OutputStream outputStream = response.getOutputStream()) {
            oneWorkbook.write(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            oneWorkbook.close();
            twoWorkbook.close();
        }
    }

    @Override
    public void dataConver(HttpServletResponse response, MultipartFile file, Integer sheetIndex) throws IOException {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf('.')).toLowerCase();
        Workbook workbook = ".xls".equals(suffix) ? new HSSFWorkbook(file.getInputStream()) : new HSSFWorkbook(file.getInputStream());

        // 获取 Sheet 页数量
        int numberOfSheets = workbook.getNumberOfSheets();

        for(int index = 0; index < numberOfSheets; index++){
            if(Objects.nonNull(sheetIndex) && index != sheetIndex.intValue())continue;

            Sheet sheet = workbook.getSheetAt(sheetIndex);

            // 遍历 sheet 页行
            for(int i = 0; i < sheet.getPhysicalNumberOfRows(); i++){
                Row row = sheet.getRow(i);
                // 遍历行的每个单元格\
                if(null == row) continue;
                for(int j = 0; j < row.getPhysicalNumberOfCells(); j++){
                    Cell cell = row.getCell(j);

                    if(null != cell){
                        if(cell.getCellType() == CellType.NUMERIC){
                            // 数字不合并
//                            cell1.setCellValue(cell1.getNumericCellValue() + "\n" + cell2.getNumericCellValue());
                        }else if(cell.getCellType() == CellType.STRING && StringUtils.isNotEmpty(cell.getStringCellValue())){
//                            cell.setCellValue(cell.getStringCellValue() + "\n" + cell.getStringCellValue());
                        }
                    }
                }
            }
        }
    }
}
