package com.base.basic.app.service;

import com.base.basic.domain.entity.v1.DataDictionary;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageInfo;
import org.springframework.web.servlet.ModelAndView;

/**
 * 数据字典应用服务
 */
public interface DataDictionaryService {

    /**
     * 数据字典分页查询
     * @param pageParmaters 分页信息
     * @return
     */
    PageInfo<DataDictionary> pageList(PageParmaters pageParmaters, DataDictionary dataDictionary);

    /**
     * 保存
     */
    DataDictionary save(DataDictionary dataDictionary);

    /**
     * 数据字典明细
     */
    DataDictionary detail(Long id);

    ModelAndView delete(Long id);
}
