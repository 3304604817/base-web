package com.base.basic.job;

import com.alibaba.fastjson.JSON;
import com.base.basic.domain.entity.v1.AuditLog;
import com.base.basic.infra.mapper.AuditLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author yang.gao
 * @description
 * @date 2022/7/28 17:48
 */
@Component
public class DemoJobHandler {
    private Logger logger = LoggerFactory.getLogger(DemoJobHandler.class);

    /**
     * 天天基金-优选基金
     */
    private static final String ttGood = "https://appconfig2.1234567.com.cn/config/FundOptimization?CToken=&MobileKey=795C3FB0-DFC1-4564-AE3D-C2C33EBFC264&OSVersion=15.6&ServerVersion=6.5.7&UToken=&UserId=&appName=ttjj&appVersion=6.5.7&deviceid=795C3FB0-DFC1-4564-AE3D-C2C33EBFC264&plat=Iphone&platId=1&product=EFund&version=6.5.7";
    /**
     * 天天基金-周榜
     */
    private static final String ttWeek = "https://fundcomapi.tiantianfunds.com/mm/FundMNewApi/FundMNRank?BUY=true&CLTYPE=&CompanyId=&DISCOUNT=&ENDNAV=&ESTABDATE=&FIELDS=FCODE%2CSHORTNAME%2CISEXCHG%2CISBUY%2CBAGTYPE%2CDWJZ%2CRZDF%2CSYL_Z%2CSYL_Y%2CSYL_3Y%2CSYL_6Y%2CSYL_JN%2CSYL_1N%2CSYL_2N%2CSYL_3N%2CSYL_5N%2CSYL_LN%2CENDNAV%2CFSRQ&FundType=0&ISABNORMAL=true&NEWTOPICAL=&PageIndex=1&PageSize=30&RISKLEVEL=&RLEVEL_SZ=&Sort=desc&SortColumn=SYL_Z&ctoken=&deviceid=795C3FB0-DFC1-4564-AE3D-C2C33EBFC264&passportctoken=&passportid=&passportutoken=&plat=Iphone&product=EFund&uid=&userid=&utoken=&version=6.5.7";
    /**
     * 同花顺-热销周榜
     */
    private static final String thsWeek = "https://fund.10jqka.com.cn/interface/net/rankinglist/rqzwj,week_0_1_20_0_desc_0_0_0_1_0_0_0";

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    @SuppressWarnings("all")
    private AuditLogMapper auditLogMapper;

//    @Scheduled(cron = "*/5 * * * * ?")
    public void run() {
        ResponseEntity<String> ttGoodResult = restTemplate.getForEntity(ttGood, String.class);
        logger.info("ttGoodResult {}", JSON.toJSONString(ttGoodResult));
        AuditLog ttGoodLog = new AuditLog();
        ttGoodLog.setUrl(ttGood);
        ttGoodLog.setMethod("GET");
        ttGoodLog.setRemoteAddr("localhost");
        ttGoodLog.setRemotePort(80L);
        ttGoodLog.setStatus(Long.valueOf(ttGoodResult.getStatusCode().value()));
        ttGoodLog.setResponseBody(ttGoodResult.getBody());
        ttGoodLog.setRemark("天天基金-优选基金");
        auditLogMapper.insertSelective(ttGoodLog);

        ResponseEntity<String> thsWeekResult = restTemplate.getForEntity(thsWeek, String.class);
        logger.info("thsWeekResult {}", JSON.toJSONString(thsWeekResult));
        AuditLog thsWeektLog = new AuditLog();
        thsWeektLog.setUrl(thsWeek);
        thsWeektLog.setMethod("GET");
        thsWeektLog.setRemoteAddr("localhost");
        thsWeektLog.setRemotePort(80L);
        thsWeektLog.setStatus(Long.valueOf(thsWeekResult.getStatusCode().value()));
        thsWeektLog.setResponseBody(thsWeekResult.getBody());
        thsWeektLog.setRemark("同花顺-热销周榜");
        auditLogMapper.insertSelective(thsWeektLog);
    }
}
