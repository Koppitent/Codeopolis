package de.koppy;

import org.w3c.dom.Text;

public class Codeopolis {
    public static void main(String[] args) {
        startTests();
        TextInterface ti = new TextInterface();
        boolean apprunning = true;
        while (apprunning) {
            switch (ti.printMainMenu()) {
                case 1:
                    //* start new game
                    Game game = new Game("Town", ti);
                    boolean gamerunning = true;
                    while (gamerunning) {
                        ti.printGameMenu();
                        int option = game.getTextInterface().promptInt("Please select an Option: ");
                        gamerunning = game.executeOption(option);
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