package com.base.basic.app.service;

import com.base.basic.domain.entity.v1.DataEcharts;
import com.base.basic.domain.entity.v1.Role;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageInfo;

public interface DataEchartsService {
    PageInfo<DataEcharts> pageList(PageParmaters pageParmaters, DataEcharts searchBody);
}
