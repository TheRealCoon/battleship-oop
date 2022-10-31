package com.codecool.battleship.utils;


import com.codecool.battleship.game.InterfaceMode;
import com.codecool.battleship.board.SquareStatus;
import com.codecool.battleship.player.Player;
import com.codecool.battleship.player.Score;

import java.util.StringJoiner;

import static com.codecool.battleship.utils.Constans.*;

public class Display {

     private final InterfaceMode interfaceMode;
    public Display(InterfaceMode interfaceMode) {
        this.interfaceMode = interfaceMode;
    }
//TODO need to create methods for GUI display
    public void printMenu(String label, String[] menu) {
        printTitle(label);
        System.out.println();
        for (int i = 1; i < menu.length; i++) {
            System.out.println(" ".repeat(MENU_ITEM_INDENT_SIZE) + i + " - " + menu[i]);
        }
        System.out.println(" ".repeat(MENU_ITEM_INDENT_SIZE) + 0 + " - " + menu[0]);
    }

    public void printBoard(String[][] board, boolean showShips) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHeader());
        for (int y = 0; y < BOARD_SIZE; y++) {
            sb
                    .append(String.format(" ".repeat(INDENT_SIZE) + "%s", getStringRow(board[y], y + 1, showShips)))
                    .append(" ".repeat(TABLE_INDENT_SIZE))
                    .append(System.lineSeparator());
        }
        sb.append(getHeader());
        System.out.println(sb);
    }


    private String getStringRow(String[] row, int lineIndex, boolean showShips) {
        StringJoiner sj = new StringJoiner("", " ", " ");
        for (int x = 0; x < BOARD_SIZE; x++) {
            String boardElement;
            if (!showShips && (row[x].equals(SquareStatus.SHIP.getColor()) || row[x].equals(SquareStatus.NEIGHBOUR.getColor()))) {
                boardElement = SquareStatus.EMPTY.getColor();
            } else {
                boardElement = row[x];
            }
            sj.add(boardElement);
        }
        return String.format(" %2d%s%2d", lineIndex, sj, lineIndex);
    }


    private String getHeader() {
        StringJoiner sjHeader = new StringJoiner("  ", " ", "");
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
