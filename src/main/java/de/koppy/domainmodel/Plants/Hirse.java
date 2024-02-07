package de.koppy.domainmodel.Plants;

public class Hirse extends Getreide {

    public Hirse() {
        super(2, false, 0.45f, 0.1f, 0.05f);
    }

    @Override
    public void pestInfestation(Conditions c) {
        if(c.isBarelyGoutFly()) {
            if(c.getSoilConditions() > 0.8f) {
                decreaseHarvest(0.1f);
            }else {
                decreaseHarvest(0.15f);
            }
        }
    }

    @Override
    public void diseaseOutbreak(Conditions c) { /* Nothings happening on disease outbreak */}

}
