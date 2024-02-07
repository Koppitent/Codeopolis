package de.koppy.domainmodel.Plants;

public class Mais extends Getreide {

    public Mais() {
        super(4, false, 0.25f, 0.2f, 0.3f);
    }

    @Override
    public void pestInfestation(Conditions c) {
        if(c.isFritFly()) {
            decreaseHarvest(0.4f);
        }
    }

    @Override
    public void diseaseOutbreak(Conditions c) {
        if(c.isPowderyMildew()) {
            decreaseHarvest(0.25f);
        }
    }
}
