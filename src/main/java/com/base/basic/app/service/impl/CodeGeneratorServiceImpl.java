package com.base.basic.app.service.impl;

import com.base.basic.app.service.CodeGeneratorService;
import com.base.basic.domain.vo.v0.TableVO;
import com.base.basic.infra.mapper.CodeGeneratorMapper;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeGeneratorServiceImpl implements CodeGeneratorService {

    @Autowired
    @SuppressWarnings("all")
    private CodeGeneratorMapper codeGeneratorMapper;

    @Override
    public PageInfo<TableVO> pageList(PageParmaters pageParmaters, TableVO tableVO){
        return PageHelper.startPage(pageParmaters.getPage(), pageParmaters.getLimit()).doSelectPageInfo(() -> codeGeneratorMapper.list(tableVO));
    }
}
