package de.koppy.domainmodel.Plants;

import de.koppy.domainmodel.City;

public class Weizen extends Getreide{

    public Weizen() {
        super(6, true, 0.1f,0.3f, 0.5f);
    }

    @Override
    public void pestInfestation(Conditions c) {
        if(c.isFritFly()) {
            decreaseHarvest(0.25f);
        }
        if(c.isBarelyGoutFly()) {
            decreaseHarvest(0.3f);
        }
    }

    @Override
    public void diseaseOutbreak(Conditions c) {
        if(c.isPowderyMildew()) {
            decreaseHarvest(0.3f);
        }
        if(c.isLeafDrought()) {
            if(c.getAvergeTemperatureWinter() - IDEAL_WINTER_TEMP < 2) {
                decreaseHarvest(0.3f);
            }else {
                decreaseHarvest(0.4f);
            }
        }
    }
}