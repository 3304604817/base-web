package com.base.basic.infra.repository.impl;

import com.base.basic.domain.entity.v1.OldGoods;
import com.base.basic.domain.repository.OldGoodsRepository;
import com.base.basic.infra.mapper.OldGoodsMapper;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 二手商品表 资源库实现
 *
 * @author 1 2022-03-23 14:01:29
 */
@Component
public class OldGoodsRepositoryImpl implements OldGoodsRepository {

    @Autowired
    private OldGoodsMapper oldGoodsMapper;

    @Override
    public PageInfo<OldGoods> pageList(PageParmaters pageParmaters, OldGoods searchBody){
        return PageHelper.startPage(pageParmaters.getPage(), pageParmaters.getLimit()).doSelectPageInfo(() -> oldGoodsMapper.list(searchBody));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void easySave(List<Object> list) {
        System.out.println("开始保存");
    }
}
