package de.koppy.domainmodel.Plants;

import java.util.Random;

public class Conditions {

    private float soilConditions; //* from 0.0 - 1.0
    private float avergeTemperatureSummer;
    private float avergeTemperatureWinter;
    private boolean drought;
    private boolean fusarium;
    private boolean powderyMildew;
    private boolean barelyGoutFly;
    private boolean leafDrought;
    private boolean deliaFly;
    private boolean fritFly;

    public Conditions(float soilConditions, float avergeTemperatureSummer, float avergeTemperatureWinter, boolean drought, boolean fusarium, boolean powderyMildew, boolean barelyGoutFly, boolean leafDrought, boolean deliaFly, boolean fritFly) {
        this.soilConditions = soilConditions;
        this.avergeTemperatureSummer = avergeTemperatureSummer;
        this.avergeTemperatureWinter = avergeTemperatureWinter;
        this.drought = drought;
        this.fusarium = fusarium;
        this.powderyMildew = powderyMildew;
        this.barelyGoutFly = barelyGoutFly;
        this.leafDrought = leafDrought;
        this.deliaFly = deliaFly;
        this.fritFly = fritFly;
    }

    public float getSoilConditions() {
        return soilConditions;
    }

    public float getAvergeTemperatureSummer() {
        return avergeTemperatureSummer;
    }

    public float getAvergeTemperatureWinter() {
        return avergeTemperatureWinter;
    }

    public boolean isDrought() {
        return drought;
    }

    public boolean isFusarium() {
        return fusarium;
    }

    public boolean isPowderyMildew() {
        return powderyMildew;
    }

    public boolean isBarelyGoutFly() {
        return barelyGoutFly;
    }

    public boolean isLeafDrought() {
        return leafDrought;
    }

    public boolean isDeliaFly() {
        return deliaFly;
    }

    public boolean isFritFly() {
        return fritFly;
    }

    public static Conditions generateRandomConitions() {
        Random random = new Random();

        float summertemp = random.nextFloat(31);
        float wintertemp = random.nextFloat(21)-10;

        float soilcondition = (float) Math.random();

        return new Conditions(soilcondition, summertemp, wintertemp, Math.random() <= 0.2D, Math.random() <= 0.2D, Math.random() <= 0.2D,
                Math.random() <= 0.2D, Math.random() <= 0.2D, Math.random() <= 0.2D, Math.random() <= 0.2D);
    }

}