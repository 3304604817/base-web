package com.base.basic.app.service.impl;

import com.base.basic.app.service.InterfaceParamsService;
import com.base.basic.domain.entity.v1.Interface;
import com.base.basic.domain.entity.v1.InterfaceParams;
import com.base.basic.infra.mapper.InterfaceParamsMapper;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterfaceParamsServiceImpl implements InterfaceParamsService {

    @Autowired
    @SuppressWarnings("all")
    private InterfaceParamsMapper interfaceParamsMapper;

    @Override
    public PageInfo<InterfaceParams> pageList(PageParmaters pageParmaters, InterfaceParams searchBody){
        return PageHelper.startPage(pageParmaters.getPage(), pageParmaters.getLimit()).doSelectPageInfo(()->interfaceParamsMapper.list(searchBody));
    }
}
