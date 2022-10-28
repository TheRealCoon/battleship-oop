package com.codecool.battleship.board;


import com.codecool.battleship.ShipPlacement;
import com.codecool.battleship.exception.WrongSquareException;
import com.codecool.battleship.player.Player;
import com.codecool.battleship.ship.Ship;
import com.codecool.battleship.ship.ShipType;
import com.codecool.battleship.utils.Display;
import com.codecool.battleship.utils.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import static com.codecool.battleship.ShipPlacement.MANUAL;
import static com.codecool.battleship.utils.Constans.ASCII_DEC_CODE_UPPERCASE_LETTER_A;
import static com.codecool.battleship.utils.Constans.BOARD_SIZE;

public class BoardFactory {
    private Board board;
    private Input input;
    private Display display;

    public BoardFactory(int size, Input input, Display display) {
        board = new Board(size);
        this.input = input;
        this.display = display;
    }

    public List<Square> randomPlacement(ShipType shipType) throws WrongSquareException, NoSuchElementException {
        Random random = new Random();
        List<Square> shipBody = new ArrayList<>();
        Square firstBodySquare = board.getSquareByPosition(random.nextInt(10), random.nextInt(10));
        shipBody.add(firstBodySquare);
        Square nextBodySquare;
        switch (createRandomDirection()) {
            case "horizontal": {
                for (int i = 1; i < shipType.getLength(); i++) {
                    nextBodySquare = board.getSquareByPosition(firstBodySquare.getY(), firstBodySquare.getX() + i);
                    shipBody.add(nextBodySquare);
                }
                break;
            }
            case "vertical": {
                for (int i = 1; i < shipType.getLength(); i++) {
                    nextBodySquare = board.getSquareByPosition(firstBodySquare.getY() + i, firstBodySquare.getX());
                    shipBody.add(nextBodySquare);
                }
                break;
            }
        }
        //TODO validate shipbody positions
        if (board.isPlacementOk(shipBody)) {
            for (Square body : shipBody) {
                body.setStatus(SquareStatus.SHIP);
            }
            return shipBody;
        }
        throw new WrongSquareException("You can't place that ship there!");
    }


    private void updateNeighbouringSquares(Ship ship) {
        List<Square> body = ship.getBody();
        int startY = body.get(0).getY() - 1;
        int startX = body.get(0).getX() - 1;
        int endY = body.get(body.size() - 1).getY() + 1;
        int endX = body.get(body.size() - 1).getX() + 1;
        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                if (x >= 0 && y >= 0 &&
                        x < BOARD_SIZE &&
                        y < BOARD_SIZE &&
                        !body.contains(board.getSquareByPosition(y, x))) {
                    board.getSquareByPosition(y, x).setStatus(SquareStatus.NEIGHBOUR);
                }
            }
        }
    }

    public void putShipsOnBoard(ShipPlacement shipPlacement, Player player) {
        List<Ship> shipList = player.getPlayerShipList();
        Board board = player.getBoard();
        for (Ship ship : shipList) {
            display.printBoard(board.getCharBoard());
            display.printGameMessage("ship type:" + ship.getType());
            do {
                if (shipPlacement.equals(MANUAL)) {
                    try {
                        ship.setBody(manualPlacement(ship.getType()));
                    } catch (WrongSquareException | NoSuchElementException e) {
                        display.printErrorMessage(e.getMessage());
                    }
                } else {
                    try {
                        ship.setBody(randomPlacement(ship.getType()));
                    } catch (WrongSquareException | NoSuchElementException ignored) {
                    }
                }
            } while (ship.getBody().size() == 0);
            updateNeighbouringSquares(ship);
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

    public List<Square> manualPlacement(ShipType shipType) throws WrongSquareException {
        List<Square> shipBody = new ArrayList<>();
        Square firstBodySquare = convertInputToSquare(input.readInput("Ship Starting square:"));
        firstBodySquare.setStatus(SquareStatus.SHIP);
        shipBody.add(firstBodySquare);
        Square nextBodySquare;
        switch (input.readInput("ship direction")) {
            case "right": {
                for (int i = 1; i < shipType.getLength(); i++) {
                    nextBodySquare = board.getSquareByPosition(firstBodySquare.getY(), firstBodySquare.getX() + i);
                    nextBodySquare.setStatus(SquareStatus.SHIP);
                    shipBody.add(nextBodySquare);
                }
                break;
            }
            case "down": {
                for (int i = 1; i < shipType.getLength(); i++) {
                    nextBodySquare = board.getSquareByPosition(firstBodySquare.getY() + i, firstBodySquare.getX());
                    nextBodySquare.setStatus(SquareStatus.SHIP);
                    shipBody.add(nextBodySquare);
                }
                break;
            }
            case "left": {
                for (int i = 1; i < shipType.getLength(); i++) {
                    nextBodySquare = board.getSquareByPosition(firstBodySquare.getY(), firstBodySquare.getX() - i);
                    nextBodySquare.setStatus(SquareStatus.SHIP);
                    shipBody.add(nextBodySquare);
                }
                break;
            }
            case "up": {
                for (int i = 1; i < shipType.getLength(); i++) {
                    nextBodySquare = board.getSquareByPosition(firstBodySquare.getY() - i, firstBodySquare.getX());
                    nextBodySquare.setStatus(SquareStatus.SHIP);
                    shipBody.add(nextBodySquare);
                }
                break;
            }
        }
        //TODO validate shipbody positions
        //TODO handle exceptions thrown because ship hangs out of the board
        if (board.isPlacementOk(shipBody)) {
            return shipBody;
        }
        throw new WrongSquareException("You can't place that ship there!");
    }

    public Board getBoard() {
        return board;
    }
}
