package uk.co.gosystem.goprintingoverlay;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;

public class PhotoPaperAttributeSet {
    public static PrintRequestAttributeSet getValidPrintSize(int DOTS_PER_INCH) {
        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

        attributeSet.add(PrintQuality.HIGH);
        attributeSet.add(OrientationRequested.PORTRAIT);
        attributeSet.add(MediaTray.MANUAL);
        attributeSet.add(new PrinterResolution(DOTS_PER_INCH, DOTS_PER_INCH, PrinterResolution.DPI));
        attributeSet.add(new MediaPrintableArea(25, 25, 350, 550, MediaPrintableArea.INCH / 100));

        return attributeSet;
    }
}
