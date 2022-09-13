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

import java.util.ArrayList;
import java.util.List;

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
    public void dltDataAnalysis(String drawTimeFm, String drawTimeTo){

        List<String> list = new ArrayList<>(32);

        list.add("----------------前区----------------" + "--------------后区-------------");
        list.add(String.format("%-8s", "")
                + String.format("%-8s", "1区")
                + String.format("%-8s", "2区")
                + String.format("%-8s", "3区")
                + String.format("%-8s", "4区")
                + String.format("%-8s", "5区")
                + String.format("%-8s", "1区")
                + String.format("%-8s", "2区"));

        for(int i = 1; i <= 35; i++){
            /**
             * 前区
             */
            Long countFrontArea1 = lotteryDltHistoryMapper.countFrontArea1(i, drawTimeFm, drawTimeTo);
            Long countFrontArea2 = lotteryDltHistoryMapper.countFrontArea2(i, drawTimeFm, drawTimeTo);
            Long countFrontArea3 = lotteryDltHistoryMapper.countFrontArea3(i, drawTimeFm, drawTimeTo);
            Long countFrontArea4 = lotteryDltHistoryMapper.countFrontArea4(i, drawTimeFm, drawTimeTo);
            Long countFrontArea5 = lotteryDltHistoryMapper.countFrontArea5(i, drawTimeFm, drawTimeTo);

            /**
             * 后区
             */
            Long countEndArea1 = null;
            Long countEndArea2 = null;
            if(i <= 12){
                countEndArea1 = lotteryDltHistoryMapper.countEndArea1(i, drawTimeFm, drawTimeTo);
                countEndArea2 = lotteryDltHistoryMapper.countEndArea2(i, drawTimeFm, drawTimeTo);
            }

            list.add(String.format("%-8s", i)
                    + String.format("%-8s", countFrontArea1)
                    + String.format("%-8s", countFrontArea2)
                    + String.format("%-8s", countFrontArea3)
                    + String.format("%-8s", countFrontArea4)
                    + String.format("%-8s", countFrontArea5)
                    + String.format("%-8s", countEndArea1)
                    + String.format("%-8s", countEndArea2));
        }

        list.stream().forEach(p -> {
            System.out.println(p);
        });
        System.out.println("------------打印完成");
    }
}
