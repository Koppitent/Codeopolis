package de.koppy;

import java.util.Random;

public class City {

    private static final int BUSHLES_PER_PERSON = 20;
    private static final int ERNTEFAKTOR = 6;
    private static final int R = 25;
    final private String name;
    private int bushles;
    private int acres;
    private int population;
    private int sizefarmland;
    private int year;
    private int priceperacre;
    private int bushlesfeedingthisyear;

    public City(String name) {
        this.name = name;
        this.bushles = 2800;
        this.acres = 1000;
        this.population = 100;
        this.year = 0;
        generateNewPriceperacre();
    }

    public TurnResult runTurn() { //* so gemeint als 'geben sie als Instanz der Klasse zurück'?

        //* Get Info
        int residents = this.population;
        int bushles = this.bushles;
        int year = this.year;

        int starved = calcPeopleStarved();
        residents = residents - starved;

        int starvedPercentage = ((int) ((((double) starved) / ((double) residents)) * 100D));
        int newresidents = calcNewResidents(starvedPercentage, residents); //* newresidents VOR oder NACH starved? (currently DANACH)
        residents = residents + newresidents;

        int bushelsHarvested = calcErnte(bushles);
        bushles = bushles + bushelsHarvested;

        int ateByRats = calcRats(bushles); //* Rats mit wert bushles VOR oder NACH Ernte? (currently DANACH)
        bushles = bushles - ateByRats;

        year++;
        TurnResult tr = new TurnResult(name, year, newresidents, bushelsHarvested, residents, bushles, starved, acres, ateByRats, starvedPercentage);

        //* Set Result
        this.bushles = tr.getBushels();
        this.year = tr.getYear();
        this.population = tr.getResidents();

        this.bushlesfeedingthisyear = 0;
        this.sizefarmland = 0;
        generateNewPriceperacre();

        return tr;
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
        if(this.bushles < (amount * priceperacre)) return false;
        if(amount < 0) return false;
        this.bushles = this.bushles - (amount * priceperacre);
        this.acres = this.acres + amount;
        return true;
    }

    public boolean verkaufen(int amount) {
        if(this.acres < amount) return false;
        if(amount < 0) return false;
        this.acres = this.acres - amount;
        this.bushles = this.bushles + (amount * priceperacre);
        return true;
    }

    public boolean ernähren(int bushlesfeeding) {
        if(bushlesfeeding < 0) return false;
        if(bushlesfeeding > bushles) return false;
        this.bushlesfeedingthisyear = this.bushlesfeedingthisyear + bushlesfeeding;
        this.bushles = this.bushles - bushlesfeeding;
        return true;
    }

    public boolean pflanzen(int acrestoplant) {
        if(acrestoplant < 0) return false;
        if(acrestoplant > this.bushles) return false;
        if(acrestoplant > population/10) return false;
        this.sizefarmland = acrestoplant;
        this.bushles = this.bushles - acrestoplant;
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
        return bushles;
    }

    public int getPopulation() {
        return population;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if(year <= this.year) return;
        this.year = year;
    }

    private void generateNewPriceperacre() {
        Random rndm = new Random();
        this.priceperacre = rndm.nextInt(50);
    }

    public void setBushles(int bushles) {
        this.bushles = bushles;
    }

    @Override
    public String toString() {
        return "After " + year + " years, the town '" + name + "' inhabits " + population + " people, owns " + acres + " acre, " + bushles + " bushles and plants " + sizefarmland + "farmlands.";
    }

    public void setPriceperacre(int priceperacre) {
        this.priceperacre = priceperacre;
    }

    public int getPriceperacre() {
        return priceperacre;
    }

    public String getStatus() {
        return acres + " acres of land, " + bushles + " bushles of grain, " + population + " residents.";
    }

}
