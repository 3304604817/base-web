package com.base.basic.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.basic.app.service.LotteryDltHistoryService;
import com.base.basic.domain.entity.v1.LotteryDltHistory;
import com.base.basic.infra.mapper.LotteryDltHistoryMapper;
import com.base.common.util.convert.ObjectConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public void getDltHistory(){
        // 每页抓取多少数据
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

                try {
                    lotteryDltHistoryMapper.insertSelective(lotteryDltHistory);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println(list);
        }
    }

    @Override
    public void dltDataAnalysis0(String drawTimeFm, String drawTimeTo){

        List<String> list = new ArrayList<>(32);

        list.add("----------------前区----------------" + "--------------后区-------------");
        list.add(String.format("%-12s", "")
                + String.format("%-12s", "1区")
                + String.format("%-12s", "2区")
                + String.format("%-12s", "3区")
                + String.format("%-12s", "4区")
                + String.format("%-12s", "5区")
                + String.format("%-12s", "1区")
                + String.format("%-12s", "2区"));

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

            list.add(String.format("%-12s", i+"号出现次数")
                    + String.format("%-12s", countFrontArea1)
                    + String.format("%-12s", countFrontArea2)
                    + String.format("%-12s", countFrontArea3)
                    + String.format("%-12s", countFrontArea4)
                    + String.format("%-12s", countFrontArea5)
                    + (i <= 12 ? String.format("%-12s", countEndArea1) : "")
                    + (i <= 12 ? String.format("%-12s", countEndArea2) : ""));
        }

        list.stream().forEach(p -> {
            System.out.println(p);
        });
        System.out.println("------------打印完成");
    }

    @Override
    public void dltDataAnalysis1(String drawTimeFm, String drawTimeTo){
        List<String> list = new ArrayList<>(32);

        list.add("--------前区" + "----后区----");

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
                    + String.format("%-8s", countFrontArea1 + countFrontArea2 + countFrontArea3 + countFrontArea4 + countFrontArea5)
                    + (i <= 12 ? String.format("%-8s", countEndArea1 + countEndArea2) : ""));
        }

        list.stream().forEach(p -> {
            System.out.println(p);
        });
        System.out.println("------------打印完成");
    }

    @Override
    public void dltDataAnalysis2(String drawTimeFm, String drawTimeTo){

        LotteryDltHistory lotteryDltHistory = new LotteryDltHistory();
        lotteryDltHistory.setDrawTimeFm(drawTimeFm);
        lotteryDltHistory.setDrawTimeTo(drawTimeTo);
        List<LotteryDltHistory> lotteryDltHistorys = lotteryDltHistoryMapper.list(lotteryDltHistory);

        List<String> list = new ArrayList<>(32);
        list.add("------------------------" + "--------------后区-------------" + "--------------合计-------------");

        Long frontAreaSum = 0L;
        Long endAreaSum = 0L;
        Long allSum = 0L;
        for(LotteryDltHistory history:lotteryDltHistorys){
            history.setFrontAreaSum(history.getFrontArea1() + history.getFrontArea2() + history.getFrontArea3() + history.getFrontArea4() + history.getFrontArea5());
            history.setEndAreaSum(history.getEndArea1() + history.getEndArea2());
            history.setAllSum(history.getFrontAreaSum() + history.getEndAreaSum());

            frontAreaSum = frontAreaSum + history.getFrontAreaSum();
            endAreaSum = endAreaSum + history.getEndAreaSum();
            allSum = allSum + history.getAllSum();
        }
        list.add("-----------" + "每个前区平均数------" + "每个后区平均数----");
        list.add(String.format("%-20s", "平均数")
                + String.format("%-20s", new BigDecimal(frontAreaSum).divide(new BigDecimal(35), BigDecimal.ROUND_HALF_UP, 6).divide(new BigDecimal(lotteryDltHistorys.size()), BigDecimal.ROUND_HALF_UP, 6))
                + String.format("%-20s", new BigDecimal(endAreaSum).divide(new BigDecimal(12), BigDecimal.ROUND_HALF_UP, 6).divide(new BigDecimal(lotteryDltHistorys.size()), BigDecimal.ROUND_HALF_UP, 6))
                + String.format("%-20s", new BigDecimal(allSum).divide(new BigDecimal(lotteryDltHistorys.size()), BigDecimal.ROUND_HALF_UP, 6)));
        list.add("-----------" + "前区和平均数------" + "后区和平均数----" + "合计平均数-------------");
        list.add(String.format("%-20s", "平均数")
                + String.format("%-20s", new BigDecimal(frontAreaSum).divide(new BigDecimal(lotteryDltHistorys.size()), BigDecimal.ROUND_HALF_UP, 6))
                + String.format("%-20s", new BigDecimal(endAreaSum).divide(new BigDecimal(lotteryDltHistorys.size()), BigDecimal.ROUND_HALF_UP, 6))
                + String.format("%-20s", new BigDecimal(allSum).divide(new BigDecimal(lotteryDltHistorys.size()), BigDecimal.ROUND_HALF_UP, 6)));
        list.add(String.format("%-20s", "合计")
                + String.format("%-20s", frontAreaSum)
                + String.format("%-20s", endAreaSum)
                + String.format("%-20s", allSum));

        list.stream().forEach(p -> {
            System.out.println(p);
        });
        System.out.println("------------打印完成");
    }

    @Override
    public void dltDataAnalysis3(String drawTimeFm, String drawTimeTo){

        LotteryDltHistory lotteryDltHistory = new LotteryDltHistory();
        lotteryDltHistory.setDrawTimeFm(drawTimeFm);
        lotteryDltHistory.setDrawTimeTo(drawTimeTo);
        List<LotteryDltHistory> lotteryDltHistorys = lotteryDltHistoryMapper.list(lotteryDltHistory);

        /**
         * 最终打印List
         */
        List<String> list = new ArrayList<>(3072);
        list.add(new StringBuilder(String.format("%-6s", "1"))
                .append(String.format("%-6s", "2"))
                .append(String.format("%-6s", "3"))
                .append(String.format("%-6s", "4"))
                .append(String.format("%-6s", "5"))
                .append(String.format("%-6s", "6"))
                .append(String.format("%-6s", "7"))
                .append(String.format("%-6s", "8"))
                .append(String.format("%-6s", "9"))
                .append(String.format("%-6s", "10"))
                .append(String.format("%-6s", "11"))
                .append(String.format("%-6s", "12"))
                .append(String.format("%-6s", "13"))
                .append(String.format("%-6s", "14"))
                .append(String.format("%-6s", "15"))
                .append(String.format("%-6s", "16"))
                .append(String.format("%-6s", "17"))
                .append(String.format("%-6s", "18"))
                .append(String.format("%-6s", "19"))
                .append(String.format("%-6s", "20"))
                .append(String.format("%-6s", "21"))
                .append(String.format("%-6s", "22"))
                .append(String.format("%-6s", "23"))
                .append(String.format("%-6s", "24"))
                .append(String.format("%-6s", "25"))
                .append(String.format("%-6s", "26"))
                .append(String.format("%-6s", "27"))
                .append(String.format("%-6s", "28"))
                .append(String.format("%-6s", "29"))
                .append(String.format("%-6s", "30"))
                .append(String.format("%-6s", "31"))
                .append(String.format("%-6s", "32"))
                .append(String.format("%-6s", "33"))
                .append(String.format("%-6s", "34"))
                .append(String.format("%-6s", "35"))
                .append(String.format("%-6s", "1"))
                .append(String.format("%-6s", "2"))
                .append(String.format("%-6s", "3"))
                .append(String.format("%-6s", "4"))
                .append(String.format("%-6s", "5"))
                .append(String.format("%-6s", "6"))
                .append(String.format("%-6s", "7"))
                .append(String.format("%-6s", "8"))
                .append(String.format("%-6s", "9"))
                .append(String.format("%-6s", "10"))
                .append(String.format("%-6s", "11"))
                .append(String.format("%-6s", "12"))
                .toString()
        );

        for(LotteryDltHistory history:lotteryDltHistorys){
            List<Long> frontAreaList = new ArrayList<>(5);
            frontAreaList.add(history.getFrontArea1());
            frontAreaList.add(history.getFrontArea2());
            frontAreaList.add(history.getFrontArea3());
            frontAreaList.add(history.getFrontArea4());
            frontAreaList.add(history.getFrontArea5());
            frontAreaList = frontAreaList.stream().sorted().collect(Collectors.toList());

            List<Long> endAreaList = new ArrayList<>(2);
            endAreaList.add(history.getEndArea1());
            endAreaList.add(history.getEndArea2());
            endAreaList = endAreaList.stream().sorted().collect(Collectors.toList());

            list.add(new StringBuilder(String.format("%-6s", frontAreaList.contains(1L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(2L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(3L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(4L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(5L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(6L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(7L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(8L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(9L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(10L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(11L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(12L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(13L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(14L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(15L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(16L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(17L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(18L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(19L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(20L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(21L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(22L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(23L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(24L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(25L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(26L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(27L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(28L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(29L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(30L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(31L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(32L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(33L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(34L) ? 1 : ""))
                    .append(String.format("%-6s", frontAreaList.contains(35L) ? 1 : ""))
                    .append(String.format("%-6s", endAreaList.contains(1L) ? 1 : ""))
                    .append(String.format("%-6s", endAreaList.contains(2L) ? 1 : ""))
                    .append(String.format("%-6s", endAreaList.contains(3L) ? 1 : ""))
                    .append(String.format("%-6s", endAreaList.contains(4L) ? 1 : ""))
                    .append(String.format("%-6s", endAreaList.contains(5L) ? 1 : ""))
                    .append(String.format("%-6s", endAreaList.contains(6L) ? 1 : ""))
                    .append(String.format("%-6s", endAreaList.contains(7L) ? 1 : ""))
                    .append(String.format("%-6s", endAreaList.contains(8L) ? 1 : ""))
                    .append(String.format("%-6s", endAreaList.contains(9L) ? 1 : ""))
                    .append(String.format("%-6s", endAreaList.contains(10L) ? 1 : ""))
                    .append(String.format("%-6s", endAreaList.contains(11L) ? 1 : ""))
                    .append(String.format("%-6s", endAreaList.contains(12L) ? 1 : ""))
                    .toString()
            );
        }


        list.stream().forEach(p->{
            System.out.println(p);
        });
    }

    @Override
    public void dltDataAnalysis4(String drawTimeFm, String drawTimeTo){
        LotteryDltHistory lotteryDltHistory = new LotteryDltHistory();
        lotteryDltHistory.setDrawTimeFm(drawTimeFm);
        lotteryDltHistory.setDrawTimeTo(drawTimeTo);
        List<LotteryDltHistory> lotteryDltHistorys = lotteryDltHistoryMapper.list(lotteryDltHistory);

        List<String> frontList = new ArrayList<>(3072);
        List<String> endList = new ArrayList<>(3072);
        List<String> allList = new ArrayList<>(3072);
        for(LotteryDltHistory history:lotteryDltHistorys){
            List<Long> frontAreaList = new ArrayList<>(5);
            frontAreaList.add(history.getFrontArea1());
            frontAreaList.add(history.getFrontArea2());
            frontAreaList.add(history.getFrontArea3());
            frontAreaList.add(history.getFrontArea4());
            frontAreaList.add(history.getFrontArea5());
            frontAreaList = frontAreaList.stream().sorted().collect(Collectors.toList());

            List<Long> endAreaList = new ArrayList<>(2);
            endAreaList.add(history.getEndArea1());
            endAreaList.add(history.getEndArea2());
            endAreaList = endAreaList.stream().sorted().collect(Collectors.toList());

            frontList.add(Arrays.toString(frontAreaList.toArray()));
            endList.add(Arrays.toString(endAreaList.toArray()));
            allList.add(Arrays.toString(frontAreaList.toArray()) + "-" + Arrays.toString(endAreaList.toArray()));
        }

        System.out.println("------------------------------前区");
        Map<String, Long> frontCollect = frontList.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        for (Map.Entry<String, Long> entry : frontCollect.entrySet()) {
            if(entry.getValue() > 1){
                System.out.println(entry.getValue() + "-----" + entry.getKey());
            }
        }

        System.out.println("------------------------------后区");
        Map<String, Long> endCollect = endList.stream().collect(Collectors.groupingBy(e->e, Collectors.counting()));
        for (Map.Entry<String, Long> entry : endCollect.entrySet()) {
            if(entry.getValue() > 1){
                System.out.println(entry.getValue() + "-----" + entry.getKey());
            }
        }

        System.out.println("------------------------------全区");
        Map<String, Long> allCollect = allList.stream().collect(Collectors.groupingBy(e->e, Collectors.counting()));
        for (Map.Entry<String, Long> entry : allCollect.entrySet()) {
            if(entry.getValue() > 1){
                System.out.println(entry.getValue() + "-----" + entry.getKey());
            }
        }
    }
}
