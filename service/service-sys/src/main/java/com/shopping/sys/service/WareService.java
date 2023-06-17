package com.shopping.sys.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.model.sys.Ware;
import com.shopping.vo.product.WareQueryVo;

/**
 * <p>
 * 仓库表 服务类
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-17
 */
public interface WareService extends IService<Ware> {

    IPage page(Long page, Long limit, WareQueryVo wareQueryVo);
}
