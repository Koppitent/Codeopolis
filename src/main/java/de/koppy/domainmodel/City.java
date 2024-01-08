package de.koppy.domainmodel;

import de.koppy.lager.Depot;
import de.koppy.lager.Harvest;

import java.util.Random;

public class City {

    private int BUSHLES_PER_PERSON = 20;
    private int BUSHLES_PER_ACRE = 1;
    private float ERNTEFAKTOR = 6;
    private int acreperresident=1;
    private int maxPricePerAcre = 30;
    private int minPricePerAcre = 10;
    private int R = 25;
    final private String name;
    private Depot depot;
    private int startcapacity = 3;
    private int acres;
    private int population;
    private int sizefarmland;
    private int year;
    private int priceperacre;
    private int initBushles = 2800;
    private int bushlesfeedingthisyear;
    private int maxyear = 10;
    private Harvest harvestThisYear; //* set everything in here and put into depot at the end of the Turn just an IDEA

    public City(String name) {
        this.name = name;
        this.depot = new Depot(startcapacity);
        this.depot.store(new Harvest(initBushles, 0));
        this.acres = 1000;
        this.population = 100;
        this.year = 0;
        generateNewPriceperacre();
    }

    public void reloadCity(float EXPAND_COST_PERCENT, int MAX_CAPACITY) {
        this.depot = new Depot(startcapacity);
        this.depot.store(new Harvest(initBushles, 0));
        this.acres = 1000;
        this.population = 100;
        this.year = 0;
        generateNewPriceperacre();

        this.depot.setEXPAND_COST_PERCENT(EXPAND_COST_PERCENT);
        this.depot.setMAX_CAPACITY(MAX_CAPACITY);
    }

    public TurnResult runTurn() { //* so gemeint als 'geben sie als Instanz der Klasse zurück'?

        //* Get Info
        int residents = this.population;
        int year = this.year;
        int newharvest = 0;

        int starved = calcPeopleStarved();
        residents = residents - starved;

        int starvedPercentage = ((int) ((((double) starved) / ((double) residents)) * 100D));
        int newresidents = calcNewResidents(starvedPercentage, residents); //* newresidents VOR oder NACH starved? (currently DANACH)
        residents = residents + newresidents;

        int bushelsHarvested = calcErnte(depot.getFillLevel());
        newharvest = newharvest + bushelsHarvested;

        int ateByRats = calcRats(depot.getFillLevel()); //* Rats mit wert bushles VOR oder NACH Ernte? (currently DANACH)
        depot.takeOut(ateByRats);

        int decayed = depot.decay();

        year++;
        TurnResult tr = new TurnResult(name, year, newresidents, bushelsHarvested, residents, depot.getFillLevel(), starved, acres, ateByRats, starvedPercentage, decayed);

        //* Set Result
        boolean enoughspace = this.depot.store(new Harvest(newharvest, 0));
        this.year = tr.getYear();
        this.population = tr.getResidents();

        this.bushlesfeedingthisyear = 0;
        this.sizefarmland = 0;
        generateNewPriceperacre();

        return tr;
    }

    public boolean expandDepot(int capacity) {
        if(depot.getFillLevel() <= 0) return false;
        this.depot.expand(capacity);
        return true;
    }

    private int calcRats(int currentbushles) {
        int percentate = new Random().nextInt(R+1);
        int ateByRats = (int) ((float) currentbushles * ((float) percentate / 100F)); //* schneide rest weg?
        return ateByRats;
    }

    private int calcErnte(int currentbushles) {
        Random rndm = new Random();
        float z = ((float) (rndm.nextInt(90)+11) / 100F);
        float ernterate = (float) z * ERNTEFAKTOR;
        int erntemenge = (int) (ernterate * (float) sizefarmland);
        if(erntemenge > (Integer.MAX_VALUE-currentbushles)) return (Integer.MAX_VALUE-currentbushles);
        return erntemenge; //* schneide rest weg?
    }

    private int calcNewResidents(int starvedPercentage, int residents) {
        int newresidents = 0;
        if(starvedPercentage >= 40) return newresidents;
        int newpercentpeople = new Random().nextInt(41);
        newresidents = (int) ((double) residents *  ((double) newpercentpeople / 100D)); //* schneide rest weg?
        if(newresidents > (Integer.MAX_VALUE - residents)) newresidents = (Integer.MAX_VALUE - residents);
        return newresidents;
    }

