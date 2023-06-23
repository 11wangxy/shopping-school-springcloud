package com.shopping.product.controller;

import com.shopping.common.result.Result;
import com.shopping.product.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-17 21:52
 * @description: shopping-parent
 */
@Api(tags = "上传文件")

@RestController
@RequestMapping("admin/product")
public class FileUploadController {
    @Resource
    private FileUploadService fileUploadService;

    //文件上传
    @ApiOperation("文件上传")
    @PostMapping("fileUpload")
    public Result fileUpload(MultipartFile file) throws Exception{
        return Result.ok(fileUploadService.fileUpload(file));
    }
}
