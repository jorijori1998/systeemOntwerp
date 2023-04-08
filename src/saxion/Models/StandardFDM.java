package saxion.Models;

import java.util.ArrayList;

/* Standard cartesian FDM printer */
public class StandardFDM extends Printer {
    private final int maxX;
    private final int maxY;
    private final int maxZ;
    protected Spool[] spools;

    public StandardFDM(int id, String printerName, String manufacturer,boolean isHoused, int maxX, int maxY, int maxZ) {
        super(id, printerName, manufacturer, isHoused);
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;

    }

    //todo waarom arraylist accepteren, dit is alleen logisch bij een multicolor printer met meerdere spools
    // todo moet wel geimplementeerd worden als we een interface gaan gebruiken
    public void setCurrentSpools(ArrayList<Spool> spools) {
        this.spools[0] = spools.get(0);
    }

    public void setCurrentSpool(Spool spool) {
        this.spools[0] = spool;
    }


    public Spool[] getCurrentSpools() {
        return spools;
    }


    @Override
    public boolean printFits(Print print) {
        return print.getHeight() <= maxZ && print.getWidth() <= maxX && print.getLength() <= maxY;
    }

    @Override
    public boolean checkPrintTaskWithCurrentSpool(PrintTask printTask) {
        // check if print fits
        if(!printFits(printTask.getPrint())){
            return false;
        }
        // check for only 1 color
        if(printTask.getColors().size() == 1){
            // een housed printer kan alles printen, maar een StandardFDM zonder house alles behalve ABS
            if(printTask.getFilamentType() != FilamentType.ABS ||isHoused()){
                if(spools[0].spoolMatch(printTask.getColors().get(0),printTask.getFilamentType())){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int CalculatePrintTime(String filename) {
        return 0;
    }

    @Override
    public String toString() {
        String result = super.toString();
        String append = "- maxX: " + maxX + System.lineSeparator() +
                "- maxY: " + maxY + System.lineSeparator() +
                "- maxZ: " + maxZ + System.lineSeparator();
        for (int i = 0;i<this.spools.length;i++){
            append += "- Spool(s): " + spools[0].getId()+ System.lineSeparator();

        }
        append += "-------->";
        result = result.replace("-------->", append);
        return result;
    }
}
