package com.base.basic.app.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.basic.app.service.TranslationService;
import com.base.common.util.http.RestfulResponse;
import com.base.common.util.http.RestfulUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TranslationServiceImpl implements TranslationService {

    @Value("${translate.baidu.url}")
    private String url;
    @Value("${translate.baidu.app-id}")
    private String appId;
    @Value("${translate.baidu.secret-key}")
    private String secretKey;

    @Override
    public String language(String from, String to, String q) throws Exception {
        // 随机数，生成的方式没有限制，当前时间戳也可以
        String salt = UUID.randomUUID().toString();
        // 按照 appid+q+salt+密钥（百度翻译开放平台的密钥）的顺序拼接得到签名。
        String sign = DigestUtils.md5DigestAsHex(
                new StringBuilder(appId).append(q).append(salt).append(secretKey).toString()
                        .getBytes("utf-8")
        );

        Map<String, String> requestParams = new HashMap<>();
        // 翻译源语言
        requestParams.put("from", from);
        // 翻译目标语言
        requestParams.put("to", to);
        // 要翻译的文本
        requestParams.put("q", q);
        // 百度翻译开放平台的 APP ID
        requestParams.put("appid", appId);
        requestParams.put("salt", salt);
        requestParams.put("sign", sign);

        Map<String, String> headerVariables = new HashMap<>();
        headerVariables.put("Content-Type", "application/x-www-form-urlencoded");
        RestfulResponse response = RestfulUtil.httpPost(url, requestParams, null, q, RestfulUtil.UTF8_CHARSET);
        if (response.getCode() != 200) {
            throw new Exception(response.getResponseBody());
        }
        JSONObject jsonObject = JSONObject.parseObject(response.getResponseBody());
        return jsonObject.getJSONArray("trans_result").getJSONObject(0).getString("dst");
    }
}
