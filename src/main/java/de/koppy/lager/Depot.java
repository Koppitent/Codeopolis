package de.koppy.lager;

public class Depot {

    private Harvest[] stock; //* IDEA: use java.util.list instead!
    private int capacity;
    private int MAX_CAPACITY = 3;
    private float EXPAND_COST_PERCENT = 0.05f;

    public Depot(int capacity) {
        this.capacity = capacity;
        stock = new Harvest[capacity];
    }

    public int getFillLevel() {
        int filllevel = 0;
        for (Harvest harvest : stock) {
            filllevel = filllevel + harvest.getAmount();
        }
        return filllevel;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean store(Harvest harvest) {
        for(int i=0; i<stock.length; i++) {
            if(stock[i] != null) {
                stock[i] = harvest;
                return true;
            }
        }
        return false;
    }

    /*
     * remove first possible
     * return amount übrig (nicht takeout)
     */
    public int takeOut(int amount) {
        if(amount > getFillLevel()) return amount;
        for(Harvest harvest : stock) {
            if(harvest.getAmount() <= amount) {
                //* Less or equal amount in Harvest than amount to remove
                amount = amount - harvest.getAmount();
                harvest.remove(harvest.getAmount());
            }else {
                //* More in Harvest than amount to remove
                harvest.remove(amount);
                amount = 0;
                break;
            }
        }
        return amount;
    }

    public void expand(int capacity) {
        for(int i=0; i<capacity; i++) {
            expandOne();
        }
    }

    private void expandOne() {
        int toremove = (int) ((float) getFillLevel() * EXPAND_COST_PERCENT);
        takeOut(toremove);
        capacity++;
        //* create and set new stock
        Harvest[] newstock = new Harvest[capacity];
        for(int i=0; i<stock.length; i++) {
            newstock[i] = stock[i];
        }
        this.stock = newstock;
    }

    public void setEXPAND_COST_PERCENT(float EXPAND_COST_PERCENT) {
        this.EXPAND_COST_PERCENT = EXPAND_COST_PERCENT;
    }

    public int decay() {
        int totaldecay=0;
        for(Harvest harvest : stock) {
            totaldecay = totaldecay + harvest.decay();
        }
        return totaldecay;
    }

    @Override
    public String toString() {
        return "The Depot contains" +
                ", " + stock.length + "Harvests" +
                ", can hold " + capacity + " Harvests" +
                ", holds a total of " + getFillLevel();
    }
}
