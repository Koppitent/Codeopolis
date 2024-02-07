package de.koppy.domainmodel.Plants;

public class Gerste extends Getreide {


    public Gerste() {
        super(4, true, 0.25f, 0.2f, 0.2f);
    }

    @Override
    public void pestInfestation(Conditions c) {
        if(c.isBarelyGoutFly()) {
            decreaseHarvest(0.4f);
        }
    }

    @Override
    public void diseaseOutbreak(Conditions c) {
        if(c.isFusarium()) {
            decreaseHarvest(0.25f);
        }
    }
}
