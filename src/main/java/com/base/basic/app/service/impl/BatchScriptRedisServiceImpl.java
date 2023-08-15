package com.base.basic.app.service.impl;

import com.base.basic.app.service.BatchScriptRedisService;
import com.base.basic.domain.vo.v0.ScriptParamVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BatchScriptRedisServiceImpl implements BatchScriptRedisService {

    String format = "\\$\\{([^}]+)\\}";
    Pattern pattern = Pattern.compile(format);

    @Override
    public void execute(ScriptParamVO scriptParam){
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
    }
}
