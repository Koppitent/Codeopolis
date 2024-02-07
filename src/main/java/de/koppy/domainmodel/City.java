package de.koppy.domainmodel;

import de.koppy.domainmodel.Plants.*;
import de.koppy.lager.Depot;
import de.koppy.lager.Harvest;

import java.util.Random;

public class City implements CityState{

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
    private Weizen weizen = new Weizen();
    private Gerste gerste = new Gerste();
    private Hirse hirse = new Hirse();
    private Mais mais = new Mais();
    private Reis reis = new Reis();
    private Roggen roggen = new Roggen();
    private Harvest harvestThisYear; //* set everything in here and put into depot at the end of the Turn just an IDEA

    public City(String name) {
        this.name = name;
        this.depot = new Depot(startcapacity);
        this.depot.store(new Harvest(initBushles));
        this.acres = 1000;
        this.population = 100;
        this.year = 0;
        generateNewPriceperacre();
    }

    public void reloadCity(float EXPAND_COST_PERCENT, int MAX_CAPACITY) {
        this.depot = new Depot(startcapacity);
        this.depot.store(new Harvest(initBushles));
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
        Conditions c = Conditions.generateRandomConitions();

        int starved = calcPeopleStarved();
        residents = residents - starved;

        //* starving stuff
        int starvedPercentage = ((int) ((((double) starved) / ((double) residents)) * 100D));
        int newresidents = calcNewResidents(starvedPercentage, residents); //* newresidents VOR oder NACH starved? (currently DANACH)
        residents = residents + newresidents;

        //* Harvest stuff
        int bushelsHarvested = 0;
        weizen.grow(c);
        weizen.drought(c);
        weizen.diseaseOutbreak(c);
        weizen.pestInfestation(c);
        bushelsHarvested = bushelsHarvested + weizen.harvest();

        gerste.grow(c);
        gerste.drought(c);
        gerste.diseaseOutbreak(c);
        gerste.pestInfestation(c);
        bushelsHarvested = bushelsHarvested + gerste.harvest();

        hirse.grow(c);
        hirse.drought(c);
        hirse.diseaseOutbreak(c);
        hirse.pestInfestation(c);
        bushelsHarvested = bushelsHarvested + hirse.harvest();

        mais.grow(c);
        mais.drought(c);
        mais.diseaseOutbreak(c);
        mais.pestInfestation(c);
        bushelsHarvested = bushelsHarvested + mais.harvest();

        roggen.grow(c);
        roggen.drought(c);
        roggen.diseaseOutbreak(c);
        roggen.pestInfestation(c);
        bushelsHarvested = bushelsHarvested + roggen.harvest();

        reis.grow(c);
        reis.drought(c);
        reis.diseaseOutbreak(c);
        reis.pestInfestation(c);
        bushelsHarvested = bushelsHarvested + reis.harvest();

        newharvest = newharvest + bushelsHarvested;

        //* Depot stuff
        int ateByRats = calcRats(depot.getFillLevel()); //* Rats mit wert bushles VOR oder NACH Ernte? (currently DANACH)
        depot.takeOut(ateByRats);

        int decayed = depot.decay();

        year++;
        TurnResult tr = new TurnResult(name, year, newresidents, bushelsHarvested, residents, depot.getFillLevel(), starved, acres, ateByRats, starvedPercentage, decayed);

        //* Set Result
        boolean enoughspace = this.depot.store(new Harvest(newharvest));
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
        if(sizefarmland > acres-amount) return false;
        if(this.acres < amount) return false;
        if(amount < 0) return false;
        boolean canstore = depot.store(new Harvest(amount * priceperacre));
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

    public boolean pflanzen(int acrestoplant, GetreideSorten getreidesorte) {
        if(sizefarmland+acrestoplant > acres) return false;
        if(acrestoplant > depot.getFillLevel()*BUSHLES_PER_ACRE) return false;
        if(sizefarmland+acrestoplant > population/acreperresident) return false;

        switch (getreidesorte) {
            case WEIZEN:
                weizen.plant(acrestoplant);
                depot.takeOut(acrestoplant);
                break;
            case MAIS:
                mais.plant(acrestoplant);
                depot.takeOut(acrestoplant);
                break;
            case HIRSE:
                hirse.plant(acrestoplant);
                depot.takeOut(acrestoplant);
                break;
            case REIS:
                reis.plant(acrestoplant);
                depot.takeOut(acrestoplant);
                break;
            case GERSTE:
                gerste.plant(acrestoplant);
                depot.takeOut(acrestoplant);
                break;
            case ROGGEN:
                roggen.plant(acrestoplant);
                depot.takeOut(acrestoplant);
                break;
            default:
                break;
        }
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

    public int getSizefarmland() {
        return sizefarmland;
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