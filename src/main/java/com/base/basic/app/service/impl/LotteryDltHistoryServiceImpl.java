package com.base.basic.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.basic.api.controller.v0.HttpRequestController;
import com.base.basic.app.service.LotteryDltHistoryService;
import com.base.basic.domain.entity.v1.LotteryDltHistory;
import com.base.basic.infra.mapper.LotteryDltHistoryMapper;
import com.base.common.util.convert.ObjectConvertUtil;
import org.checkerframework.checker.units.qual.A;
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
    @Autowired
    @SuppressWarnings("all")
    private LotteryDltHistoryMapper lotteryDltHistoryMapper;

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
                lotteryDltHistory.setDrawNum(data.getString("lotteryDrawNum"));
                lotteryDltHistory.setDrawTime(data.getDate("lotteryDrawTime"));

                String lotteryDrawResult = data.getString("lotteryDrawResult");
                String [] result = lotteryDrawResult.split("\\s+");
                lotteryDltHistory.setFrontArea1(Long.valueOf(result[0]));
                lotteryDltHistory.setFrontArea2(Long.valueOf(result[1]));
                lotteryDltHistory.setFrontArea3(Long.valueOf(result[2]));
                lotteryDltHistory.setFrontArea4(Long.valueOf(result[3]));
                lotteryDltHistory.setFrontArea5(Long.valueOf(result[4]));
                lotteryDltHistory.setEndArea1(Long.valueOf(result[5]));
                lotteryDltHistory.setEndArea2(Long.valueOf(result[6]));

                lotteryDltHistoryMapper.insertSelective(lotteryDltHistory);
            }
            System.out.println(list);
        }
    }

    @Override
    public void dltDataAnalysis(){

        System.out.println("------------前区-------------" + "------------后区-------------");
        System.out.println("1区｜2区｜3区｜4区｜5区" + "1区｜2区");
        for(int i = 1; i <= 30; i++){
            /**
             * 前区
             */
            Long countFrontArea1 = lotteryDltHistoryMapper.countFrontArea1(1);
            Long countFrontArea2 = lotteryDltHistoryMapper.countFrontArea2(1);
            Long countFrontArea3 = lotteryDltHistoryMapper.countFrontArea3(1);
            Long countFrontArea4 = lotteryDltHistoryMapper.countFrontArea4(1);
            Long countFrontArea5 = lotteryDltHistoryMapper.countFrontArea5(1);

            /**
             * 后区
             */
            Long countEndArea1 = null;
            Long countEndArea2 = null;
            if(i <= 12){
                countEndArea1 = lotteryDltHistoryMapper.countEndArea1(1);
                countEndArea2 = lotteryDltHistoryMapper.countEndArea2(1);
            }


            System.out.println(i + ": " + countFrontArea1 + "  " + countFrontArea2 + "  " + countFrontArea3 + "  " + countFrontArea4 + "  " + countFrontArea5 + "  " + countEndArea1 + "  " + countEndArea2);
        }

        System.out.println("------------打印完成");
    }
}
