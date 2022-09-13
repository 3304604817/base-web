package com.base.basic.app.service;

/**
 * @author yang.gao
 * @description
 * @date 2022/9/13 11:39
 */
public interface LotteryDltHistoryService {

    /**
     * 抓取大乐透历史中奖信息
     */
    void getDltHistory();

    /**
     * 大乐透数据分析
     */
    void dltDataAnalysis();
}
