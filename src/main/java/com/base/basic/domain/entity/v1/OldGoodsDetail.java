package com.base.basic.domain.entity.v1;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.base.basic.domain.entity.v0.base.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 二手商品详情
 */
@ApiModel("二手商品详情")
@Table(name = "db_old_goods_detail")
public class OldGoodsDetail extends BaseEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_GOODS_ID = "goodsId";
    public static final String FIELD_SELLER_PHONE = "sellerPhone";
    public static final String FIELD_SELLER_EMAIL = "sellerEmail";
    public static final String FIELD_SELLER_DESCRIPTION = "sellerDescription";
    public static final String FIELD_BUYER_MESSAGE = "buyerMessage";
    public static final String FIELD_ENABLED_FLAG = "enabledFlag";

    @ApiModelProperty("表ID，主键")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

	@ApiModelProperty(value = "商品头ID")
    private Long goodsId;

	@ApiModelProperty(value = "卖家手机")
	private String sellerPhone;

	@ApiModelProperty(value = "卖家邮箱")
	private String sellerEmail;

   	@ApiModelProperty(value = "卖家描述")
    private String sellerDescription;

   	@ApiModelProperty(value = "买家留言")
    private String buyerMessage;

   	@ApiModelProperty(value = "是否启用。1启用，0未启用",required = true)
    private Integer enabledFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getSellerDescription() {
        return sellerDescription;
    }

    public void setSellerDescription(String sellerDescription) {
        this.sellerDescription = sellerDescription;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public Integer getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(Integer enabledFlag) {
        this.enabledFlag = enabledFlag;
    }
}
