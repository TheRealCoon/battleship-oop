package com.codecool.battleship.board;

import com.codecool.battleship.ship.Ship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        for (Square body:ship.getBody()) {
            return ((body.getX() <= size && body.getY() <= size)
                    && ocean[body.getX()][body.getY()].getStatus().equals(SquareStatus.EMPTY)
                    && !ocean[body.getX()][body.getY()].getStatus().equals(SquareStatus.NEIGHBOUR));
        }
        return false;
    }
}
