package de.koppy;

public class Game {

    private City city;
    private TextInterface textInterface;
    private String name;

    public Game(String name, TextInterface textInterface) {
        this.name = name;
        this.city = new City(name);
        this.textInterface = textInterface;
    }

    public TextInterface getTextInterface() {
        return textInterface;
    }

    public void resetCity() {
        this.city = new City(name);
    }

    public City getCity() {
        return this.city;
    }

    public void doBuyMenu() {
        boolean valid = false;
        while(!valid) {
            int input = textInterface.promptInt("How many acres would you like to buy? ");
            if (getCity().kaufen(input)) {
                textInterface.printMessage("You bought " + input + " acres.");
                textInterface.printNewStatus(getCity().getStatus());
                valid = true;
            } else {
                textInterface.printErr("Buying failed. Please try again");
            }
        }
    }

    public void doSellMenu() {
        boolean valid = false;
        while(!valid) {
            int input = textInterface.promptInt("How many acres would you like to sell? ");
            if (getCity().verkaufen(input)) {
                textInterface.printMessage("You sold " + input + " acres.");
                textInterface.printNewStatus(getCity().getStatus());
                valid = true;
            } else {
                textInterface.printErr("Selling failed. Please try again!");
            }
        }
    }

    public void doPlant() {
        boolean valid = false;
        while(!valid) {
            int input = textInterface.promptInt("How many acres of land do you wish to plant with seed? ");
            if (getCity().pflanzen(input)) {
                textInterface.printNewStatus(getCity().getStatus());
                valid = true;
            } else {
                textInterface.printErr("Planting failed. Please try again!");
            }
        }
    }

    public void doFeed() {
        boolean valid = false;
        while(!valid) {
            int input = textInterface.promptInt("How many bushles would you like to feed to your people? ");
            if (getCity().ernähren(input)) {
                textInterface.printNewStatus(getCity().getStatus());
                valid = true;
            } else {
                textInterface.printErr("Feeding failed. Please try again!");
            }
        }
    }

    public boolean executeOption(int option) {

        switch (option) {
            case 1:
                //* Buying land
                textInterface.printBuyMenu(getCity().getStatus(), getCity().getPriceperacre());
                doBuyMenu();
                return true;
            case 2:
                //* Selling land
                textInterface.printSellMenu(getCity().getStatus(), getCity().getPriceperacre());
                doSellMenu();
                return true;
            case 3:
                //* Feeding people
                textInterface.printMenu("FEED", getCity().getStatus());
                doFeed();
                return true;
            case 4:
                //* Planting seeds
                textInterface.printMenu("PLANT", getCity().getStatus());
                doPlant();
                return true;
            case 5:
                //* Show Status
                textInterface.printMenu("STATUS", getCity().getStatus());
                return true;
            case 6:
                //* Quit game
                textInterface.printMessage("Quitting game...");
                return false;
            default:
                textInterface.printMessage("Invalid Input. Please try again.");
                return true;
        }
    }
}