package com.base.basic.api.controller.v1;

import com.base.basic.app.service.BatchScriptRedisService;
import com.base.basic.domain.vo.v0.ScriptBodyVO;
import com.base.basic.domain.vo.v0.ScriptParamVO;
import com.base.basic.domain.vo.v0.TestConnectionVO;
import com.base.common.annotation.Access;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

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

    @ApiOperation(value = "测试redis连通性")
    @PostMapping("/redis/connect")
    public ResponseEntity<TestConnectionVO> redis(@RequestBody TestConnectionVO testConnection) {
        Jedis jedis = new Jedis(testConnection.getAddress(), testConnection.getPort());
        try {
            if(StringUtils.isNotEmpty(testConnection.getPassword())){
                jedis.auth(testConnection.getPassword());
            }
            String ping = jedis.ping();
            testConnection.setConnected(StringUtils.equals(ping, "PONG") ? Boolean.TRUE : Boolean.FALSE);
        }catch (Exception e){
            testConnection.setConnected(Boolean.FALSE);
        }finally {
            jedis.close();
        }
        return new ResponseEntity(testConnection, HttpStatus.OK);
    }

    @ApiOperation(value = "执行Redis脚本")
    @PostMapping("/execute/redis")
    @Access(accessNoToken = true)
    public ResponseEntity executeRedis(@RequestBody ScriptBodyVO scriptBody) {
        batchScriptRedisService.executeRedis(scriptBody);
        return new ResponseEntity(HttpStatus.OK);
    }
}
