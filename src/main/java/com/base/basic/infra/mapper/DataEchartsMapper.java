package com.base.basic.infra.mapper;

import com.base.basic.domain.entity.v1.DataEcharts;
import com.base.common.util.mybatis.mapper.SupperMapper;

import java.util.List;

public interface DataEchartsMapper extends SupperMapper<DataEcharts> {

    List<DataEcharts> list(DataEcharts dataEcharts);
}
