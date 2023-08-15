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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Api(tags="批量脚本执行")
@RestController
@RequestMapping("/batch-script")
public class BatchScriptController {

    String format = "\\$\\{([^}]+)\\}";
    Pattern pattern = Pattern.compile(format);


    @Autowired
    private BatchScriptRedisService batchScriptRedisService;

    /**
     * ${}
     */
    @ApiOperation(value = "execute")
    @PostMapping("/execute")
    @Access(accessNoToken = true)
    public ResponseEntity execute(@RequestBody ScriptParamVO scriptParam) {
        scriptParam.setKey("${" + scriptParam.getKey() + "}");

        Matcher matcher = pattern.matcher(scriptParam.getScriptText());

        StringBuffer sb = new StringBuffer();
        while (matcher.find()){
            String key = matcher.group();
            if(StringUtils.equals(key, scriptParam.getKey())){
                matcher.appendReplacement(sb, scriptParam.getValue());
            }
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
