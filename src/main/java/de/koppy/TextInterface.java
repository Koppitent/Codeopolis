package de.koppy;

import java.util.Scanner;

public class TextInterface {

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

    public void printGameMenu() {
        System.out.println();
        System.out.println("===== GAME MENU =====");
        System.out.println("1. BUY");
        System.out.println("2. SELL");
        System.out.println("3. FEED");
        System.out.println("4. PLANT");
        System.out.println("5. SHOW STATUS");
        System.out.println("6. QUIT GAME");
    }

    public void printBuyMenu(String status, int priceperacre) {
        System.out.println();
        System.out.println("===== BUY MENU =====");
        System.out.println("City status: " + status);
        System.out.println("Current price per acre: " + priceperacre);
        System.out.println();
    }

    public void printSellMenu(String status, int priceperacre) {
        System.out.println();
        System.out.println("===== SELL MENU =====");
        System.out.println("City status: " + status);
        System.out.println("Current price per acre: " + priceperacre);
        System.out.println();
    }

    public void printStauts(String status) {
        System.out.println("City Status: " + status);
    }

    public void printNewStatus(String status) {
        System.out.println("New Status: " + status);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printMenu(String menuname, String status) {
        System.out.println();
        System.out.println("===== "+menuname+" MENU =====");
        printStauts(status);
        System.out.println();
    }

    public void printErr(String s) {
        System.out.println("ERROR: " + s);
    }
}