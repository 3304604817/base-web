package com.base.basic.app.service;

/**
 * @author yang.gao
 * @description
 * @date 11/21/22 10:10 上午
 */
public interface RocketMQProducerService {

    /**
     * 发送同步消息
     */
    void syncSend();

    /**
     * 发送异步消息
     */
    void asyncSend();

    /**
     * 发送顺序消息
     */
    void syncSendOrderly();

    /**
     * 发送延迟异步消息
     */
    void delayAsyncSend();
}
