package com.base.basic.infra.mapper;


import com.base.basic.domain.entity.v1.LotteryDltHistory;
import com.base.common.util.mybatis.mapper.SupperMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 大乐透中奖历史Mapper
 *
 * @author 3304604817@qq.com 2022-09-13 11:35:31
 */
public interface LotteryDltHistoryMapper extends SupperMapper<LotteryDltHistory> {

    List<LotteryDltHistory> list(LotteryDltHistory lotteryDltHistory);

    Long countFrontArea1(@Param("frontAreaNum") Integer frontAreaNum, @Param("drawTimeFm") String drawTimeFm, @Param("drawTimeTo") String drawTimeTo);

    Long countFrontArea2(@Param("frontAreaNum") Integer frontAreaNum, @Param("drawTimeFm") String drawTimeFm, @Param("drawTimeTo") String drawTimeTo);

    Long countFrontArea3(@Param("frontAreaNum") Integer frontAreaNum, @Param("drawTimeFm") String drawTimeFm, @Param("drawTimeTo") String drawTimeTo);

    Long countFrontArea4(@Param("frontAreaNum") Integer frontAreaNum, @Param("drawTimeFm") String drawTimeFm, @Param("drawTimeTo") String drawTimeTo);

    Long countFrontArea5(@Param("frontAreaNum") Integer frontAreaNum, @Param("drawTimeFm") String drawTimeFm, @Param("drawTimeTo") String drawTimeTo);

    Long countEndArea1(@Param("endAreaNum") Integer endAreaNum, @Param("drawTimeFm") String drawTimeFm, @Param("drawTimeTo") String drawTimeTo);

    Long countEndArea2(@Param("endAreaNum") Integer endAreaNum, @Param("drawTimeFm") String drawTimeFm, @Param("drawTimeTo") String drawTimeTo);
}
