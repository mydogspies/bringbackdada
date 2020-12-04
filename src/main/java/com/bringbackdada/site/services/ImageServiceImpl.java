package com.bringbackdada.site.services;

import com.bringbackdada.site.model.Content;
import ij.ImagePlus;
import ij.io.FileInfo;
import ij.io.ImageReader;
import ij.process.ImageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageServiceImpl implements ImageService {

    private final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final ContentService contentService;

    public ImageServiceImpl(ContentService contentService) {
        this.contentService = contentService;
    }

    @Override
    public InputStream getImageStream(Long id, int width) {

        Content content = contentService.findById(id);
        InputStream is = new ByteArrayInputStream(content.getImageFile());

        if (content.getVisible()) {
            // InputStream is = new ByteArrayInputStream(content.getImageFile());
            InputStream resizedImageStream = null;
            try {
                resizedImageStream = resizedImage(is, width);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return resizedImageStream;
        }
        return null;

    }

    /**
     *
     * @param is
     * @return
     * @throws IOException
     */
    public InputStream resizedImage (InputStream is, int width) throws IOException {

        double sigmaFactor = 0.3;
        int interpolationMethod = ImageProcessor.NONE; // equals 0

        BufferedImage img = ImageIO.read(is);
        ImagePlus imp = new ImagePlus("image", img);

        double scaleFactor = width/(double) imp.getWidth();

        ImageProcessor ip = imp.getProcessor();
        ip.blurGaussian(sigmaFactor / scaleFactor);
        ip.setInterpolationMethod(interpolationMethod);
        ImageProcessor outputProcessor = ip.resize((int)(ip.getWidth() * scaleFactor), (int)(ip.getHeight()*scaleFactor));
        BufferedImage newBuffImage = outputProcessor.getBufferedImage();

        // turns BufferedImage into a stream. Point in this is to share the same array and thus avoid memory issues.
        // https://stackoverflow.com/questions/4251383/how-to-convert-bufferedimage-to-inputstream/21569243
        final ByteArrayOutputStream output = new ByteArrayOutputStream() {
            @Override
            public synchronized byte[] toByteArray() {
                return this.buf;
            }
        };
        ImageIO.write(newBuffImage, "jpeg", output);
        return new ByteArrayInputStream(output.toByteArray(), 0, output.size());
    }

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
