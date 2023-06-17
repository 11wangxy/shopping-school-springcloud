package com.shopping.product.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-17 21:56
 * @description: shopping-parent
 */
public interface FileUploadService {
    String fileUpload(MultipartFile file) throws IOException;
}
