package com.base.basic.api.controller.v0;

import com.base.basic.app.service.CodeGeneratorService;
import com.base.basic.domain.vo.v0.TableVO;
import com.base.common.util.layui.LayJson;
import com.base.common.util.page.PageParmaters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="代码生成")
@RestController
@RequestMapping("/code-generator")
public class CodeGeneratorController {

    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @ApiOperation(value = "生成MVC代码模型")
    @GetMapping("/list")
    public LayJson<TableVO> list(PageParmaters pageParmaters, TableVO tableVO) {
        return new LayJson<>(codeGeneratorService.pageList(pageParmaters, tableVO));
    }

    @ApiOperation(value = "生成MVC代码模型")
    @PostMapping("/mvc")
    public ResponseEntity mvc(@RequestBody List<TableVO> tableVOS) {
        codeGeneratorService.mvcGenerator(tableVOS);
        return new ResponseEntity(HttpStatus.OK);
    }
}
