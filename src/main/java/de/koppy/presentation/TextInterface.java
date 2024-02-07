package de.koppy.presentation;

import de.koppy.domainmodel.CityState;
import de.koppy.domainmodel.Plants.GetreideSorten;
import de.koppy.domainmodel.TurnResult;
import de.koppy.domainmodel.UserInterface;

import java.util.Scanner;

public class TextInterface implements UserInterface {

    public int promptInt(String text) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(text);
        String input = scanner.next();
        if(input.matches("[0-9]+")) {
            return Integer.parseInt(input);
        }else {
            return promptInt(text);
        }
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
        System.out.println("6. EXPAND");
        System.out.println("7. QUIT GAME");
        System.out.println("8. RUN TURN");
        return promptInt("Please select an Option: ");
    }

    public int printPlantMenu() {
        System.out.println();
        System.out.println("===== PLANT MENU =====");
        //nt i = 1;
        //for(GetreideSorten sorten : GetreideSorten.values()) {
            //System.out.println(i + ". " + sorten.toString() + " pflanzen.");
          //  i++;
        //}
        System.out.println("1. MAIS");
        System.out.println("2. WEIZEN");
        System.out.println("3. GERSTE");
        System.out.println("4. HIRSE");
        System.out.println("5. REIS");
        System.out.println("6. ROGGEN");
        System.out.println(0 + ". back to GAME-MENU");
        return promptInt("Please select an Option: ");
    }

    public void printStatusMenu(CityState city) {
        System.out.println();
        System.out.println("===== STATUS MENU =====");
        System.out.println("City Status: " + city.getStatus());
        System.out.println();
    }

    @Override
    public int buy(int pricePerAcre, CityState city) {
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
        return 0;
    }

    @Override
    public int expand(CityState city) {
        System.out.println();
        System.out.println("===== EXPAND MENU =====");
        System.out.println("City status: " + city.getStatus());
        System.out.println();
        boolean valid = false;
        while(!valid) {
            int input = promptInt("How many expansions would you like to buy? ");
            if (city.expandDepot(input)) {
                System.out.println("You expandet " + input + " in Depot.");
                System.out.println("New Status: " + city.getStatus());
                valid = true;
            } else {
                System.out.println("Expanding failed. Please try again!");
            }
        }
        return 0;
    }

    @Override
    public int sell(int pricePerAcre, CityState city) {
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
        return 0;
    }

    @Override
    public int feed(int bushelsPerResident, CityState city) {
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
        return 0;
    }

    @Override
    public int plant(int bushelsPerAcre, int acrePerResident, CityState city, GetreideSorten sorte) {
        System.out.println();
        System.out.println("===== "+sorte.toString().toUpperCase()+" MENU =====");
        System.out.println("City status: " + city.getStatus());
        System.out.println("Current price per acre: " + city.getPriceperacre());
        System.out.println();
        boolean valid = false;
        while(!valid) {
            int input = promptInt("How many acres of land do you wish to plant with seed? ");
            if (city.pflanzen(input, sorte)) {
                System.out.println("New Status: " + city.getStatus());
                valid = true;
            } else {
                illegalInput("Planting failed. Please try again!");
            }
        }
        return 0;
    }

    @Override
    public void turnEnd(TurnResult result) {
        System.out.println("Result of the Turn: ");
        System.out.println("This year bla, bla...");

        System.out.println("Your progress to Year " + result.getYear());
        System.out.println("Your City does have " + result.getBushels() + " bushles now.");
        System.out.println(result.getBushlesdecayed() + " bushles decayed");
        //*TODO: add info about turn here
    }

    @Override
    public void illegalInput(String message) {
        System.out.println(message);
    }

    @Override
    public void gameWon(String message) {
        System.out.println("You Won!");
        System.out.println(message);
    }

    @Override
    public void gameLost(String message) {
        System.out.println("You Lost! (Game Over)");
        System.out.println(message);
    }
}