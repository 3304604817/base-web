package com.base.basic.app.service;

import com.base.basic.domain.entity.v1.DataDictionary;
import com.base.basic.domain.vo.v0.TableVO;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageInfo;

public interface CodeGeneratorService {

    PageInfo<TableVO> pageList(PageParmaters pageParmaters, TableVO tableVO);
}
