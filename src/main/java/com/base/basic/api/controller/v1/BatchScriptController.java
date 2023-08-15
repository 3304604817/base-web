package com.base.basic.api.controller.v1;

import com.base.basic.app.service.BatchScriptRedisService;
import com.base.basic.domain.vo.v0.ScriptParamVO;
import com.base.common.annotation.Access;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Api(tags="批量脚本执行")
@RestController
@RequestMapping("/batch-script")
public class BatchScriptController {

    @Autowired
    private BatchScriptRedisService batchScriptRedisService;

    @ApiOperation(value = "执行脚本")
    @PostMapping("/execute")
    @Access(accessNoToken = true)
    public ResponseEntity execute(@RequestBody ScriptParamVO scriptParam) {
        batchScriptRedisService.execute(scriptParam);
        return new ResponseEntity(HttpStatus.OK);
    }
}