    private int calcPeopleStarved() {
        int peopleabletofeed = this.bushlesfeedingthisyear / BUSHLES_PER_PERSON;
        int peoplestarved = 0;
        if(this.population > peopleabletofeed) {
            peoplestarved = this.population - peopleabletofeed;
        }
        return peoplestarved;
    }

    /* price = how many bushles per acre
     *
     * kauf/verkauf bezieht sich auf acres!
     *
     */
    public boolean kaufen(int amount) {
        if(depot.getFillLevel() < (amount * priceperacre)) return false;
        if(amount < 0) return false;
        depot.takeOut(amount * priceperacre);
        this.acres = this.acres + amount;
        return true;
    }

    public boolean verkaufen(int amount) {
        if(this.acres < amount) return false;
        if(amount < 0) return false;
        boolean canstore = depot.store(new Harvest(amount * priceperacre, 0));
        if(!canstore) return false;
        this.acres = this.acres - amount;
        return true;
    }

    public boolean ernähren(int bushlesfeeding) {
        if(bushlesfeeding < 0) return false;
        if(bushlesfeeding > depot.getFillLevel()) return false;
        this.bushlesfeedingthisyear = this.bushlesfeedingthisyear + bushlesfeeding;
        depot.takeOut(bushlesfeeding);
        return true;
    }

    public boolean pflanzen(int acrestoplant) {
        if(acrestoplant < 0) return false;
        if(acrestoplant > depot.getFillLevel()*BUSHLES_PER_ACRE) return false;
        if(acrestoplant > population/acreperresident) return false;
        this.sizefarmland = acrestoplant;
        depot.takeOut(acrestoplant);
        return true;
    }

    //* getter & setter
    public String getName() {
        return name;
    }

    public int getAcres() {
        return acres;
    }

    public int getBushles() {
        return depot.getFillLevel();
    }

    public int getPopulation() {
        return population;
    }

    public int getYear() {
        return year;
    }

    public void setAcres(int acres) {
        this.acres = acres;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setBUSHLES_PER_ACRE(int BUSHLES_PER_ACRE) {
        this.BUSHLES_PER_ACRE = BUSHLES_PER_ACRE;
    }

    public void setBUSHLES_PER_PERSON(int BUSHLES_PER_PERSON) {
        this.BUSHLES_PER_PERSON = BUSHLES_PER_PERSON;
    }

    public void setERNTEFAKTOR(float ERNTEFAKTOR) {
        this.ERNTEFAKTOR = ERNTEFAKTOR;
    }

    public void setR(int r) {
        R = r;
    }

    public void setStartcapacity(int startcapacity) {
        this.startcapacity = startcapacity;
    }

    public void setAcreperresident(int acreperresident) {
        this.acreperresident = acreperresident;
    }

    public void setInitBushles(int initBushles) {
        this.initBushles = initBushles;
    }

    public void setSizefarmland(int sizefarmland) {
        this.sizefarmland = sizefarmland;
    }

    public void setBushlesfeedingthisyear(int bushlesfeedingthisyear) {
        this.bushlesfeedingthisyear = bushlesfeedingthisyear;
    }

    public void setYear(int year) {
        if(year <= this.year) return;
        this.year = year;
    }

    private void generateNewPriceperacre() {
        Random rndm = new Random();
        this.priceperacre = minPricePerAcre + rndm.nextInt(maxPricePerAcre-minPricePerAcre);
    }

    public void setMaxyear(int maxyear) {
        this.maxyear = maxyear;
    }

    @Override
    public String toString() {
        return "After " + year + " years, the town '" + name + "' inhabits " + population + " people, owns " + acres + " acre, " + depot.getFillLevel() + " bushles and plants " + sizefarmland + "farmlands.";
    }

    public void setPriceperacre(int priceperacre) {
        this.priceperacre = priceperacre;
    }

    public void setMaxPricePerAcre(int maxPricePerAcre) {
        this.maxPricePerAcre = maxPricePerAcre;
    }

    public void setMinPricePerAcre(int minPricePerAcre) {
        this.minPricePerAcre = minPricePerAcre;
    }

    public int getPriceperacre() {
        return priceperacre;
    }

    public String getStatus() {
        return acres + " acres of land, " + depot.getFillLevel() + " bushles of grain, " + population + " residents.";
    }

}