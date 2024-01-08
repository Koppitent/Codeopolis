package de.koppy.domainmodel;

public class GameConfig {

    private DifficultyLevel difficultyLevel;
    private int initialResidents;
    private int initialAcres;
    private int initialBushels;
    private int maxAcrePrice;
    private int minArcePrice;
    private int bushelsPerResident;
    private int bushelsPerAcre;
    private int acrePerResident;
    private int numberOfYears;
    private int initmaxlager;
    private float percentexpandcost;
    private float harvestFactor;
    private int rateInfestation;

    public GameConfig(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        this.initialResidents = 100;
        this.initialAcres = 1000;
        this.initialBushels = 2800;
        this.maxAcrePrice = 30;
        this.minArcePrice = 10;
        this.bushelsPerResident = 20;
        this.bushelsPerAcre = 1;
        this.acrePerResident = 10;
        this.numberOfYears = 10;
        this.harvestFactor = 6.f;
        this.rateInfestation = 10;
        this.percentexpandcost = 0.05f;
        this.initmaxlager = 3;
        switch (difficultyLevel) {
            case MEDIUM:
                this.initialAcres = 900;
                this.maxAcrePrice = 35;
                this.minArcePrice = 15;
                this.bushelsPerResident = 22;
                this.harvestFactor = 5.f;
                this.rateInfestation = 20;
                break;
            case HARD:
                this.initialAcres = 800;
                this.maxAcrePrice = 35;
                this.minArcePrice = 15;
                this.bushelsPerResident = 24;
                this.harvestFactor = 4.f;
                this.rateInfestation = 25;
                break;
        }
    }

    public int getInitmaxlager() {
        return initmaxlager;
    }

    public float getPercentexpandcost() {
        return percentexpandcost;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public int getInitialResidents() {
        return initialResidents;
    }

    public int getInitialAcres() {
        return initialAcres;
    }

    public int getInitialBushels() {
        return initialBushels;
    }

    public int getMaxAcrePrice() {
        return maxAcrePrice;
    }

    public int getMinArcePrice() {
        return minArcePrice;
    }

    public int getBushelsPerResident() {
        return bushelsPerResident;
    }

    public int getBushelsPerAcre() {
        return bushelsPerAcre;
    }

    public int getAcrePerResident() {
        return acrePerResident;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public float getHarvestFactor() {
        return harvestFactor;
    }

    public int getRateInfestation() {
        return rateInfestation;
    }
}
