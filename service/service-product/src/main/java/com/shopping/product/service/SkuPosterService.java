package com.shopping.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.model.product.SkuPoster;

import java.util.List;

/**
 * <p>
 * 商品海报表 服务类
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-17
 */
public interface SkuPosterService extends IService<SkuPoster> {

    List<SkuPoster> findBySkuId(Long skuId);
}
