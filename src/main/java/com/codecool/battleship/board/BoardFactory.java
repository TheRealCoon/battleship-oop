package com.codecool.battleship.board;


import com.codecool.battleship.ShipPlacement;
import com.codecool.battleship.board.Board;
import com.codecool.battleship.ship.Ship;
import com.codecool.battleship.ship.ShipType;
import com.codecool.battleship.utils.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.codecool.battleship.ShipPlacement.MANUAL;
import static com.codecool.battleship.ShipPlacement.RANDOMIZED;
import static com.codecool.battleship.utils.Constans.ASCII_DEC_CODE_UPPERCASE_LETTER_A;

public class BoardFactory {
    private Board board;
    private Input input;

    public BoardFactory(int size, Input input) {
        board = new Board(size);
        this.input = input;
    }

    public List<Square> randomPlacement(ShipType shipType) {
        Random random = new Random();
        List<Square> shipBody = new ArrayList<>();
        Square firstBodySquare = board.getSquareByPosition(random.nextInt(10), random.nextInt(10));
        firstBodySquare.setStatus(SquareStatus.SHIP);
        shipBody.add(firstBodySquare);
        Square nextBodySquare;
        switch (createRandomDirection()) {
            case "horizontal":
                for (int i = 1; i < shipType.getLength(); i++) {
                    nextBodySquare = board.getSquareByPosition(firstBodySquare.getY(), firstBodySquare.getX() + i);
                    nextBodySquare.setStatus(SquareStatus.SHIP);
                }
            case "vertical":
                for (int i = 1; i < shipType.getLength(); i++) {
                    nextBodySquare = board.getSquareByPosition(firstBodySquare.getY()+i, firstBodySquare.getX());
                    nextBodySquare.setStatus(SquareStatus.SHIP);
                }
        }
        //TODO validate shipbody positions
        //TODO handle exceptions thrown because ship hangs out of the board
        return shipBody;
    }


    private void putOneShipOnBoard(Ship ship) {
        List<Square> body = ship.getBody();
        for (Square square : body) {
            board.getOcean()[square.getY()][square.getX()] = square;
            //fill neighbour squares
            if (body.get(0).getX() == body.get(1).getX()) { /*horizontal ship*/
                if (body.get(0).getX() > 0) {/*before the first square of ship if it is not on the left border*/
                    board.getOcean()[body.get(0).getY()][body.get(0).getX() - 1].setStatus(SquareStatus.NEIGHBOUR);
                }
                if (square.getY() < body.size() - 1) { /*under ship if it is not at the bottom*/
                    board.getOcean()[square.getY() + 1][square.getX()].setStatus(SquareStatus.NEIGHBOUR);
                }
                if (body.get(0).getY() > 0) { /*over the ship if it is not at the top*/
                    board.getOcean()[square.getY() - 1][square.getX()].setStatus(SquareStatus.NEIGHBOUR);
                }
                if (body.get(body.size() - 1).getX() < body.size() - 1) {/*after the last square of ship if it is not on the right border*/
                    board.getOcean()[body.get(body.size() - 1).getY()][body.get(0).getX() + 1].setStatus(SquareStatus.NEIGHBOUR);
                }
            }
            if (body.get(0).getY() == body.get(1).getY()) { /*vertical ship*/
                if (body.get(0).getY() > 0) {/*over the first square of ship if it is not on the top*/
                    board.getOcean()[body.get(0).getY() - 1][body.get(0).getX()].setStatus(SquareStatus.NEIGHBOUR);
                }
                if (square.getX() > 0) { /*left side of the ship if it is not at the left border*/
                    board.getOcean()[square.getY()][square.getX() - 1].setStatus(SquareStatus.NEIGHBOUR);
                }
                if (square.getX() < body.size() - 1) { /*right side of the ship if it is not at the right border*/
                    board.getOcean()[square.getY()][square.getX() + 1].setStatus(SquareStatus.NEIGHBOUR);
                }
                if (body.get(body.size() - 1).getY() < body.size() - 1) {/*under the last square of ship if it is not on the bottom*/
                    board.getOcean()[body.get(body.size() - 1).getY() + 1][body.get(0).getX()].setStatus(SquareStatus.NEIGHBOUR);
                }
            }
        }
    }

    public void putShipsOnBoard(ShipPlacement shipPlacement, List<Ship> shipList) {
        for (Ship ship : shipList) {
            if (shipPlacement.equals(MANUAL)) {
                ship.setBody(manualPlacement(ship.getType()));
            } else {
                ship.setBody(randomPlacement(ship.getType()));
            }
            putOneShipOnBoard(ship);
        }

    }

    private static String createRandomDirection() {
        Random random = new Random();
        int randomDirection = random.nextInt(2);
        String direction = "";
        switch (randomDirection) {
            case 0 -> direction = "horizontal";
            case 1 -> direction = "vertical";

        }
        return direction;
    }

    private Square convertInputToSquare(String input) {
        return board.getSquareByPosition(Integer.parseInt(input.substring(1)) - 1, input.charAt(0) - ASCII_DEC_CODE_UPPERCASE_LETTER_A);
    }

    public List<Square> manualPlacement(ShipType shipType) {
        List<Square> shipBody = new ArrayList<>();
        Square firstBodySquare = convertInputToSquare(input.readInput("Ship Starting square:"));
        firstBodySquare.setStatus(SquareStatus.SHIP);
        shipBody.add(firstBodySquare);
        Square nextBodySquare;
        switch (input.readInput("ship direction")) {
            case "right":
                for (int i = 1; i < shipType.getLength(); i++) {
                    nextBodySquare = board.getSquareByPosition(firstBodySquare.getY(), firstBodySquare.getX() + i);
                    nextBodySquare.setStatus(SquareStatus.SHIP);
                }
            case "down":
                for (int i = 1; i < shipType.getLength(); i++) {
                    nextBodySquare = board.getSquareByPosition(firstBodySquare.getY() + i, firstBodySquare.getX());
                    nextBodySquare.setStatus(SquareStatus.SHIP);
                }
            case "left":
                for (int i = 1; i < shipType.getLength(); i++) {
                    nextBodySquare = board.getSquareByPosition(firstBodySquare.getY(), firstBodySquare.getX() - i);
                    nextBodySquare.setStatus(SquareStatus.SHIP);
                }
            case "up":
                for (int i = 1; i < shipType.getLength(); i++) {
                    nextBodySquare = board.getSquareByPosition(firstBodySquare.getY() - i, firstBodySquare.getX());
                    nextBodySquare.setStatus(SquareStatus.SHIP);
                }
        }
        //TODO validate shipbody positions
        //TODO handle exceptions thrown because ship hangs out of the board
        return shipBody;
    }

    public Board getBoard() {
        return board;
    }
}
