package de.koppy.domainmodel.Plants;

public abstract class Getreide {

    public static float IDEAL_SUMMER_TEMP = 18f;
    public static float IDEAL_WINTER_TEMP = 3.3f;
    private int grundertrag;
    private boolean isWinter;
    private float zulaessigeAbweichung;
    private float minderungAbweichung;
    private float minderungDürre;
    private int amountplanted;
    protected int amountToHarvest;

    public Getreide(int grundertrag, boolean isWinter, float zulaessigeAbweichung, float minderungAbweichung, float minderungDürre) {
        this.grundertrag = grundertrag;
        this.isWinter = isWinter;
        this.zulaessigeAbweichung = zulaessigeAbweichung;
        this.minderungAbweichung = minderungAbweichung;
        this.minderungDürre = minderungDürre;
    }

    public boolean plant(int acres) {
       amountplanted = amountplanted + acres;
       return true;
    }

    public void grow(Conditions c) {
        this.amountToHarvest = amountToHarvest + (amountplanted * grundertrag);
    }

    public void drought(Conditions c) {
        if(c.isDrought()) {
            this.amountToHarvest = (int) ((float) amountToHarvest * (1f - minderungDürre));
        }
        if(isWinter) {
            float avergeTemp = c.getAvergeTemperatureWinter();
            if(avergeTemp < IDEAL_WINTER_TEMP) {
                float differencePercent = 1f - (avergeTemp / IDEAL_WINTER_TEMP);
                if(differencePercent > zulaessigeAbweichung) {
                    drought();
                }
            }
        }else {
            float avergeTemp = c.getAvergeTemperatureSummer();
            if(avergeTemp > IDEAL_SUMMER_TEMP) {
                float differencePercent = (avergeTemp / IDEAL_SUMMER_TEMP) - 1f;
                if(differencePercent > zulaessigeAbweichung) {
                    drought();
                }
            }
        }
    }

    private void drought() {
        this.amountToHarvest = (int) ((float) amountToHarvest * (1f - minderungAbweichung));
    }

    public void decreaseHarvest(float percent) {
        this.amountToHarvest = (int) ((float) amountToHarvest * (1f - percent));
    }

    public int harvest() {
        int amount = amountToHarvest;
        this.amountplanted = 0;
        this.amountToHarvest = 0;
        return amount;
    }

    public abstract void pestInfestation(Conditions c);
    public abstract void diseaseOutbreak(Conditions c);

}