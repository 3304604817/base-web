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

            for (int j = 0; j < notNullTwoParagraphs.get(i).getRuns().size(); j++) {
                notNullOneParagraphs.get(i).createRun().setText(notNullTwoParagraphs.get(i).getText());
            }
        }
//        for (XWPFParagraph para : oneParagraphs) {
//            String text = para.getText();
//            logger.info(text);
//            if (text != null && text.contains("oldText")) {
//                text = text.replace("oldText", "newText");
//                para.removeRun(0); // 移除原有的Run内容
//                para.createRun().setText(text); // 创建新的Run并设置修改后的文本
//            }
//        }

        /**
         * 遍历文档中的所有表格进行操作
         */
        List<XWPFTable> tables = oneDocument.getTables();
        // 一个 XWPFTable 就是 Word 中的一个表格
        for (XWPFTable table : tables) {
            int count = table.getNumberOfRows();
            for (int i = 0; i < count; i++) {
                XWPFTableRow row = table.getRow(i);
                List<XWPFTableCell> cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    logger.info(cell.getText());
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
