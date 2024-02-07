package de.koppy.presentation;

import de.koppy.domainmodel.DifficultyLevel;
import de.koppy.domainmodel.Game;
import de.koppy.domainmodel.Plants.GetreideSorten;

public class Codeopolis {
    public static void main(String[] args) {
        TextInterface ti = new TextInterface();
        Game game = new Game("Town", DifficultyLevel.EASY, ti);
        boolean apprunning = true;
        while (apprunning) {
            switch (ti.printMainMenu()) {
                case 1:
                    //* start new game
                    boolean gamerunning = true;
                    game.resetCity("Town");
                    while (gamerunning) {
                        switch (ti.printGameMenu()) {
                            case 1:
                                //* Buying land
                                game.getTextInterface().buy(game.getCity().getPriceperacre(), game.getCity());
                                break;
                            case 2:
                                //* Selling land
                                game.getTextInterface().sell(game.getCity().getPriceperacre(), game.getCity());
                                break;
                            case 3:
                                //* Feeding people
                                game.getTextInterface().feed(game.getConfig().getBushelsPerResident(), game.getCity());
                                break;
                            case 4:
                                //* Planting seeds
                                boolean inPlantMenu = true;
                                while(inPlantMenu) {
                                    switch (game.getTextInterface().printPlantMenu()) {
                                        case 0:
                                            inPlantMenu = false;
                                            break;
                                        case 1:
                                            game.getTextInterface().plant(game.getConfig().getBushelsPerAcre(), game.getConfig().getAcrePerResident(), game.getCity(), GetreideSorten.MAIS);
                                            break;
                                        case 2:
                                            game.getTextInterface().plant(game.getConfig().getBushelsPerAcre(), game.getConfig().getAcrePerResident(), game.getCity(), GetreideSorten.WEIZEN);
                                            break;
                                        case 3:
                                            game.getTextInterface().plant(game.getConfig().getBushelsPerAcre(), game.getConfig().getAcrePerResident(), game.getCity(), GetreideSorten.GERSTE);
                                            break;
                                        case 4:
                                            game.getTextInterface().plant(game.getConfig().getBushelsPerAcre(), game.getConfig().getAcrePerResident(), game.getCity(), GetreideSorten.HIRSE);
                                            break;
                                        case 5:
                                            game.getTextInterface().plant(game.getConfig().getBushelsPerAcre(), game.getConfig().getAcrePerResident(), game.getCity(), GetreideSorten.REIS);
                                            break;
                                        case 6:
                                            game.getTextInterface().plant(game.getConfig().getBushelsPerAcre(), game.getConfig().getAcrePerResident(), game.getCity(), GetreideSorten.ROGGEN);
                                            break;
                                        default:
                                            System.out.println("Invalid Input. Please try again.");
                                            break;
                                    }
                                }
                                break;
                            case 5:
                                //* Show Status
                                ti.printStatusMenu(game.getCity());
                                break;
                            case 6:
                                //* Show Status
                                game.getTextInterface().expand(game.getCity());
                                break;
                            case 7:
                                //* Quit game
                                System.out.println("Quitting game...");
                                gamerunning = false;
                                break;
                            case 8:
                                //* Next Turn
                                game.nextTurn();
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

}