package com.base.basic.infra.repository.impl;

import com.base.basic.domain.entity.v1.DataDictionary;
import com.base.basic.domain.repository.DataDictionaryRepository;
import com.base.basic.infra.mapper.DataDictionaryMapper;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 数据字典 资源库实现
 */
@Component
public class DataDictionaryRepositoryImpl implements DataDictionaryRepository {

    @Autowired
    private DataDictionaryMapper dataDictionarysMapper;

    @Override
    public PageInfo<DataDictionary> pageList(DataDictionary parameters, PageParmaters pageParmaters){
        return PageHelper.startPage(pageParmaters.getPage(), pageParmaters.getLimit()).doSelectPageInfo(() -> dataDictionarysMapper.list(parameters));
    }
}
