package saxion.Models;

import java.util.ArrayList;

/* Printer capable of printing multiple colors. */
public class MultiColor extends StandardFDM {
    private int maxColors;
//    private Spool spool2;
//    private Spool spool3;
//    private Spool spool4;

    public MultiColor(int id, String printerName, String manufacturer,boolean isHoused, int maxX, int maxY, int maxZ, int maxColors) {
        super(id, printerName, manufacturer, isHoused, maxX, maxY, maxZ);
        this.maxColors = maxColors;
    }

    //todo check als arraylist niet leeg is
    public void setCurrentSpools(ArrayList<Spool> spools) {
        setCurrentSpool(spools.get(0));
        for(int i = 0;spools.size()>i;i++){
            this.spools[i] = (Spool) spools.toArray()[i];
        }
    }

    @Override
    public Spool[] getCurrentSpools() {
        return spools;
    }

    @Override
    public boolean checkPrintTaskWithCurrentSpool(PrintTask printTask) {
        // check if print fits
        if(!printFits(printTask.getPrint())){
            return false;
        }
        if(printTask.getColors().size() <= maxColors){
            for(String color:printTask.getColors()){
                boolean colorWorks = false;
                for(Spool spool:spools){
                    if (spool.spoolMatch(color,printTask.getFilamentType())){
                        colorWorks = true;
                    }
                }
                if(colorWorks == false){
                    return false;
                }
            }
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
    public String toString() {
        String result = super.toString();
        String[] resultArray = result.split("- ");
        String spools = resultArray[resultArray.length-1];

        spools = spools.replace("-------->", "- maxColors: " + maxColors + System.lineSeparator() +
               "-------->");
        resultArray[resultArray.length-1] = spools;
        result = String.join("- ", resultArray);

        return result;
    }

    public int getMaxColors() {
        return maxColors;
    }
}
