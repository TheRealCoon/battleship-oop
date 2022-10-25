package com.codecool.battleship;

import com.codecool.battleship.utils.Constans;

public class Main {
    public static void main(String[] args) {
        Battleship battleship = new Battleship();
        battleship.printTitle(Constans.GAME_TITLE);
        battleship.mainMenu();
    }
}
