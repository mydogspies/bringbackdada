package com.bringbackdada.site.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImageFile(Long contentId, MultipartFile file);
}
