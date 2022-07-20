package com.base.basic.app.service;

import com.base.basic.domain.entity.v1.OldGoods;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 二手商品表应用服务
 */
public interface OldGoodsService {

    PageInfo<OldGoods> pageList(PageParmaters pageParmaters, OldGoods searchBody);

    OldGoods detail(Long id);

    OldGoods save(OldGoods oldGoods);

    /**
     * 商品导出
     * @param response
     * @return
     */
    String exportData(HttpServletResponse response);

    /**
     * 商品导入
     * @param file
     * @return
     */
    String importData(MultipartFile file);
}
