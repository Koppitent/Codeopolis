package de.koppy;

public class City {

    final private String name;
    private int bushles;
    private int acres;
    private int population;
    private int sizefarmland;
    private int year;

    public City(String name) {
        this.name = name;
        this.bushles = 2800;
        this.acres = 1000;
        this.population = 100;
        this.year = 0;
    }

    /* price = how many bushles per acre
    *
    * kauf/verkauf bezieht sich auf acres!
    *
    */
    public boolean kaufen(int priceperacre, int amount) {
        if(this.bushles < (amount * priceperacre)) return false;
        if(amount < 0) return false;
        this.bushles = this.bushles - (amount * priceperacre);
        this.acres = this.acres + amount;
        return true;
    }

    public boolean verkaufen(int priceperacre, int amount) {
        if(this.acres < amount) return false;
        if(amount < 0) return false;
        this.acres = this.acres - amount;
        this.bushles = this.bushles + (amount * priceperacre);
        return true;
    }

    public boolean ernähren(int bushlesfeeding) {
        if(bushlesfeeding < 0) return false;
        if(bushlesfeeding > bushles) return false;
        int peopleabletofeed = bushlesfeeding / 20;
        if(peopleabletofeed >= population) {
            //* feeding ALL people possible
            this.bushles = this.bushles - bushlesfeeding;
            return true;
        }else {
            //* more people in town than able to feed
            this.population = peopleabletofeed;
            this.bushles = this.bushles - bushlesfeeding;
            return true;
        }
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

    public void setBushles(int bushles) {
        this.bushles = bushles;
    }

    @Override
    public String toString() {
        return "After " + year + " years, the town '" + name + "' inhabits " + population + " people, owns " + acres + " acre, " + bushles + " bushles and plants " + sizefarmland + "farmlands.";
    }
}
