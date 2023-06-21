package com.shopping.activity.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shopping.model.activity.ActivityInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 活动表 Mapper 接口
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-21
 */
@Mapper
public interface ActivityInfoMapper extends BaseMapper<ActivityInfo> {

    List<Long> selectSkuIdListExist(@Param("skuIdList") List<Long> skuIdList);
}
