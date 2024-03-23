package com.base.basic.app.service.impl;

import com.base.basic.app.service.WordService;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordServiceImpl implements WordService {
    Logger logger = LoggerFactory.getLogger(WordServiceImpl.class);

    @Override
    public void mergeTwoLanguage(HttpServletResponse response, MultipartFile langeOneFile, MultipartFile langeTwoFile) throws IOException {
        ZipSecureFile.setMinInflateRatio(-1.0D);
        XWPFDocument oneDocument = new XWPFDocument(langeOneFile.getInputStream());
        XWPFDocument twoDocument = new XWPFDocument(langeTwoFile.getInputStream());

        /**
         * 遍历文档中的所有段落进行操作
         */
        List<XWPFParagraph> oneParagraphs = oneDocument.getParagraphs();
        List<XWPFParagraph> twoParagraphs = twoDocument.getParagraphs();
        // 提取两个文本的非空段落
        List<XWPFParagraph> notNullOneParagraphs = oneParagraphs.stream().filter(para -> para.getText() != null && !para.getText().isEmpty()).collect(Collectors.toList());
        List<XWPFParagraph> notNullTwoParagraphs = twoParagraphs.stream().filter(para -> para.getText() != null && !para.getText().isEmpty()).collect(Collectors.toList());
        // 循环第一个文档，把第二个文档合并到第一个文档中
        for(int i = 0; i < notNullOneParagraphs.size(); i++){
            // 添加换行
            notNullOneParagraphs.get(i).createRun().addBreak();
            // 把文档2的段落追加到文档1中
            notNullOneParagraphs.get(i).createRun().setText(notNullTwoParagraphs.get(i).getText());
        }

        /**
         * 遍历文档中的所有表格进行操作
         */
        // 一个 XWPFTable 就是 Word 中的一个表格
        List<XWPFTable> oneTables = oneDocument.getTables();
        List<XWPFTable> twoTables = twoDocument.getTables();
        for(int i = 0; i < oneTables.size(); i++){
            // 获取第 i 个表格
            XWPFTable oneTable = oneTables.get(i);
            XWPFTable twoTable = twoTables.get(i);
            for(int j = 0; j < oneTable.getNumberOfRows(); j++){
                XWPFTableRow oneRow = oneTable.getRow(j);
                XWPFTableRow twoRow = twoTable.getRow(j);
                List<XWPFTableCell> oneCells = oneRow.getTableCells();
                List<XWPFTableCell> twoCells = twoRow.getTableCells();
                for(int k = 0; k < oneCells.size(); k++){
                    // 获取单元格里面的段落
                    XWPFParagraph paragraph = oneCells.get(k).getParagraphs().get(0);
                    paragraph.createRun().addBreak();
                    paragraph.createRun().setText(twoCells.get(k).getText());
                }
            }
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=Word.docx");
        try (OutputStream outputStream = response.getOutputStream()) {
            oneDocument.write(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
