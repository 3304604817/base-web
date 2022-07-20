package com.base.basic.domain.repository;


import com.base.basic.domain.entity.v1.OldGoods;
import com.base.common.util.excel.EasyOperaInterface;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageInfo;

/**
 * 二手商品表资源库
 */
public interface OldGoodsRepository extends EasyOperaInterface {

    PageInfo<OldGoods> pageList(PageParmaters pageParmaters, OldGoods searchBody);
}
