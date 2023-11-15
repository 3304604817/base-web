package com.base.basic.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.base.basic.app.service.EchartsRecordsService;
import com.base.basic.domain.vo.v0.EchartsLegendVO;
import com.base.basic.domain.vo.v0.EchartsRecordsVO;
import com.base.common.util.excel.helper.EasyExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

@Service
public class EchartsRecordsServiceImpl implements EchartsRecordsService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private EchartsRecordsService echartsRecordsService;

    @Override
    public EchartsRecordsVO reviewChart(){
        String value = (String) redisTemplate.opsForValue().get("line");
        return JSONObject.parseObject(value, EchartsRecordsVO.class);
    }

    @Override
    public String importData(MultipartFile file){
        EasyExcelHelper.getInstance().easyDynamicBatchImport(0, echartsRecordsService, file);
        return "导入成功";
    }

    /**
     * 重写导入方法
     * @param list
     */
    @Override
    public void easySave(List<Object> list) {
        EchartsRecordsVO echartsRecordsVO = new EchartsRecordsVO();
        List<EchartsLegendVO> echartsLegendVOList = new ArrayList<>(8);
        for(int lineNum = 0; lineNum < list.size(); lineNum++){
            LinkedHashMap<Integer, String> lineLinkedMap = (LinkedHashMap<Integer, String>)list.get(lineNum);
            if(0 == lineNum){
                // X 坐标
                List<String> xAxis = new ArrayList<>(16);
                for(int i = 1; i < lineLinkedMap.size(); i++){
                    xAxis.add(lineLinkedMap.get(i));
                }

                echartsRecordsVO.setxAxis(xAxis);
            }else {
                EchartsLegendVO echartsLegendVO = new EchartsLegendVO();
                List<BigDecimal> values = new ArrayList<>(16);
                for(int i = 1; i < lineLinkedMap.size(); i++){
                    values.add(new BigDecimal(lineLinkedMap.get(i)));
                }
                echartsLegendVO.setLegend(lineLinkedMap.get(0));
                echartsLegendVO.setValues(values);
                echartsLegendVOList.add(echartsLegendVO);
            }
        }

        echartsRecordsVO.setLegendVOList(echartsLegendVOList);

        /**
         * 数据推到Redis
         */
//        String key = "line-" + UUID.randomUUID().toString();
        String key = "line";
        String value = JSONObject.toJSONString(echartsRecordsVO);
        redisTemplate.opsForValue().set(key, value);
    }
}
