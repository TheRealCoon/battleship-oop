package com.codecool.battleship.board;

import com.codecool.battleship.exception.WrongSquareException;

import java.util.*;

import static com.codecool.battleship.utils.Constans.BOARD_SIZE;

public class Board {
    private Square[][] ocean;
    private int size;
    private Set<Square> shipSquares;

    public Board(int size) {
        this.size = size;
        ocean = createEmptyBoard();
        shipSquares = new HashSet<>();
    }

    public Square[][] createEmptyBoard() {
        Square[][] ocean = new Square[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ocean[i][j] = new Square(i, j, SquareStatus.EMPTY);
            }
        }
        return ocean;
    }

    public Square getSquareByPosition(int y, int x) throws WrongSquareException {
        for (Square[] row : ocean) {
            for (Square square : row) {
                if (square.getY() == y && square.getX() == x) {
                    return square;
                }
            }
        }
        throw new WrongSquareException("You can't place that ship there!");
    }

    public Square getSquareByPosition(Square square) {
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


    public boolean isPlacementOk(List<Square> shipBody) {
        for (Square body : shipBody) {
            if (body.getX() >= size || body.getY() >= size
                    || body.getX() < 0 || body.getY() < 0
                    || ocean[body.getY()][body.getX()].getStatus().equals(SquareStatus.NEIGHBOUR)
                    || ocean[body.getY()][body.getX()].getStatus().equals(SquareStatus.SHIP)) return false;
        }

        return true;
    }

    public String[][] getStringBoard() {
        String[][] stringBoard = new String[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                stringBoard[i][j] = ocean[i][j].getStatus().getColor();
            }
        }
        return stringBoard;
    }

}
