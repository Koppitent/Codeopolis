package de.koppy.domainmodel;

import de.koppy.presentation.TextInterface;

public class Game {

    private City city;
    private GameConfig config;
    private TextInterface ti;

    public Game(String cityname, DifficultyLevel difficultyLevel, TextInterface ti) {
        this.city = new City(cityname);
        this.config = new GameConfig(difficultyLevel);
        this.ti = ti;
        applyConfig();
    }

    public TextInterface getTextInterface() {
        return ti;
    }

    public GameConfig getConfig() {
        return config;
    }

    public DifficultyLevel getDifficulty() {
        return this.config.getDifficultyLevel();
    }

    public void nextTurn() {
        TurnResult tr = city.runTurn();
        ti.turnEnd(tr);
    }

    public void applyConfig() {
        this.city.setInitBushles(config.getInitialBushels());
        this.city.setAcres(config.getInitialAcres());
        this.city.setPopulation(config.getInitialResidents());
        this.city.setERNTEFAKTOR(config.getHarvestFactor());
        this.city.setBUSHLES_PER_PERSON(config.getBushelsPerResident());
        this.city.setMaxPricePerAcre(config.getMaxAcrePrice());
        this.city.setMinPricePerAcre(config.getMinArcePrice());
        this.city.setAcreperresident(config.getAcrePerResident());
        this.city.setR(config.getRateInfestation());
        this.city.setMaxyear(config.getNumberOfYears());
        this.city.setBUSHLES_PER_ACRE(config.getBushelsPerAcre());
        this.city.setStartcapacity(config.getInitmaxlager());
        this.city.reloadCity(config.getPercentexpandcost(), config.getInitmaxlager());
    }

    public City getCity() {
        return city;
    }

    public void resetCity(String name) {
        this.city = new City(name);
    }
}