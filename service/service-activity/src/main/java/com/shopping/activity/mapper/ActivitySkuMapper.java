package com.shopping.activity.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shopping.model.activity.ActivitySku;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 活动参与商品 Mapper 接口
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-21
 */
@Mapper
public interface ActivitySkuMapper extends BaseMapper<ActivitySku> {

}
