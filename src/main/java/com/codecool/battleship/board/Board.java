package com.codecool.battleship.board;

import com.codecool.battleship.ship.Ship;

import java.util.*;

import static com.codecool.battleship.utils.Constans.BOARD_SIZE;
import static com.codecool.battleship.utils.Constans.INDENT_SIZE;

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

    public char[][] getCharBoard() {
        char[][] charBoard = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                charBoard[i][j] = ocean[i][j].getStatus().getCharacter();
            }
        }
        return charBoard;
    }

}
