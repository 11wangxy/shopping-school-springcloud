package com.shopping.sys.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.model.sys.RegionWare;
import com.shopping.vo.sys.RegionWareQueryVo;

/**
 * <p>
 * 城市仓库关联表 服务类
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-17
 */
public interface RegionWareService extends IService<RegionWare> {

    IPage<RegionWare> pages(Long page, Long limit, RegionWareQueryVo regionWareQueryVo);

    void saveRegionWare(RegionWare regionWare);

    void updateStatus(Long id, Integer status);
}
