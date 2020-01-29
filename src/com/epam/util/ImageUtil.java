/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

public class ImageUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageUtil.class);
    private static final Dimension BOUNDARY_DIMENSIONS = new Dimension(300, 300);
    private static final String PATH_TO_IMAGES = "C:\\images\\";

    public static boolean imageSave(FileItem imageFileItem) {
        File directory = new File("./");
        System.out.println(directory.getAbsolutePath());
        LOGGER.info("TRY TO WRITE FILE");
        String fileName = new File(imageFileItem.getName()).getName();

        BufferedImage image = null;
        try {
            if (ImageIO.read(imageFileItem.getInputStream()) != null) {
                image = ImageIO.read(imageFileItem.getInputStream());
                Dimension scaledDimension = getScaledDimension(new Dimension(image.getWidth(), image.getHeight()), BOUNDARY_DIMENSIONS);
                image = resizeImage(image, image.getType(), scaledDimension.width, scaledDimension.height);
            } else {
                LOGGER.error("THIS FILE ISN'T IMAGE");
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        try {
            return ImageIO.write(image, getExtensionByApacheCommonLib(fileName),
                    new File(PATH_TO_IMAGES + File.separator + fileName));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    public static Image imageRead(String imageLink) {
        return new Image() {
            @Override
            public int getWidth(ImageObserver observer) {
                return 0;
            }

            @Override
            public int getHeight(ImageObserver observer) {
                return 0;
            }

            @Override
            public ImageProducer getSource() {
                return null;
            }

            @Override
            public Graphics getGraphics() {
                return null;
            }

            @Override
            public Object getProperty(String name, ImageObserver observer) {
                return null;
            }
        };
    }

    /**
     * @param filename
     * @return file extension if exists
     */
    private static String getExtensionByApacheCommonLib(String filename) {
        return FilenameUtils.getExtension(filename);
    }

    //https://stackoverflow.com/questions/10245220/java-image-resize-maintain-aspect-ratio/45902544

    public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type, Integer img_width, Integer img_height) {

        BufferedImage resizedImage = new BufferedImage(img_width, img_height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, img_width, img_height, null);
        g.dispose();

        return resizedImage;
    }
}