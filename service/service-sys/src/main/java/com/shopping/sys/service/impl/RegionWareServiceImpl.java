package com.shopping.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shopping.common.exception.shoppingException;
import com.shopping.common.result.ResultCodeEnum;
import com.shopping.model.sys.RegionWare;
import com.shopping.sys.mapper.RegionWareMapper;
import com.shopping.sys.service.RegionWareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.vo.sys.RegionWareQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 城市仓库关联表 服务实现类
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-17
 */
@Service
public class RegionWareServiceImpl extends ServiceImpl<RegionWareMapper, RegionWare> implements RegionWareService {

    /**
     * 分页查询
     * @param page
     * @param limit
     * @param regionWareQueryVo
     * @return
     */
    @Override
    public IPage<RegionWare> pages(Long page, Long limit, RegionWareQueryVo regionWareQueryVo) {
        IPage<RegionWare> pageParam = new Page<>(page,limit);
        String keyword = regionWareQueryVo.getKeyword();
        LambdaQueryWrapper<RegionWare> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)){
            queryWrapper.like(RegionWare::getRegionName,keyword).
                    or().like(RegionWare::getWareName,keyword);
        }
        IPage<RegionWare> regionWareIPage = baseMapper.selectPage(pageParam,queryWrapper);
        return regionWareIPage;
    }

    @Override
    public void saveRegionWare(RegionWare regionWare) {
        //判断区域是否开通已经
        Long regionId = regionWare.getRegionId();
        LambdaQueryWrapper<RegionWare> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RegionWare::getRegionId,regionId);
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count>0){
            throw new shoppingException(ResultCodeEnum.REGION_OPEN);
        }
        save(regionWare);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        RegionWare regionWare = baseMapper.selectById(id);
        regionWare.setStatus(status);
        baseMapper.updateById(regionWare);
    }
}
