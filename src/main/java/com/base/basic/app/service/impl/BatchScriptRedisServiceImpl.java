package com.base.basic.app.service.impl;

import com.base.basic.app.service.BatchScriptRedisService;
import com.base.basic.domain.vo.v0.ScriptBodyVO;
import com.base.basic.domain.vo.v0.ScriptParamVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BatchScriptRedisServiceImpl implements BatchScriptRedisService {
    Logger logger = LoggerFactory.getLogger(BatchScriptRedisServiceImpl.class);

    String format = "\\$\\{([^}]+)\\}";
    Pattern pattern = Pattern.compile(format);

    @Override
    public void executeRedis(ScriptBodyVO scriptBody){
        List<String> scriptList = this.analyzeScript(scriptBody.getScript());

        Jedis jedis = new Jedis(scriptBody.getAddress(), scriptBody.getPort());
        try {
            if(StringUtils.isNotEmpty(scriptBody.getPassword())){
                jedis.auth(scriptBody.getPassword());
            }
            for(String script:scriptList){
//                Object result = jedis.evalsha(script.getBytes(StandardCharsets.UTF_8));
//                logger.info("{}", result);
            }
        }catch (Exception e){
            logger.error("redis执行异常 {}", e);
        }finally {
            jedis.close();
        }
    }

    /**
     * 生成要执行的脚本
     * @param scriptParam
     * @return
     */
    private List<String> analyzeScript(ScriptParamVO scriptParam){
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
        return scriptList;
    }

    /**
     * 执行脚本
     * @param scriptList
     */
    private void run(List<String> scriptList){

    }
}
