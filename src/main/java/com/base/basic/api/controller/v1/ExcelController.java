package com.base.basic.api.controller.v1;

import com.base.basic.app.service.ExcelService;
import com.base.common.annotation.Access;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(tags="Excel操作")
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @ApiOperation(value = "将两种语言的Word进行合并")
    @PostMapping("/merge/two-lange")
    @Access(accessNoToken = true)
    public ResponseEntity mergeTwoLanguage(
            HttpServletResponse response,
            @ApiParam(value="文件一", required = true) @RequestPart("langeOneFile") MultipartFile langeOneFile,
            @ApiParam(value="文件二", required = true) @RequestPart("langeTwoFile") MultipartFile langeTwoFile) throws IOException {
        excelService.mergeTwoLanguage(response, langeOneFile, langeTwoFile);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "数据转化")
    @PostMapping("/merge/conver")
    @Access(accessNoToken = true)
    public ResponseEntity dataConver(
            HttpServletResponse response,
            @ApiParam(value="文件", required = true) @RequestPart("file") MultipartFile file,
            @ApiParam(value="要处理的Sheet页") @RequestParam(value = "path", required = false) Integer sheetIndex,
            @ApiParam(value="正则表达式", required = true) @RequestParam(value = "regex") String regex,
            @ApiParam(value="起始坐标轴X") @RequestParam(value = "startX", required = false) Integer startX,
            @ApiParam(value="起始坐标轴Y") @RequestParam(value = "startY", required = false) Integer startY,
            @ApiParam(value="终止坐标轴X") @RequestParam(value = "endX", required = false) Integer endX,
            @ApiParam(value="终止坐标轴Y") @RequestParam(value = "endY", required = false) Integer endY) throws IOException {
        excelService.dataConver(response, file, sheetIndex, regex, startX-1, startY-1, endX-1, endY-1);
        return new ResponseEntity(HttpStatus.OK);
    }
}