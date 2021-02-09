package com.bringbackdada.site.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface ImageService {

    void saveImageFile(Long contentId, MultipartFile file);
    byte[] convertToByteArray(MultipartFile file);
    InputStream getImageStream(Long id, int width);
}
