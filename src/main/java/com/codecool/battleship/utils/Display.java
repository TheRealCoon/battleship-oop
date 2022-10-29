package com.codecool.battleship.utils;

import com.codecool.battleship.board.SquareStatus;
import com.codecool.battleship.player.Player;
import com.codecool.battleship.player.Score;
import com.codecool.battleship.ship.ShipType;

import java.util.StringJoiner;

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

    public void printBoard(char[][] board, boolean showShips) {
        StringBuilder sb = new StringBuilder();
        String verticalSeparator = "-".repeat(BOARD_SIZE * 6 + 1);
        sb.append(getHeader());
        sb.append(" ".repeat(TABLE_INDENT_SIZE)).append(verticalSeparator).append(System.lineSeparator());
        for (int y = 0; y < BOARD_SIZE; y++) {
            sb
                    .append(String.format(" ".repeat(INDENT_SIZE) + "%s", getStringRow(board[y], y + 1, showShips)))
                    .append(System.lineSeparator())
                    .append(" ".repeat(TABLE_INDENT_SIZE))
                    .append(verticalSeparator)
                    .append(System.lineSeparator());
        }
        sb.append(getHeader());
        System.out.println(sb);
    }


    private String getStringRow(char[] row, int lineIndex, boolean showShips) {
        StringJoiner sj = new StringJoiner("  |  ", " |  ", "  | ");
        for (int x = 0; x < BOARD_SIZE; x++) {
            if (showShips) {
                sj.add(Character.toString(row[x]));
            } else {
                if (row[x] == SquareStatus.SHIP.getCharacter() && row[x] == SquareStatus.NEIGHBOUR.getCharacter()) {
                    row[x] = SquareStatus.EMPTY.getCharacter();
                }
            }
        }
        return String.format(" %2d%s%2d", lineIndex, sj, lineIndex);
    }


    private String getHeader() {
        StringJoiner sjHeader = new StringJoiner("     ", "   ", " ");
        for (int i = 0; i < BOARD_SIZE; i++) {
            sjHeader.add(String.valueOf((char) ('A' + i)));
        }
        return " ".repeat(TABLE_INDENT_SIZE) + sjHeader + System.lineSeparator();
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
