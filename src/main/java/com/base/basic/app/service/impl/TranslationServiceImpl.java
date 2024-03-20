package com.base.basic.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.basic.app.service.TranslationService;
import com.base.common.util.http.RestfulResponse;
import com.base.common.util.http.RestfulUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TranslationServiceImpl implements TranslationService {
    Logger logger = LoggerFactory.getLogger(TranslationServiceImpl.class);

    @Value("${translate.baidu.url}")
    private String url;
    @Value("${translate.baidu.app-id}")
    private String appId;
    @Value("${translate.baidu.secret-key}")
    private String secretKey;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String language(String from, String to, String q) throws Exception {
        // 随机数，生成的方式没有限制，当前时间戳也可以
        String salt = UUID.randomUUID().toString();
        // 按照 appid+q+salt+密钥（百度翻译开放平台的密钥）的顺序拼接得到签名。
        String sign = DigestUtils.md5DigestAsHex(
                new StringBuilder(appId).append(q).append(salt).append(secretKey).toString()
                        .getBytes("utf-8")
        );
        String uri = url + "?q=" + q + "&from=" + from + "&to=" + to + "&appid=" + appId + "&salt=" + salt + "&sign=" + sign;

        ResponseEntity<String> postResult = restTemplate.postForEntity(uri, null, String.class);
        logger.info("postResult {}", JSON.toJSONString(postResult));
        if (HttpStatus.OK.equals(postResult.getStatusCode())) {
            throw new Exception(postResult.getBody());
        }
        JSONObject jsonObject = JSONObject.parseObject(postResult.getBody());
        return jsonObject.getJSONArray("trans_result").getJSONObject(0).getString("dst");
    }
}
