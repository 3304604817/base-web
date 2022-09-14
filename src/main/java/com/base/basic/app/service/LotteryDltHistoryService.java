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
     * 大乐透数据分析-查各区数字分布情况
     */
    void dltDataAnalysis(String drawTimeFm, String drawTimeTo);

    /**
     * 大乐透数据分析-查前后区所有数字分布
     */
    void dltDataAnalysis1(String drawTimeFm, String drawTimeTo);

    /**
     * 大乐透数据分析-查前后区数字和
     */
    void dltDataAnalysis2(String drawTimeFm, String drawTimeTo);
}
