package com.codecool.battleship.board;


import com.codecool.battleship.ShipPlacement;
import com.codecool.battleship.exception.NoSuchDirectionException;
import com.codecool.battleship.exception.WrongSquareException;
import com.codecool.battleship.player.Player;
import com.codecool.battleship.ship.Ship;
import com.codecool.battleship.ship.ShipType;
import com.codecool.battleship.utils.Display;
import com.codecool.battleship.utils.Input;
import java.util.*;

import static com.codecool.battleship.ShipPlacement.MANUAL;
import static com.codecool.battleship.ShipPlacement.RANDOMIZED;
import static com.codecool.battleship.utils.Constans.ASCII_DEC_CODE_UPPERCASE_LETTER_A;
import static com.codecool.battleship.utils.Constans.BOARD_SIZE;

public class BoardFactory {
    private Input input;
    private Display display;

    public BoardFactory(Input input, Display display) {
        this.input = input;
        this.display = display;
    }

    private void updateNeighbouringSquares(Ship ship, Board board) {
        //TODO neighbours are not ok when direction is left

        List<Square> body = ship.getBody().stream().sorted(Comparator.comparing(Square::getY)).toList();
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
            if (shipPlacement.equals(MANUAL)) {
                display.printBoard(board.getStringBoard(), true);
                display.printGameMessage("Current player:" + player.getName());
                display.printGameMessage("Ship type:" + ship.getType() + " (length: " + ship.getType().getLength() + ")");
            }
            do {
                if (shipPlacement.equals(MANUAL)) {
                    try {
                        ship.setBody(manualPlacement(ship.getType(), board));
                    } catch (WrongSquareException | NoSuchElementException e) {
                        display.printErrorMessage(e.getMessage());
                    }
                } else {
                    try {
                        ship.setBody(randomPlacement(ship.getType(), board));
                    } catch (WrongSquareException | NoSuchElementException ignored) {
                    }
                }
            } while (ship.getBody().size() == 0);
            updateNeighbouringSquares(ship, board);
        }
        if (shipPlacement.equals(RANDOMIZED)) {
            display.printGameMessage(player.getName() + "'s final board:");
            display.printBoard(board.getStringBoard(), true);
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

    private Square convertInputToSquare(String input, Board board) {
        return board.getSquareByPosition(Integer.parseInt(input.substring(1)) - 1,
                Character.toUpperCase(input.charAt(0)) - ASCII_DEC_CODE_UPPERCASE_LETTER_A);
    }

    public List<Square> manualPlacement(ShipType shipType, Board board) throws WrongSquareException, NoSuchElementException, NoSuchDirectionException {
        List<Square> shipBody = new ArrayList<>();
        String inputMessageAtShipPlacement = "Ship starts from ('a1' - 'j10'):";
        String shipStartInput = input.readInput(inputMessageAtShipPlacement);
        while (!input.isValidCoordinate(shipStartInput))
            try {
                display.printErrorMessage("Please type a valid coordinate!");
                shipStartInput = input.readInput(inputMessageAtShipPlacement);
            } catch (NoSuchElementException ignored) {
            }
        Square firstBodySquare = convertInputToSquare(shipStartInput, board);
        Square nextBodySquare;
        shipBody.add(firstBodySquare);
        if (shipType != ShipType.DESTROYER) {
            String inputMessageForShipDirection = "Ship direction ('right', 'left', 'up', 'down'):";
            String directionInput = input.readInput(inputMessageForShipDirection);
            while (!input.isValidDirectionInput(directionInput))
                try {
                    display.printErrorMessage("Please select a valid ship direction!");
                    directionInput = input.readInput(inputMessageForShipDirection);
                } catch (NoSuchDirectionException ignored) {
                }
            switch (directionInput) {
                case "right" -> {
                    for (int i = 1; i < shipType.getLength(); i++) {
                        nextBodySquare = board.getSquareByPosition(firstBodySquare.getY(), firstBodySquare.getX() + i);
                        shipBody.add(nextBodySquare);
                    }
                }
                case "down" -> {
                    for (int i = 1; i < shipType.getLength(); i++) {
                        nextBodySquare = board.getSquareByPosition(firstBodySquare.getY() + i, firstBodySquare.getX());
                        shipBody.add(nextBodySquare);
                    }
                }
                case "left" -> {
                    for (int i = 1; i < shipType.getLength(); i++) {
                        nextBodySquare = board.getSquareByPosition(firstBodySquare.getY(), firstBodySquare.getX() - i);
                        shipBody.add(nextBodySquare);
                    }
                }
                case "up" -> {
                    for (int i = 1; i < shipType.getLength(); i++) {
                        nextBodySquare = board.getSquareByPosition(firstBodySquare.getY() - i, firstBodySquare.getX());
                        shipBody.add(nextBodySquare);
                    }
                }
            }
        }

        //TODO change error msg if user wants to put the ship outside of the board

        if (board.isPlacementOk(shipBody)) {
            for (Square body : shipBody) {
                body.setStatus(SquareStatus.SHIP);
            }
            return shipBody;
        }
        throw new WrongSquareException("You can't place that ship there!");
    }

    public List<Square> randomPlacement(ShipType shipType, Board board) throws WrongSquareException, NoSuchElementException {
        Random random = new Random();
        List<Square> shipBody = new ArrayList<>();
        Square firstBodySquare = board.getSquareByPosition(random.nextInt(10), random.nextInt(10));
        shipBody.add(firstBodySquare);
        Square nextBodySquare;
        switch (createRandomDirection()) {
            case "horizontal" -> {
                for (int i = 1; i < shipType.getLength(); i++) {
                    nextBodySquare = board.getSquareByPosition(firstBodySquare.getY(), firstBodySquare.getX() + i);
                    shipBody.add(nextBodySquare);
                }
            }
            case "vertical" -> {
                for (int i = 1; i < shipType.getLength(); i++) {
                    nextBodySquare = board.getSquareByPosition(firstBodySquare.getY() + i, firstBodySquare.getX());
                    shipBody.add(nextBodySquare);
                }
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

}
