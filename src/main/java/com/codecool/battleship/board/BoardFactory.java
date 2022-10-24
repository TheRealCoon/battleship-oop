package com.codecool.battleship.board;

import com.codecool.battleship.board.Board;

public class BoardFactory {
    private Board board;

    //TODO decide which constructor to use: - Create board here,
    // or create board somewhere else, and just pass it to the constructor
    public BoardFactory(int size){
        board = new Board(size);
    }

    public BoardFactory(Board board) {
        this.board = board;
    }

    public void randomPlacement(){
        //TODO
    }

    public void manualPlacement(){
        //TODO probably this should receive an 'Input' instance as parameter
    }
}
