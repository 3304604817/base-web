package com.base.basic.api.controller.v0;

import com.base.basic.app.service.EchartsRecordsService;
import com.base.basic.domain.vo.v0.EchartsRecordsVO;
import com.base.common.annotation.Access;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Api(tags="折线图")
@RestController
@RequestMapping("/echarts-records")
public class EchartsRecordsController {

    @Autowired
    private EchartsRecordsService echartsRecordsService;

    @ApiOperation(value = "导入")
    @PostMapping("/import")
    @Access(accessNoToken = true)
    public ResponseEntity<String> importData(@ApiParam(value="文件名", required = false) @RequestPart("name") String name,
                                             @ApiParam(value="选择文件", required = true) @RequestPart("file") MultipartFile file){
        return new ResponseEntity(echartsRecordsService.importData(file), HttpStatus.OK);
    }
}
