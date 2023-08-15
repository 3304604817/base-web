package com.base.basic.api.controller.v1;

import com.base.basic.app.service.BatchScriptRedisService;
import com.base.basic.domain.entity.v1.OldGoods;
import com.base.basic.domain.vo.v0.ScriptBodyVO;
import com.base.basic.domain.vo.v0.ScriptParamVO;
import com.base.common.annotation.Access;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    public ResponseEntity execute(@RequestBody ScriptBodyVO scriptBodyVO) {
        for(ScriptParamVO param:scriptBodyVO.getParams()){
            param.setKey("${" + param.getKey() + "}");
        }
        Matcher matcher = pattern.matcher(scriptBodyVO.getScriptText());


        Map<String, List<ScriptParamVO>> paramMap = scriptBodyVO.getParams().stream().collect(Collectors.groupingBy(ScriptParamVO::getKey));


        Set<String> keySet = new HashSet<>();

        StringBuffer sb = new StringBuffer();
        while (matcher.find()){
            String key = matcher.group();
            paramMap.get(key).get(0);
            matcher.appendReplacement(sb, paramMap.get(key).get(0).getValue());
        }


        return new ResponseEntity(HttpStatus.OK);
    }
}
