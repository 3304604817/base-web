package com.base.basic.api.controller.v0;

import com.base.basic.app.service.RocketMQProducerService;
import com.base.common.util.jwt.annotation.Access;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang.gao
 * @description
 * @date 11/21/22 10:26 上午
 */
@Api(tags="rocketMQTemplate")
@RestController
@RequestMapping("/rocketMQTemplate")
public class RocketMQTemplateController {
    @Autowired
    private RocketMQProducerService rocketMQTemplateService;

    @ApiOperation(value = "发送MQ同步消息")
    @PostMapping("/syncSend")
    @Access(accessNoToken = true)
    public ResponseEntity syncSend() {
        rocketMQTemplateService.syncSend();
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "发送MQ顺序消息")
    @PostMapping("/syncSendOrderly")
    @Access(accessNoToken = true)
    public ResponseEntity syncSendOrderly() {
        rocketMQTemplateService.syncSendOrderly();
        return new ResponseEntity(HttpStatus.OK);
    }
}
