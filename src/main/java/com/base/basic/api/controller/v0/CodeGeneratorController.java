package com.base.basic.api.controller.v0;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="代码生成")
@RestController
@RequestMapping("/code-generator")
public class CodeGeneratorController {

    @ApiOperation(value = "生成MVC代码模型")
    @PostMapping("/mvc")
    public ResponseEntity mvc(@RequestParam(value = "author", required = true) String author,
                                  @RequestParam(value = "pkg", required = true) String pkg,
                                  @RequestParam(value = "db", required = true) String bd,
                                  @RequestParam(value = "tableName", required = true) String tableName) {

        return new ResponseEntity(HttpStatus.OK);
    }
}
