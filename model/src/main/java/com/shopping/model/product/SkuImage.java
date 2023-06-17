package com.shopping.model.product;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shopping.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@ApiModel(description = "SkuImages")
@TableName("sku_image")

@Accessors(chain = true)
public class SkuImage extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "sku_id")
	@TableField("sku_id")
	private Long skuId;

	@ApiModelProperty(value = "图片名称")
	@TableField("img_name")
	private String imgName;

	@ApiModelProperty(value = "图片地址")
	@TableField("img_url")
	private String imgUrl;

	@ApiModelProperty(value = "排序")
	@TableField("sort")
	private Integer sort;

}