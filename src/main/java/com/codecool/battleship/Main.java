package com.codecool.battleship;

import com.codecool.battleship.utils.Constants;

public class Main {
    public static void main(String[] args) {
        Battleship battleship = new Battleship();
        battleship.printTitle(Constants.GAME_TITLE);
        battleship.mainMenu();
    }
}
