package com.base.basic.domain.exc;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OldGoodsExcelModel {

    @ExcelProperty(value = "商品唯一标志UUID" ,index = 0)
    private String sku;

    @ExcelProperty(value = "商品名称" ,index = 1)
    private String name;

    @ExcelProperty(value = "价格" ,index = 2)
    private BigDecimal price;

    @ExcelProperty(value = "图片" ,index = 3)
    private String pictureUrl;
}
