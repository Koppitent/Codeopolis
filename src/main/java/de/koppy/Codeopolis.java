package de.koppy;

public class Codeopolis {
    public static void main(String[] args) {
        startTests();
        TextInterface ti = new TextInterface(new Game("Town"));
        boolean apprunning = true;
        while (apprunning) {
            switch (ti.printMainMenu()) {
                case 1:
                    //* start new game
                    boolean gamerunning = true;
                    ti.resetCity("Town");
                    while (gamerunning) {

                        switch (ti.printGameMenu()) {
                            case 1:
                                //* Buying land
                                ti.printBuyMenu();
                                break;
                            case 2:
                                //* Selling land
                                ti.printSellMenu();
                                break;
                            case 3:
                                //* Feeding people
                                ti.printFeedMenu();
                                break;
                            case 4:
                                //* Planting seeds
                                ti.printPlantMenu();
                                break;
                            case 5:
                                //* Show Status
                                ti.printStatusMenu();
                                break;
                            case 6:
                                //* Quit game
                                System.out.println("Quitting game...");
                                gamerunning = false;
                                break;
                            default:
                                System.out.println("Invalid Input. Please try again.");
                                break;
                        }

                    }
                    break;
                case 2:
                    //* Quit Program
                    apprunning = false;
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }



    }

    public static void startTests() {
        System.out.println("Program starting tests...!");
        CityTestDrive.startTest();
    }

}