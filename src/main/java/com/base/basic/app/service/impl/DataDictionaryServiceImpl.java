package com.base.basic.app.service.impl;

import com.base.basic.app.service.DataDictionaryService;
import com.base.basic.domain.entity.v1.DataDictionary;
import com.base.basic.domain.entity.v1.DataDictionaryDetail;
import com.base.basic.domain.repository.DataDictionaryRepository;
import com.base.basic.infra.mapper.DataDictionaryDetailMapper;
import com.base.basic.infra.mapper.DataDictionaryMapper;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

/**
 * 数据字典应用服务默认实现
 */
@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {

    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;
    @Autowired
    private DataDictionaryRepository dataDictionaryRepository;
    @Autowired
    private DataDictionaryDetailMapper dataDictionaryDetailMapper;

    @Override
    public PageInfo<DataDictionary> pageList(PageParmaters pageParmaters, DataDictionary dataDictionary){
        PageInfo<DataDictionary> pages = dataDictionaryRepository.pageList(dataDictionary, pageParmaters);
        return pages;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataDictionary save(DataDictionary dataDictionary){
        if(Objects.nonNull(dataDictionary.getId())){
            // 更新
            dataDictionaryMapper.updateByIdSelective(dataDictionary);
        }else{
            // 创建
            dataDictionaryMapper.insertSelective(dataDictionary);
        }

        DataDictionaryDetail dataDictionaryDetail = dataDictionary.getDataDictionaryDetail();
        if(Objects.nonNull(dataDictionaryDetail.getId())){
            // 更新
            dataDictionaryDetailMapper.updateByIdSelective(dataDictionaryDetail);
        }else{
            // 创建
            dataDictionaryDetail.setDicId(dataDictionary.getId());
            dataDictionaryDetailMapper.insertSelective(dataDictionaryDetail);
        }
        return dataDictionary;
    }

    @Override
    public DataDictionary detail(Long id){
        // 获取数据字典头
        DataDictionary dataDictionary = dataDictionaryMapper.selectByPrimaryKey(id);
        DataDictionaryDetail dataDictionaryDetail = new DataDictionaryDetail();
        dataDictionaryDetail.setDicId(dataDictionary.getId());
        dataDictionary.setDataDictionaryDetail(
                dataDictionaryDetailMapper.selectOne(dataDictionaryDetail)
        );
        return dataDictionary;
    }

    @Override
    public ModelAndView delete(Long id){
        dataDictionaryMapper.deleteByPrimaryKey(id);

        /**
         * 初始化静态文件位置和model
         */
        ModelAndView mv = new ModelAndView("/DataDictionary/index.html");
        return mv;
    }
}
