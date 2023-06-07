package com.base.basic.domain.entity.v1;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.base.basic.domain.entity.v0.base.BaseEntity;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 二手商品表
 */
@ApiModel("二手商品表")
@Table(name = "db_old_goods")
public class OldGoods extends BaseEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_SKU = "sku";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_CATEGORY_ID = "categoryId";
    public static final String FIELD_PRICE = "price";
    public static final String FIELD_PICTURE_URL = "pictureUrl";
    public static final String FIELD_FREE_SHIP_FLAG = "freeShipFlag";
    public static final String FIELD_ENABLED_FLAG = "enabledFlag";

    @ApiModelProperty("表ID，主键")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @ApiModelProperty(value = "商品唯一标志UUID")
    private String sku;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "所属品类ID")
    private Long categoryId;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "图片")
    private String pictureUrl;

    @ApiModelProperty(value = "卖家是否包邮")
    private Integer freeShipFlag;

	@ApiModelProperty(value = "是否启用。1启用，0未启用")
    private Integer enabledFlag;

    @ApiModelProperty(value = "商品明细信息")
    @Transient
    private OldGoodsDetail oldGoodsDetail;

    @ApiModelProperty(value = "价格从")
    @Transient
    private BigDecimal priceFm;

    @ApiModelProperty(value = "价格到")
    @Transient
    private BigDecimal priceTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public Integer getFreeShipFlag() {
        return freeShipFlag;
    }

    public void setFreeShipFlag(Integer freeShipFlag) {
        this.freeShipFlag = freeShipFlag;
    }

    public Integer getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(Integer enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public OldGoodsDetail getOldGoodsDetail() {
        return oldGoodsDetail;
    }

    public void setOldGoodsDetail(OldGoodsDetail oldGoodsDetail) {
        this.oldGoodsDetail = oldGoodsDetail;
    }

    public BigDecimal getPriceFm() {
        return priceFm;
    }

    public void setPriceFm(BigDecimal priceFm) {
        this.priceFm = priceFm;
    }

    public BigDecimal getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(BigDecimal priceTo) {
        this.priceTo = priceTo;
    }
}
