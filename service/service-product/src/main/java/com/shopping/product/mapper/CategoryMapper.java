package com.shopping.product.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shopping.model.product.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品三级分类 Mapper 接口
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-17
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
