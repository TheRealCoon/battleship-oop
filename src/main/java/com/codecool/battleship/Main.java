package com.codecool.battleship;

import com.codecool.battleship.game.Battleship;
import com.codecool.battleship.utils.Constans;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            if(args[0].equals("-G")){

            }
        }
        Battleship battleship = new Battleship();
        battleship.printTitle(Constans.GAME_TITLE);
        battleship.mainMenu();
    }
}
