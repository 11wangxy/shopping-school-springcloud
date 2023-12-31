package com.shopping.model.product;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shopping.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "SkuPoster")
@TableName("sku_poster")

@Accessors(chain = true)
public class SkuPoster extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "商品id")
	@TableField("sku_id")
	private Long skuId;

	@ApiModelProperty(value = "文件名称")
	@TableField("img_name")
	private String imgName;

	@ApiModelProperty(value = "文件路径")
	@TableField("img_url")
	private String imgUrl;

}

