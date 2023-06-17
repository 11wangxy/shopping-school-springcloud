package com.shopping.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.model.product.SkuImage;

import java.util.List;

/**
 * <p>
 * 商品图片 服务类
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-17
 */
public interface SkuImageService extends IService<SkuImage> {

    List<SkuImage> findBySkuId(Long skuId);
}
