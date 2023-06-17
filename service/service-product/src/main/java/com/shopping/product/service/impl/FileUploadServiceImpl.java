package com.shopping.product.service.impl;

import com.mysql.cj.log.Log;
import com.shopping.product.service.FileUploadService;
import com.shopping.product.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-17 21:56
 * @description: shopping-parent
 */
@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {
    @Resource
    private AliOSSUtils aliOSSUtils;

    @Override
    public String fileUpload(MultipartFile file) throws IOException {
        String upload = aliOSSUtils.upload(file);
        log.info("文件上传路径为{}",upload);
        return upload;
    }
}
