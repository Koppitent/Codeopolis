package de.koppy;

public class GameConfig {

    private int initResidents;
    private int initialAcres;
    private int initialBushles;
    private int maxAcrePrice;
    private int minAcrePrice;
    private int bushlesPerResident;
    private int bushlesPerAcre;
    private int acrePerResident;
    private int numberOfYears;
    private float harvestFactor;
    private int rateInfestation;
    private DifficultyLevel difficultyLevel;

    public GameConfig(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        this.initResidents = 100;
        this.initialBushles = 2800;
        this.maxAcrePrice = 35;
        this.minAcrePrice = 15;
        this.bushlesPerAcre = 1;
        this.harvestFactor = 10;
        this.numberOfYears = 10;
        switch (difficultyLevel) {
            case EASY:
                this.harvestFactor = 6.0f;
                this.initialAcres = 1000;
                this.maxAcrePrice = 30;
                this.minAcrePrice = 10;
                this.bushlesPerResident = 20;
                break;
            case MEDIUM:
                this.harvestFactor = 5.0f;
                this.initialAcres = 900;
                this.bushlesPerResident = 22;
                break;
            case HARD:
                this.harvestFactor = 4.0f;
                this.initialAcres = 800;
                this.bushlesPerResident = 24;
                break;
            default:
            break;
        }
    }

    public int getInitResidents() {
        return initResidents;
    }

    public int getInitialAcres() {
        return initialAcres;
    }

    public int getInitianBushles() {
        return initianBushles;
    }

    public int getMaxAcrePrice() {
        return maxAcrePrice;
    }

    public int getMinAcrePrice() {
        return minAcrePrice;
    }

    public int getBushlesPerResident() {
        return bushlesPerResident;
    }

    public int getBushlesPerAcre() {
        return bushlesPerAcre;
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

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }
}
