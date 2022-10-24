package com.codecool.battleship.utils;

public class Display {
    public Display() {
    }

    public void printMenu(String label, String[] menu) {
        System.out.println("\n" + label + "\n");
        for (int i = 1; i < menu.length; i++) {
            System.out.println(i + " - " + menu[i]);
        }
        System.out.println(0 + " - " + menu[0]);
    }

    public void printBoard(String[][] board /* or char[][] board */) {
        //TODO receives String/char board
    }

    public void printGameMessage(String msg) {
        System.out.println(msg);
    }

    public void printTheOutcomeOfTheGame() {
        //TODO probably receives game or player as argument
    }

    public void printTitle(String title) {
        StringBuilder sb = new StringBuilder(" ");
        for (char c : title.toUpperCase().toCharArray()) {
            sb.append(c).append(" ");
        }
        System.out.println("   *" + "*".repeat(sb.length()) + " *");
        System.out.println("   *" + sb + "*");
        System.out.println("   *" + "*".repeat(sb.length()) + " *");
    }
}
