package de.koppy.domainmodel.Plants;

public class Reis extends Getreide {

    public Reis() {
        super(6, false, 0.1f, 0.3f, 0.5f);
    }

    @Override
    public void pestInfestation(Conditions c) {
        if(c.isDeliaFly()) {
            float differencePercent = (c.getAvergeTemperatureSummer() / IDEAL_SUMMER_TEMP) - 1f;
            if(differencePercent <= 0.1f) {
                decreaseHarvest(0.3f);
            }else {
                decreaseHarvest(0.4f);
            }
        }
        if(c.isBarelyGoutFly()) {
            decreaseHarvest(0.3f);
        }
    }

    @Override
    public void diseaseOutbreak(Conditions c) {
        if(c.isLeafDrought()) {
            decreaseHarvest(0.3f);
        }
        if(c.isFusarium()) {
            decreaseHarvest(0.25f);
        }
    }
}
