package com.shopping.vo.activity;


import com.shopping.model.activity.ActivityRule;
import com.shopping.model.activity.ActivitySku;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(description = "活动规则")
@Accessors(chain = true)
public class ActivityRuleVo implements Serializable {
   
   private static final long serialVersionUID = 1L;

   @ApiModelProperty(value = "活动id")
   private Long activityId;
   
   @ApiModelProperty(value = "活动规则list")
   private List<ActivityRule> activityRuleList;

   @ApiModelProperty(value = "活动参与商品list")
   private List<ActivitySku> activitySkuList;

   @ApiModelProperty(value = "优惠券id列表")
   private List<Long> couponIdList;

}