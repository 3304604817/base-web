package com.base.basic.api.controller.v0;

import com.base.basic.app.service.EchartsRecordsService;
import com.base.basic.domain.vo.v0.EchartsXAxisVO;
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
    public ResponseEntity<String> importData(@ApiParam(value="选择文件", required = true) @RequestPart("fileName") MultipartFile file){
        return new ResponseEntity(echartsRecordsService.importData(file), HttpStatus.OK);
    }

    @ApiOperation(value = "生成预览数据")
    @PostMapping("/export")
    public ResponseEntity<List<EchartsXAxisVO>> reviewChart(){
        echartsRecordsService.reviewChart();
        return new ResponseEntity(new ArrayList<>(), HttpStatus.OK);
    }
}
