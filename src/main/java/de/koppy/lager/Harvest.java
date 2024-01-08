package de.koppy.lager;

public class Harvest {
    private int amount;
    private int year;

    public Harvest(int amount, int year) {
        this.amount = amount;
        this.year = year;
    }

    /*
     * return new value
     *
     * if it fails: return -1
     */
    public int remove(int amount) {
        if(amount > this.amount) return -1;
        this.amount = this.amount - amount;
        return this.amount;
    }

    public int decay() {
        year++;
        if(year < 2) return 0;
        float basepercent = 0.02f;
        int multiplier = 2 * (year - 2);
        if(multiplier == 0) multiplier = 1; //special case year == 2
        basepercent = basepercent * (float) multiplier;
        int decayamount = (int) ((float) this.amount * basepercent); //maybe round up/down for better measurement!
        this.amount = this.amount - decayamount; // actually decay the amount
        return decayamount;
    }

    public int getAmount() {
        return amount;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        //TODO
        return "Harvest" +
                ", is in year " + this.year +
                ", contains " + this.amount;
    }
}
