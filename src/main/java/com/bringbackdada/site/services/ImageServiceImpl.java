package com.bringbackdada.site.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Override
    public void saveImageFile(Long contentId, MultipartFile file) {

        // TODO implement
        logger.debug("Image file saved");
    }

    @Override
    public byte[] convertToByteArray(MultipartFile file) {

        byte[] byteArray = new byte[0];
        int i = 0;

        try {
            byteArray = new byte[file.getBytes().length];
        } catch (IOException e) {
            logger.error("The multipart image file is of length zero bytes: " + e.getMessage());
        }

        try {
            for (byte b : file.getBytes()) {
                byteArray[i++] = b;
            }
        } catch (IOException e) {
            logger.error("Can't read the multipart image file: " + e.getMessage());
        }

        return byteArray;
    }
}
