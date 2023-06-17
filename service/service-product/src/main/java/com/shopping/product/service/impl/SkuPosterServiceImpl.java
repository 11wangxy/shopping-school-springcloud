package com.shopping.product.service.impl;


import com.shopping.model.product.SkuPoster;
import com.shopping.product.mapper.SkuPosterMapper;
import com.shopping.product.service.SkuPosterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品海报表 服务实现类
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-17
 */
@Service
public class SkuPosterServiceImpl extends ServiceImpl<SkuPosterMapper, SkuPoster> implements SkuPosterService {

    @Override
    public List<SkuPoster> findBySkuId(Long skuId) {
        return query().eq("sku_id", skuId).list();
    }
}
