package com.base.basic.app.service.impl;

import com.base.basic.app.service.ExcelService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;


@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public void mergeTwoLanguage(HttpServletResponse response, MultipartFile langeOneFile, MultipartFile langeTwoFile) throws IOException {
        Workbook oneWorkbook = new XSSFWorkbook(langeOneFile.getInputStream());
        Workbook twoWorkbook = new XSSFWorkbook(langeTwoFile.getInputStream());
        // 获取 Sheet 页数量
        int numberOfSheets = oneWorkbook.getNumberOfSheets();
        for(int sheetIndex = 0; sheetIndex < numberOfSheets; sheetIndex++){
            Sheet oneSheet = oneWorkbook.getSheetAt(sheetIndex);
            Sheet twoSheet = twoWorkbook.getSheetAt(sheetIndex);

            // 遍历 sheet 页行
            for(int i = 0; i < oneSheet.getPhysicalNumberOfRows(); i++){
                Row row = oneSheet.getRow(i);
                // 遍历行的每个单元格
                for(int j = 0; j < row.getPhysicalNumberOfCells(); j++){
                    Cell cell1 = row.getCell(j);
                    Cell cell2 = twoSheet.getRow(i).getCell(j);


                    cell1.setCellValue(cell1.getStringCellValue() + "\n" + cell2.getStringCellValue());
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
}
