package com.base.basic.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.base.basic.app.service.RocketMQProducerService;
import com.base.basic.domain.entity.v0.IamUser;
import com.base.basic.infra.mapper.UserMapper;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @author yang.gao
 * @description
 * @date 11/21/22 10:10 上午
 */
@Service
public class RocketMQProducerServiceImpl implements RocketMQProducerService {
    Logger logger = LoggerFactory.getLogger(RocketMQProducerServiceImpl.class);

    @Autowired
    @SuppressWarnings("all")
    private UserMapper userMapper;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void syncSend(){
        IamUser user = userMapper.selectByPrimaryKey(1L);
        String realName = user.getRealName();
        for(int i = 0; i < 100; i++){
            user.setLoginName("user-" + i);
            user.setRealName(realName + "-" + i);
            SendResult sendResult = rocketMQTemplate.syncSend("user_topic", MessageBuilder.withPayload(user).build());
            logger.info("生产消息 {}", JSON.toJSONString(sendResult));
        }
    }

    @Override
    public void asyncSend(){
        IamUser user = userMapper.selectByPrimaryKey(1L);
        String realName = user.getRealName();
        for(int i = 0; i < 100; i++){
            user.setLoginName("user-" + i);
            user.setRealName(realName + "-" + i);
            rocketMQTemplate.asyncSend("user_topic", MessageBuilder.withPayload(user).build(), new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    logger.info("生产异步消息 {}", JSON.toJSONString(sendResult));
                }

                @Override
                public void onException(Throwable e) {
                    logger.info("消息发送失败 {}", e);
                }
            });
        }
    }

    @Override
    public void syncSendOrderly(){
        IamUser user = userMapper.selectByPrimaryKey(1L);
        String realName = user.getRealName();
        for(int i = 0; i < 10; i++){
            user.setLoginName("user-" + i);
            user.setRealName(realName + "-" + i);
            // hashKey 指定消息发送到哪个队列
            SendResult sendResult = rocketMQTemplate.syncSendOrderly("user_topic", MessageBuilder.withPayload(user).build(), "1");
            logger.info("生产消息 {}", JSON.toJSONString(sendResult));
        }

        user = userMapper.selectByPrimaryKey(-1L);
        realName = user.getRealName();
        for(int i = 0; i < 10; i++){
            user.setLoginName("user-" + i);
            user.setRealName(realName + "-" + i);
            // hashKey 指定消息发送到哪个队列
            SendResult sendResult = rocketMQTemplate.syncSendOrderly("user_topic", MessageBuilder.withPayload(user).build(), "-1");
            logger.info("生产消息 {}", JSON.toJSONString(sendResult));
        }
    }

    @Override
    public void delayAsyncSend(){
        IamUser user = userMapper.selectByPrimaryKey(1L);
        String realName = user.getRealName();
        for(int i = 0; i < 100; i++){
            user.setLoginName("user-" + i);
            user.setRealName(realName + "-" + i);
            /**
             * 延迟消息级别设置
             * delayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
             */
            rocketMQTemplate.asyncSend("user_topic", MessageBuilder.withPayload(user).build(), new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    logger.info("生产延迟异步消息 {}", JSON.toJSONString(sendResult));
                }

                @Override
                public void onException(Throwable e) {
                    logger.info("消息发送失败 {}", e);
                }
            }, 3000, 4);
        }
    }
}
