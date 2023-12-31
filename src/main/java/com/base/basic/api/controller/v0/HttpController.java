package com.base.basic.api.controller.v0;

import com.alibaba.fastjson.JSON;
import com.base.basic.app.service.InterfaceService;
import com.base.basic.domain.entity.v0.IamUser;
import com.base.common.util.convert.ObjectConvertUtil;
import com.base.common.util.http.RestfulResponse;
import com.base.common.util.http.RestfulUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Api(tags="Http请求测试")
@RestController
@RequestMapping("/request")
public class HttpController {
    Logger logger = LoggerFactory.getLogger(HttpController.class);

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private InterfaceService interfaceService;

    /**
     * 使用 RestTemplate 发送 http 请求示例
     * 使用前先配置 RestTemplateConfig 文件
     */
    @ApiOperation(value = "")
    @PostMapping("/rest-template/get")
    public ResponseEntity restTemplate() {
        /**
         * GET 请求
         * responseType：接受返回 Body 的类，可以是具体业务实体类。这里 String.class 更加通用，方便后续可以自己转成任意对象
         * uriVariables：参数会按照顺序拼接到 {} 标签中
         */
        ResponseEntity<String> getResult = restTemplate.getForEntity("http://localhost/old-goods?name={name}", String.class, "iPhoneXS");
        logger.info("getResult {}", JSON.toJSONString(getResult));

        /**
         * POST 请求
         * request：传 RequestBody 体
         * uriVariables：参数会按照顺序拼接到 {} 标签中
         */
        IamUser user = new IamUser(UUID.randomUUID().toString());
        ResponseEntity<String> postResult = restTemplate.postForEntity("http://localhost/user/insert", user, String.class);
        logger.info("postResult {}", JSON.toJSONString(postResult));

        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 使用 JDK 原生 http 请求示例
     */
    @ApiOperation(value = "")
    @PostMapping("/jdk/http")
    public ResponseEntity httpUrlConnection() {

        Map<String, Object> uriVariables = new HashMap<>(2);
        uriVariables.put("name", "iPhoneXS");
        RestfulResponse getResult = interfaceService.sendRestful("GOODS_DETAIL", RestfulUtil.GET_METHOD, uriVariables, null, null);

        IamUser user = new IamUser(UUID.randomUUID().toString());
        String str = ObjectConvertUtil.convertString(user);
        RestfulResponse postResult = RestfulUtil.httpPost("http://localhost:8086/user/insert", null, null, str, RestfulUtil.UTF8_CHARSET);
        return new ResponseEntity(HttpStatus.OK);
    }
}
