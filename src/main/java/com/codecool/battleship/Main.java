package com.codecool.battleship;

import com.codecool.battleship.game.Battleship;
import com.codecool.battleship.game.InterfaceMode;
import com.codecool.battleship.utils.Constants;

public class Main {
    public static void main(String[] args) {
        InterfaceMode ifm =
                (args.length > 0 && args[0].equalsIgnoreCase("-G")) ?
                InterfaceMode.GUI :
                InterfaceMode.Console;
        Battleship battleship = new Battleship(ifm);
        battleship.printTitle(Constants.GAME_TITLE);
        battleship.mainMenu();
    }
}
