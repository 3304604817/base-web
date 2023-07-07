package com.base.basic.job;

import com.base.common.cache.ServerClusterCache;
import com.base.common.scheduled.runner.ScheduledRunner;
import com.base.common.util.http.RestfulUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component("HeartCheckJobHandler")
public class HeartCheckJobHandler implements ScheduledRunner {
    private Logger logger = LoggerFactory.getLogger(HeartCheckJobHandler.class);

    @Override
    public void run(String args) {
        logger.info("心跳检测");
        Set<String> addressSet = ServerClusterCache.getServerCluster().keySet();
        for(String address:addressSet){
            int code = RestfulUtil.httpGet("http://" + address + "/cluster/heart-check", null, null, RestfulUtil.UTF8_CHARSET).getCode();
            if(200 == code){
                ServerClusterCache.serverUp(address);
            }else {
                ServerClusterCache.serverDown(address);
            }
        }
    }
}
