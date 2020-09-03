package com.bringbackdada.site.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class ImageServiceImpl implements ImageService {

    private final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Override
    public void saveImageFile(Long contentId, MultipartFile file) {

        // TODO implement
        logger.debug("Image file saved");
    }
}
