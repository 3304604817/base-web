package com.base.basic.infra.mapper;

import com.base.basic.domain.entity.v1.Scheduled;
import com.base.common.util.mybatis.mapper.SupperMapper;

import java.util.List;

public interface ScheduledMapper extends SupperMapper<Scheduled> {

    List<Scheduled> list(Scheduled scheduled);
}
