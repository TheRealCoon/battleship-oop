package com.codecool.battleship.utils.display;

import com.codecool.battleship.player.Player;
import com.codecool.battleship.player.Score;


public interface Display {
    void printMenu(String label, String[] menu);

    void printBoard(String[][] board, boolean showShips);

    void printGameMessage(String msg);

    void printErrorMessage(String msg);

    void printTheOutcomeOfTheGame(Player player);

    void printTitle(String title);

    void printHighScore(String title, Score[] highScore);
}
