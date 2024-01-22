package de.koppy.domainmodel.Plants;

import de.koppy.domainmodel.City;
import de.koppy.domainmodel.CityState;

public abstract class Getreide {

    private static int GRUNDERTRAG;
    private static boolean isWinter;
    private int amountplanted;

    public Getreide(int GRUNDERTRAG, boolean isWinter) {
        this.GRUNDERTRAG = GRUNDERTRAG;
        this.isWinter = isWinter;
    }

    public boolean plant(int acres) {
       amountplanted = amountplanted + acres;
       return true;
    }

    public void grow(Conditions c) {

    }

    public void drought(Conditions c) {

    }

    public int harvest() {
        return 0;
    }

    public abstract void pestInfestation(Conditions c);
    public abstract void diseaseOutbreak(Conditions c);

}