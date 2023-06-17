package com.shopping.product.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shopping.model.product.AttrGroup;
import com.shopping.product.mapper.AttrGroupMapper;
import com.shopping.product.service.AttrGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.vo.product.AttrGroupQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 属性分组 服务实现类
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-17
 */
@Service
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroup> implements AttrGroupService {

    @Override
    public IPage pages(Long page, Long limit, AttrGroupQueryVo attrGroupQueryVo) {
        LambdaQueryWrapper<AttrGroup> queryWrapper = new LambdaQueryWrapper<>();
        Page<AttrGroup> param = new Page<>(page, limit);
        String name = attrGroupQueryVo.getName();
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like(AttrGroup::getName,name);
        }
        IPage result = baseMapper.selectPage(param,queryWrapper);
        return result;
    }

    @Override
    public List findAllList() {
        QueryWrapper<AttrGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        List<AttrGroup> list = baseMapper.selectList(queryWrapper);
        return list;
    }
}
