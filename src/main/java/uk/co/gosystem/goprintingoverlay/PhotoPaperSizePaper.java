package uk.co.gosystem.goprintingoverlay;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.print.Paper;

public class PhotoPaperSizePaper extends Paper {
    private static final Logger logger = LogManager.getLogger(PhotoPaperSizePaper.class);

    private static final int WIDTH_IN_INCHES = 4;
    private static final int HEIGHT_IN_INCHES = 6;
    private static final int POINT_SIZE = 72;

    /**
     * Create a new Photo Paper sized Paper, of 4x6-inches (portrait).
     */
    public PhotoPaperSizePaper() {
        logger.trace("Creating a paper of size {}x{} inches.", WIDTH_IN_INCHES, HEIGHT_IN_INCHES);

        this.setSize(WIDTH_IN_INCHES * POINT_SIZE, HEIGHT_IN_INCHES * POINT_SIZE);
    }
}
