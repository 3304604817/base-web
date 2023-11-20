package com.base.basic.app.service.impl;

import com.base.basic.app.service.DataEchartsService;
import com.base.basic.domain.entity.v1.DataEcharts;
import com.base.basic.infra.mapper.DataEchartsMapper;
import com.base.common.util.convert.DateConvertUtil;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataEchartsServiceImpl implements DataEchartsService {

    @Autowired
    @SuppressWarnings("all")
    private DataEchartsMapper dataEchartsMapper;

    @Override
    public PageInfo<DataEcharts> pageList(PageParmaters pageParmaters, DataEcharts searchBody) {
        PageInfo<DataEcharts> pageInfo = PageHelper.startPage(pageParmaters.getPage(), pageParmaters.getLimit()).doSelectPageInfo(() -> dataEchartsMapper.list(searchBody));
        for(DataEcharts dataEcharts:pageInfo.getList()){
            dataEcharts.setCreationDateTime(
                    DateConvertUtil.dateTimeString(dataEcharts.getCreationDate())
            );
        }
        return pageInfo;
    }
}
