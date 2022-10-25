package com.codecool.battleship.utils;

import static com.codecool.battleship.utils.Constans.*;

public class Display {
    public Display() {
    }

    public void printMenu(String label, String[] menu) {
        printTitle(label);
        System.out.println();
        for (int i = 1; i < menu.length; i++) {
            System.out.println(" ".repeat(MENU_ITEM_INDENT_SIZE) + i + " - " + menu[i]);
        }
        System.out.println(" ".repeat(MENU_ITEM_INDENT_SIZE) + 0 + " - " + menu[0]);
    }

    public void printBoard(String[][] board /* or char[][] board */) {
        //TODO receives String/char board
    }

    public void printGameMessage(String msg) {
        System.out.println(" ".repeat(INDENT_SIZE) + msg);
    }

    public void printErrorMessage(String msg) {
        System.out.println(" ".repeat(INDENT_SIZE) + msg);
    }

    public void printTheOutcomeOfTheGame() {
        //TODO probably receives game or player as argument
    }

    public void printTitle(String title) {
        StringBuilder sb = new StringBuilder(" ");
        for (char c : title.toUpperCase().toCharArray()) {
            sb.append(c).append(" ");
        }
        System.out.println(" ".repeat(TITLE_INDENT_SIZE) + "*" + "*".repeat(sb.length()) + "*");
        System.out.println(" ".repeat(TITLE_INDENT_SIZE) + "*" + sb + "*");
        System.out.println(" ".repeat(TITLE_INDENT_SIZE) + "*" + "*".repeat(sb.length()) + "*");
    }
}
