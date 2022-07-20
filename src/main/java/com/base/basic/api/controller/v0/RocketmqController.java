package com.base.basic.api.controller.v0;

import com.base.basic.app.service.RocketmqService;
import com.base.common.util.jwt.annotation.Access;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags="rocketmq")
@RestController
@RequestMapping("/rocketmq")
public class RocketmqController {

    @Autowired
    private RocketmqService rocketmqService;

    @Access(accessNoToken = true)
    @ApiOperation(value = "启动Consumer")
    @PostMapping("/consumer-start")
    public ResponseEntity consumerStart() throws MQClientException, InterruptedException {
        rocketmqService.consumerStart();
        return new ResponseEntity(HttpStatus.OK);
    }

    @Access(accessNoToken = true)
    @ApiOperation(value = "发送MQ消息同步")
    @PostMapping("/send")
    public ResponseEntity send() throws MQClientException, InterruptedException {
        rocketmqService.send();
        return new ResponseEntity(HttpStatus.OK);
    }

    @Access(accessNoToken = true)
    @ApiOperation(value = "发送MQ消息无返回")
    @PostMapping("/send-oneway")
    public ResponseEntity sendOneway() throws MQClientException, InterruptedException {
        rocketmqService.sendOneway();
        return new ResponseEntity(HttpStatus.OK);
    }

    @Access(accessNoToken = true)
    @ApiOperation(value = "发送MQ消息异步")
    @PostMapping("/send-async")
    public ResponseEntity sendAsync() throws MQClientException, InterruptedException {
        rocketmqService.sendAsync();
        return new ResponseEntity(HttpStatus.OK);
    }
}
