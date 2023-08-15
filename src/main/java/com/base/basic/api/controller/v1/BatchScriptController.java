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
//        scriptParam.setKey("${" + scriptParam.getKey() + "}");

        Matcher matcher = pattern.matcher(scriptParam.getScriptText());

        // 最终要执行的命令集合
        List<String> scriptList = new ArrayList<>(16);
        String script = null;
        while (matcher.find()){
            String key = matcher.group();
            if(StringUtils.equals(key, "${" + scriptParam.getKey() + "}")){
                if(scriptParam.getValueType().equals("fixed")){
                    script = scriptParam.getScriptText().replaceAll("\\$\\{" + scriptParam.getKey() + "\\}", scriptParam.getValue());
                    scriptList.add(script);
                }else if(scriptParam.getValueType().equals("range")){
                    for(int i = scriptParam.getRangeFm().intValue(); i <= scriptParam.getRangeTo().intValue(); i++){
                        script = scriptParam.getScriptText().replaceAll("\\$\\{" + scriptParam.getKey() + "\\}", String.valueOf(i));
                        scriptList.add(script);
                    }
                }else {}
            }
        }

        return new ResponseEntity(scriptList, HttpStatus.OK);
    }
}
