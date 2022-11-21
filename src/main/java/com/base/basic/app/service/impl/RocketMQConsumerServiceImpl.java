package com.base.basic.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.base.basic.app.service.RocketMQConsumerService;
import com.base.basic.domain.entity.v0.IamUser;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author yang.gao
 * @description
 * @date 11/21/22 10:50 上午
 */
@Service
@RocketMQMessageListener(topic = "user_topic", consumerGroup = "base-group", consumeMode = ConsumeMode.ORDERLY)
public class RocketMQConsumerServiceImpl implements RocketMQConsumerService, RocketMQListener<IamUser> {
    Logger logger = LoggerFactory.getLogger(RocketMQConsumerServiceImpl.class);

    @Override
    public void onMessage(IamUser message) {
        logger.info("消费消息 {}", JSON.toJSONString(message));
    }
}
