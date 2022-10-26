package com.codecool.battleship;

import com.codecool.battleship.player.Player;
import com.codecool.battleship.utils.Display;
import com.codecool.battleship.utils.Input;

public class Game {
    private Player player1;
    private Player player2;
    private Input input;
    private Display display;
    private GameMode gameMode;
    private ShipPlacement shipPlacement;

    public Game(Input input, Display display, GameMode gameMode, ShipPlacement shipPlacement) {
        this.input = input;
        this.display = display;
        this.gameMode = gameMode;
        this.shipPlacement = shipPlacement;
    }



    public void play() {

        //TODO
    }

    private boolean hasWon() {
        //TODO
        return false;
    }
}
