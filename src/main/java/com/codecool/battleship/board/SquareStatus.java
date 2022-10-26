package com.codecool.battleship.board;

public enum SquareStatus {
    EMPTY(' '), NEIGHBOUR('+'), SHIP('#'), HIT('¤'), MISS('×');
private final char character;
    SquareStatus(char character) {
    this.character = character;
    }

    public char getCharacter() {
        return character;
    }



}
