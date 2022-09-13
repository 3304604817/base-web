package com.base.basic.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.basic.api.controller.v0.HttpRequestController;
import com.base.basic.app.service.LotteryDltHistoryService;
import com.base.basic.domain.entity.v1.LotteryDltHistory;
import com.base.common.util.convert.ObjectConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * @author yang.gao
 * @description
 * @date 2022/9/13 11:39
 */
@Service
public class LotteryDltHistoryServiceImpl implements LotteryDltHistoryService {
    Logger logger = LoggerFactory.getLogger(LotteryDltHistoryServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void getDltHistory(){
        int pageSize = 30;
        for(int pageNo = 1;; pageNo++){
            ResponseEntity<String> getResult = restTemplate.getForEntity(
                    "https://webapi.sporttery.cn/gateway/lottery/getHistoryPageListV1.qry?gameNo=85&provinceId=0&pageSize={pageSize}&isVerify=1&pageNo={pageNo}",
                    String.class,
                    pageSize, pageNo);
            logger.info("getResult {}", JSON.toJSONString(getResult));

            JSONObject body = JSONObject.parseObject(getResult.getBody());
            JSONObject value = body.getJSONObject("value");
            JSONArray list = value.getJSONArray("list");

            // 查不到数据说明所有数据都查完了
            if(0 == list.size()){
                break;
            }

            for(Object obj:list){
                JSONObject data = ObjectConvertUtil.convertJsonObject(obj);

                LotteryDltHistory lotteryDltHistory = new LotteryDltHistory();
//                lotteryDltHistory.setDrawNum();
            }
            System.out.println(list);
        }
    }
}
