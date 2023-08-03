package com.base.basic.app.service;

import com.base.basic.domain.entity.v1.InterfaceParams;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageInfo;

public interface InterfaceParamsService {

    PageInfo<InterfaceParams> pageList(PageParmaters pageParmaters, InterfaceParams searchBody);
}
