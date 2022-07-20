package com.base.basic.infra.mapper;

import com.base.basic.domain.entity.v1.OldGoods;
import com.base.common.util.mybatis.mapper.SupperMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 二手商品表Mapper
 */
public interface OldGoodsMapper extends SupperMapper<OldGoods> {

    List<OldGoods> list(OldGoods oldGoods);
}
