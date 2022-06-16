package uk.co.gosystem.goprintingoverlay;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoyalHackawayPhotoPaperOverlayPrintable implements Printable {
    private static final Logger logger = LogManager.getLogger(RoyalHackawayPhotoPaperOverlayPrintable.class);
    BufferedImage overlay;
    BufferedImage image;

    int dpi;

    public RoyalHackawayPhotoPaperOverlayPrintable(URL overlay, File image, int dpi) throws IOException {
        this.overlay = ImageIO.read(overlay);
        this.image = ImageIO.read(image);
        this.dpi = dpi;
    }

    public RoyalHackawayPhotoPaperOverlayPrintable(URL overlay, URL image, int dpi) throws IOException {
        this.overlay = ImageIO.read(overlay);
        this.image = ImageIO.read(image);
        this.dpi = dpi;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
        if (pageIndex != 0) return NO_SUCH_PAGE;

        Graphics2D canvas = (Graphics2D) graphics;

        canvas.translate(
                pageFormat.getImageableX(),
                pageFormat.getImageableY()
        );

        double pageWidth = pageFormat.getImageableWidth();
        double pageHeight = pageFormat.getImageableHeight();

        double overlayWidth = this.overlay.getWidth();
        double overlayHeight = this.overlay.getHeight();

        int offsetEdge = this.dpi / 4;

        double scaleX = pageWidth / overlayWidth;
        double scaleY = pageHeight / overlayHeight;

        logger.trace("Creating a page of size: {}x{} for an image of {}x{}", pageWidth / 72, pageHeight / 72, overlayWidth, overlayHeight);

        canvas.scale(scaleX, scaleY);
        canvas.drawImage(this.overlay, 0, 0, this.overlay.getWidth(), this.overlay.getHeight(), null);

        int newWidth = this.overlay.getWidth() - (2*offsetEdge);
        // TODO: Calculate the height programmatically, from a size of 4.5 inches.
        int newHeight = 2700;

        BufferedImage subsection = Cover.resize(this.image, newWidth, newHeight);

        canvas.drawImage(subsection, offsetEdge, offsetEdge, newWidth, newHeight, null);
        return PAGE_EXISTS;
    }
}
