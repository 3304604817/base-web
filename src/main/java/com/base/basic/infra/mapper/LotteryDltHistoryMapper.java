package com.base.basic.infra.mapper;


import com.base.basic.domain.entity.v1.LotteryDltHistory;
import com.base.common.util.mybatis.mapper.SupperMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 大乐透中奖历史Mapper
 *
 * @author 3304604817@qq.com 2022-09-13 11:35:31
 */
public interface LotteryDltHistoryMapper extends SupperMapper<LotteryDltHistory> {

    Long countFrontArea1(@Param("frontAreaNum") Integer frontAreaNum);

    Long countFrontArea2(@Param("frontAreaNum") Integer frontAreaNum);

    Long countFrontArea3(@Param("frontAreaNum") Integer frontAreaNum);

    Long countFrontArea4(@Param("frontAreaNum") Integer frontAreaNum);

    Long countFrontArea5(@Param("frontAreaNum") Integer frontAreaNum);

    Long countEndArea1(@Param("endAreaNum") Integer endAreaNum);

    Long countEndArea2(@Param("endAreaNum") Integer endAreaNum);
}
