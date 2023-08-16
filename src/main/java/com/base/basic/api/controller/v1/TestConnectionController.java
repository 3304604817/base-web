package com.base.basic.api.controller.v1;

import com.base.basic.domain.entity.v1.Role;
import com.base.basic.domain.vo.v0.TestConnectionVO;
import com.base.common.annotation.Access;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@Api(tags="测试连通性")
@RestController
@RequestMapping("/test-connect")
public class TestConnectionController {

//    @ApiOperation(value = "测试redis连通性")
//    @PostMapping("/redis")
//    public ResponseEntity<String> redis(@RequestBody TestConnectionVO testConnection) {
//        Jedis jedis = new Jedis(testConnection.getAddress(), testConnection.getPort());
//        if(StringUtils.isNotEmpty(testConnection.getPassword())){
//            jedis.auth(testConnection.getPassword());
//        }
//        String ping = jedis.ping();
//        return new ResponseEntity(StringUtils.equals(ping, "PONG") ? "连接成功" : "连接失败", HttpStatus.OK);
//    }
@ApiOperation(value = "测试redis连通性")
@PostMapping("/redis")
@Access(accessNoToken = true)
public ResponseEntity<String> redis(@RequestBody TestConnectionVO testConnection) {
    return new ResponseEntity("连接失败", HttpStatus.OK);
}
}
