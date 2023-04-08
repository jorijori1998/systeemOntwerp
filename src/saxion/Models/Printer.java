package saxion.Models;

import java.util.ArrayList;

public abstract class Printer {
    private int id;
    private String name;
    private String manufacturer;
    private boolean isHoused;

    public Printer(int id, String printerName, String manufacturer,boolean isHoused) {
        this.id = id;
        this.name = printerName;
        this.manufacturer = manufacturer;
        this.isHoused = isHoused;
    }

    public int getId() {
        return id;
    }

    public boolean isHoused() {
        return isHoused;
    }

    public abstract int CalculatePrintTime(String filename);

    public abstract Spool[] getCurrentSpools();

    public abstract void setCurrentSpools(ArrayList<Spool> spools);

    public abstract boolean printFits(Print print);

    public abstract boolean checkPrintTaskWithCurrentSpool(PrintTask printTask);

    @Override
    public String toString() {
        return  "<--------" + System.lineSeparator() +
                "- ID: " + id + System.lineSeparator() +
                "- Name: " + name + System.lineSeparator() +
                "- Manufacturer: " + manufacturer + System.lineSeparator() +
                "-------->";
    }

    public String getName(){
        return name;
    }
}
