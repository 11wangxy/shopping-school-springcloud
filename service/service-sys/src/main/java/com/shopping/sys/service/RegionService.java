package com.shopping.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.model.sys.Region;

import java.util.List;

/**
 * <p>
 * 地区表 服务类
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-17
 */
public interface RegionService extends IService<Region> {

    List<Region> getRegionByKeyword(String keyword);
}
