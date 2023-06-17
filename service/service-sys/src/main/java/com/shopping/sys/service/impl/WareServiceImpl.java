package com.shopping.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shopping.model.sys.Ware;
import com.shopping.sys.mapper.WareMapper;
import com.shopping.sys.service.WareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.vo.product.WareQueryVo;
import org.springframework.cglib.core.MethodWrapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 仓库表 服务实现类
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-17
 */
@Service
public class WareServiceImpl extends ServiceImpl<WareMapper, Ware> implements WareService {

    @Override
    public IPage page(Long page, Long limit, WareQueryVo wareQueryVo) {
        IPage<Ware> param = new Page<>(page,limit);
        LambdaQueryWrapper<Ware> queryWrapper = new LambdaQueryWrapper<>();
        String name = wareQueryVo.getName();
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like(Ware::getName,name);
        }
        IPage reult=  baseMapper.selectPage(param,queryWrapper);
        return reult;
    }
}
