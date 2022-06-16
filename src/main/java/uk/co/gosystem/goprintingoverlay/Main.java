package uk.co.gosystem.goprintingoverlay;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.print.attribute.PrintRequestAttributeSet;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final int DOTS_PER_INCH = 600;

    private static final URL overlay = Main.class.getResource("/overlay.png");
    private static final URL defaultImage = Main.class.getResource("/shrey.jpg");

    public static void print(Printable page) throws PrinterException {
        // Get the set of attributes that we want from our printout
        PrintRequestAttributeSet printRequestAttributeSet = PhotoPaperAttributeSet.getValidPrintSize(DOTS_PER_INCH);

        // Get a printer job
        PrinterJob pj = PrinterJob.getPrinterJob();

        // Get the closest page format that our printer can afford to give us
        PageFormat pageFormat = pj.getPageFormat(printRequestAttributeSet);

        // Set the size of the paper to a 4x6 inch photo card.
        pageFormat.setPaper(new PhotoPaperSizePaper());

        // Print print our printout!
        pj.setPrintable(page, pageFormat);

        // Sanity check our page size, by checking what the format is now.
        var format = pj.validatePage(pageFormat);
        logger.debug(
                "Attempting to print onto an {}x{} area for {}x{} paper",
                format.getImageableWidth() / 72,
                format.getImageableHeight() / 72,
                format.getPaper().getWidth() / 72,
                format.getPaper().getHeight() / 72
        );

        // If the user accepts the print dialogue, print out the page.
        if (pj.printDialog()) pj.print();
    }

    public static void main(String[] args) throws IOException, PrinterException {
        logger.info("Welcome to goPrintingOverlay!");
        logger.debug("Program started with {} arguments:", args.length);

        for (String arg : args) {
            logger.debug("Argument: {}", arg);
        }

        GoSystemAtRoyalHackawayPhotoBoothPhotoPaperBorderOverlayPrintoutPrintable page;

        if (args.length > 0) {
            logger.info("Picture found - {}", args[0]);
            page = new GoSystemAtRoyalHackawayPhotoBoothPhotoPaperBorderOverlayPrintoutPrintable(overlay, new File(args[0]), DOTS_PER_INCH);
        } else {
            logger.warn("An image argument was not passed - defaulting to a picture of Shrey.");
            page = new GoSystemAtRoyalHackawayPhotoBoothPhotoPaperBorderOverlayPrintoutPrintable(overlay, defaultImage, DOTS_PER_INCH);
        }

        print(page);
    }
}