package de.koppy.domainmodel.Plants;

public class Roggen extends Getreide {

    public Roggen() {
        super(2, true, 0.45f, 0.1f, 0.05f);
    }

    @Override
    public void pestInfestation(Conditions c) { /* Nothings happening on Infestation */ }

    @Override
    public void diseaseOutbreak(Conditions c) {
        if(c.isPowderyMildew()) {
            if(c.getAvergeTemperatureWinter() - IDEAL_WINTER_TEMP < 3) {
                decreaseHarvest(0.1f);
            }else {
                decreaseHarvest(0.15f);
            }
        }
    }
}
