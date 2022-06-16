package uk.co.gosystem.goprintingoverlay;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.image.BufferedImage;

public class Cover {
    private final static Logger logger = LogManager.getLogger(Cover.class);

    public static BufferedImage resize(BufferedImage input, int targetWidth, int targetHeight) {
        int offsetX;
        int offsetY;
        int outputWidth;
        int outputHeight;

        // Calculate the aspect ratios of the target/image
        double targetAspectRatio = (double) targetWidth / targetHeight;
        double imageAspectRatio = (double) input.getWidth() / input.getHeight();

        if (targetAspectRatio > imageAspectRatio) {
            // If the target is wider than the image...
            // 1. Pin the output width to the target width
            // 2. Make the output height TALLER than the target height
            // 3. Move the image up to centre the viewport on the image.
            outputWidth = targetWidth;
            outputHeight = (int) ((float) targetWidth / imageAspectRatio);
            offsetX = 0;
            offsetY = (outputHeight - targetHeight) / 2;
        } else {
            // If the target is taller than the image...
            // 1. Pin the output height to the target height
            // 2. Make the output width WIDER than the target width
            // 3. Move the image left to centre the viewport on the image.
            outputWidth = (int) ((float) targetHeight * imageAspectRatio);
            outputHeight = targetHeight;
            offsetX = (outputWidth - targetHeight) / 2;
            offsetY = 0;
        }

        logger.debug("Resizing {}x{} image to {}x{}", input.getWidth(), input.getHeight(), targetWidth, targetHeight);
        logger.debug("New image size {}x{} with offset ({},{})", outputWidth, outputHeight, offsetX, offsetY);

        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);

        outputImage.getGraphics().drawImage(input, -offsetX, -offsetY, outputWidth, outputHeight, null);

        return outputImage;
    }
}
