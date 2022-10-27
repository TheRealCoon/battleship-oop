package com.codecool.battleship.utils;

import com.codecool.battleship.player.Player;
import com.codecool.battleship.player.Score;

import java.util.Arrays;

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

    public void printBoard(char[][] board) {
        System.out.println();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {

            }
        }
    }


    public void printGameMessage(String msg) {
        System.out.println(" ".repeat(INDENT_SIZE) + msg);
    }

    public void printErrorMessage(String msg) {
        System.out.println(" ".repeat(INDENT_SIZE) + msg);
    }

    public void printTheOutcomeOfTheGame(Player player) {
        printTitle(player.getName() + " has won!");
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

    public void printHighScore(String title, Score[] highScore) {
        printTitle(title);
        for (int i = 0; i < highScore.length; i++) {
            System.out.println(
                    " ".repeat(INDENT_SIZE) +
                            String.format("%3d | ", i + 1) +
                            String.format("%-20s" +
                                            ".".repeat(20 - String.valueOf(highScore[i].getValue()).length()) +
                                            "%d",
                                    highScore[i].getPlayerName(), highScore[i].getValue()).replace(' ', '.'));
        }
    }
}
