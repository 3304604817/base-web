package com.base.basic.api.controller.v1;

import com.base.basic.app.service.WordService;
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

@Api(tags="Word操作")
@RestController
@RequestMapping("/word")
public class WordController {

    @Autowired
    private WordService wordService;

    @ApiOperation(value = "将两种语言的Word进行合并")
    @PostMapping("/merge/two-lange")
    @Access(accessNoToken = true)
    public ResponseEntity mergeTwoLanguage(
            HttpServletResponse response,
            @ApiParam(value="文件一", required = true) @RequestPart("langeOneFile") MultipartFile langeOneFile,
            @ApiParam(value="文件二", required = true) @RequestPart("langeTwoFile") MultipartFile langeTwoFile) throws IOException {
        wordService.mergeTwoLanguage(response, langeOneFile, langeTwoFile);
        return new ResponseEntity(HttpStatus.OK);
    }
}
