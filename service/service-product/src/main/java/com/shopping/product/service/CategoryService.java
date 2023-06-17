package com.shopping.product.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.model.product.Category;
import com.shopping.vo.product.CategoryQueryVo;

/**
 * <p>
 * 商品三级分类 服务类
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-17
 */
public interface CategoryService extends IService<Category> {

    IPage<Category> selectPage(Long page, Long limit, CategoryQueryVo categoryQueryVo);
}
