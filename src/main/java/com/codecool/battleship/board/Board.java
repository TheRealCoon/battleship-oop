package com.codecool.battleship.board;

import com.codecool.battleship.ship.Ship;

import java.util.*;

public class Board {
    private Square[][] ocean;
    private int size;
    private Set<Square> shipSquares;

    public Board(int size) {
        this.size = size;
        ocean = new Square[this.size][this.size];
        shipSquares = new HashSet<>();
    }

    public Square[][] createEmptyBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ocean[i][j] = new Square(i, j, SquareStatus.EMPTY);
            }
        }
        return ocean;
    }

    public Square getSquareByPosition(int y, int x) throws NoSuchElementException {
        for (Square[] row : ocean) {
            for (Square square : row) {
                if (square.getY() == y && square.getX() == x) {
                    return square;
                }
            }
        }
        throw new NoSuchElementException("Couldn't find square in position (" + y + ", " + x + ")!");
    }

    public Square getSquareByPosition(Square square) throws NoSuchElementException {
        int y = square.getY();
        int x = square.getX();
        return getSquareByPosition(y, x);
    }

    public Square[][] getOcean() {
        return ocean;
    }

    public void setOcean(Square[][] ocean) {
        this.ocean = ocean;
    }

    public Set<Square> getShipSquares() {
        return shipSquares;
    }

    public void setShipSquares(Set<Square> shipSquares) {
        this.shipSquares = shipSquares;
    }


    public boolean isPlacementOk(Ship ship) {
        for (Square body : ship.getBody()) {
            return ((body.getX() <= size && body.getY() <= size)
                    && ocean[body.getX()][body.getY()].getStatus().equals(SquareStatus.EMPTY)
                    && !ocean[body.getX()][body.getY()].getStatus().equals(SquareStatus.NEIGHBOUR));
        }
        return false;

    }
}
