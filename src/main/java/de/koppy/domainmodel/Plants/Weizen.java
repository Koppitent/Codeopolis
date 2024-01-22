package de.koppy.domainmodel.Plants;

import de.koppy.domainmodel.City;

public class Weizen extends Getreide{

    public Weizen(int amount, City city) {
        super(amount, 6, true);
    }

    @Override
    public void pestInfestation(Conditions c) {

    }

    @Override
    public void diseaseOutbreak(Conditions c) {

    }
}