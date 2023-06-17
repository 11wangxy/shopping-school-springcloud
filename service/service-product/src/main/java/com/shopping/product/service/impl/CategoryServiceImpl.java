package com.shopping.product.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shopping.model.product.Category;
import com.shopping.product.mapper.CategoryMapper;
import com.shopping.product.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.vo.product.CategoryQueryVo;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 商品三级分类 服务实现类
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-17
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public IPage<Category> selectPage(Long page, Long limit, CategoryQueryVo categoryQueryVo) {
        Page<Category> param = new Page<>(page,limit);
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        String name = categoryQueryVo.getName();
        if(!StringUtils.isEmpty(name)){
            queryWrapper.like(Category::getName,name);
        }
        Page<Category> categoryPage = baseMapper.selectPage(param, queryWrapper);
        return categoryPage;
    }
}
