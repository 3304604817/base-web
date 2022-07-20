package com.base.basic.domain.entity.v1;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.base.basic.domain.entity.v0.base.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 二手商品详情
 */
@Data
@ApiModel("二手商品详情")
@Table(name = "old_goods_detail")
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
}
