package saxion.Models;

/* Printer capable of printing ABS */
//boolean maken in de class printer standardFDM ipv class
public class HousedPrinter extends StandardFDM {
    public HousedPrinter(int id, String printerName, String manufacturer, int maxX, int maxY, int maxZ) {
        super(id, printerName, manufacturer, maxX, maxY, maxZ);
    }
}
