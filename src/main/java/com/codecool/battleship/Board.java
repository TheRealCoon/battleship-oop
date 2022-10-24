package com.codecool.battleship;

public class Board {
    private Square[][] ocean;
    public Board(int size){
        ocean = new Square[size][size];
    }
    public boolean isPlacementOk(Square square, Ship ship){
        //TODO
        return false;
    }
}
