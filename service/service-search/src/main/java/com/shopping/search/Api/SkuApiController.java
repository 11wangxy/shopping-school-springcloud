package com.shopping.search.Api;

import com.shopping.model.search.SkuEs;
import com.shopping.search.service.SkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-23 12:14
 * @description: shopping-parent
 */
@CrossOrigin
@RestController
@RequestMapping("/api/search/sku")
@Api(tags = "远程调用ES")
public class SkuApiController {

    @Resource
    private SkuService skuService;
    @ApiOperation("查询爆款商品")
    @GetMapping("/inner/findHotSkuList")
    public List<SkuEs> findHotSkuList(){
        List<SkuEs> list=skuService.findSkuHotList();
        return list;
    }
}
