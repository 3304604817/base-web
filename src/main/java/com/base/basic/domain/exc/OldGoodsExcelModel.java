package com.base.basic.domain.exc;

import com.alibaba.excel.annotation.ExcelProperty;
import java.math.BigDecimal;

public class OldGoodsExcelModel {

    @ExcelProperty(value = "商品唯一标志UUID" ,index = 0)
    private String sku;

    @ExcelProperty(value = "商品名称" ,index = 1)
    private String name;

    @ExcelProperty(value = "价格" ,index = 2)
    private BigDecimal price;

    @ExcelProperty(value = "图片" ,index = 3)
    private String pictureUrl;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
