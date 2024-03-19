package com.base.basic.api.controller.v1;

import com.base.basic.app.service.TranslationService;
import com.base.basic.domain.entity.v1.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 基于百度翻译API
 * 参考百度开放平台：https://api.fanyi.baidu.com/doc/21
 */
@Api(tags="实现翻译功能")
@RestController
@RequestMapping("/translation")
public class TranslationController {

    @Autowired
    private TranslationService translationService;

    /**
     * 翻译一句话
     * 翻译源/目标语言: zh/en
     */
    @ApiOperation(value = "翻译一句话")
    @PostMapping("/language")
    public ResponseEntity<String> language(
            @ApiParam(value="翻译源语言") @RequestParam(required = false, defaultValue = "auto", value = "from") String from,
            @ApiParam(value="翻译目标语言", required = true) @RequestParam("to") String to,
            @ApiParam(value="要翻译的语句", required = true) @RequestParam("q") String q) throws Exception {
        return new ResponseEntity(translationService.language(from, to, q), HttpStatus.OK);
    }
}
