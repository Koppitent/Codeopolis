package de.koppy;

import java.util.Scanner;

public class TextInterface {

    private City city;
    public TextInterface(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public int promptInt(String text) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(text);
        return scanner.nextInt();
    }

    public String promptString(String text) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(text);
        return scanner.next();
    }

    public int printMainMenu() {
        System.out.println();
        System.out.println("===== MAIN MENU =====");
        System.out.println("1. NEW GAME");
        System.out.println("2. QUIT");
        return promptInt("Please select an Option: ");
    }

    public int printGameMenu() {
        System.out.println();
        System.out.println("===== GAME MENU =====");
        System.out.println("1. BUY");
        System.out.println("2. SELL");
        System.out.println("3. FEED");
        System.out.println("4. PLANT");
        System.out.println("5. SHOW STATUS");
        System.out.println("6. QUIT GAME");
        return promptInt("Please select an Option: ");
    }

    public void printBuyMenu() {
        System.out.println();
        System.out.println("===== BUY MENU =====");
        System.out.println("City status: " + city.getStatus());
        System.out.println("Current price per acre: " + city.getPriceperacre());
        System.out.println();
        boolean valid = false;
        while(!valid) {
            int input = promptInt("How many acres would you like to buy? ");
            if (city.kaufen(input)) {
                System.out.println("You bought " + input + " acres.");
                System.out.println("New Status: " + city.getStatus());
                valid = true;
            } else {
                System.out.println("Buying failed. Please try again");
            }
        }
    }

    public void printSellMenu() {
        System.out.println();
        System.out.println("===== SELL MENU =====");
        System.out.println("City status: " + city.getStatus());
        System.out.println("Current price per acre: " + city.getPriceperacre());
        System.out.println();
        boolean valid = false;
        while(!valid) {
            int input = promptInt("How many acres would you like to sell? ");
            if (city.verkaufen(input)) {
                System.out.println("You sold " + input + " acres.");
                System.out.println("New Status: " + city.getStatus());
                valid = true;
            } else {
                System.out.println("Selling failed. Please try again!");
            }
        }
    }

    public void printFeedMenu() {
        System.out.println();
        System.out.println("===== FEED MENU =====");
        System.out.println("City status: " + city.getStatus());
        System.out.println("Current price per acre: " + city.getPriceperacre());
        System.out.println();
        boolean valid = false;
        while(!valid) {
            int input = promptInt("How many bushles would you like to feed to your people? ");
            if (city.ernähren(input)) {
                System.out.println("New Status: " + city.getStatus());
                valid = true;
            } else {
                System.out.println("Feeding failed. Please try again!");
            }
        }
    }

    public void printPlantMenu() {
        System.out.println();
        System.out.println("===== PLANT MENU =====");
        System.out.println("City status: " + city.getStatus());
        System.out.println("Current price per acre: " + city.getPriceperacre());
        System.out.println("");
        boolean valid = false;
        while(!valid) {
            int input = promptInt("How many acres of land do you wish to plant with seed? ");
            if (city.pflanzen(input)) {
                System.out.println("New Status: " + city.getStatus());
                valid = true;
            } else {
                System.out.println("Planting failed. Please try again!");
            }
        }
    }

    public void printStatusMenu() {
        System.out.println();
        System.out.println("===== STATUS MENU =====");
        System.out.println("City Status: " + city.getStatus());
        System.out.println();
    }





}