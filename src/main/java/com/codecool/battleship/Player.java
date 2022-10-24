package com.codecool.battleship;

public class Player {
    private Board board;
    private List<Ship> shipList;

    public Player(Board board, List<Ship> shipList) {
        this.board = board;
        this.shipList = shipList;
    }

    public boolean isAlive() {
        //TODO
        return false;
    }

    public void handlingShots() {
        //TODO probably we need to separate this to multiple functions
    }
}
