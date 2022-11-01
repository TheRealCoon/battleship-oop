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
import static com.codecool.battleship.utils.Constants.*;

public class BoardFactory {
    private Input input;
    private Display display;

    public BoardFactory(Input input, Display display) {
        this.input = input;
        this.display = display;
    }

    private void updateNeighbouringSquares(Ship ship, Board board) {
        List<Square> body = ship.getBody().stream().sorted(Comparator.comparing(Square::getY)).toList();
        Map<String, Integer> coordinates = getCoordinatesForNeighbouringFields(body);
        for (int y = coordinates.get(START_Y); y <= coordinates.get(END_Y); y++) {
            for (int x = coordinates.get(START_X); x <= coordinates.get(END_X); x++) {
                if (x >= 0 && y >= 0 &&
                        x < BOARD_SIZE &&
                        y < BOARD_SIZE &&
                        !body.contains(board.getSquareByPosition(y, x))) {
                    board.getSquareByPosition(y, x).setStatus(SquareStatus.NEIGHBOUR);
                }
            }
        }
    }

    private HashMap<String, Integer> getCoordinatesForNeighbouringFields(List<Square> shipBody) {
        int startY = shipBody.get(0).getY();
        int startX = shipBody.get(0).getX();
        int endY = shipBody.get(0).getY();
        int endX = shipBody.get(0).getX();
        for (Square square : shipBody) {
            if (square.getY() < startY) startY = square.getY();
            if (square.getX() < startX) startX = square.getX();
            if (square.getY() > endY) endY = square.getY();
            if (square.getX() > endX) endX = square.getX();
        }
        int finalStartY = startY - 1;
        int finalStartX = startX - 1;
        int finalEndY = endY + 1;
        int finalEndX = endX + 1;
        return new HashMap<>() {{
            put(START_Y, finalStartY);
            put(START_X, finalStartX);
            put(END_Y, finalEndY);
            put(END_X, finalEndX);
        }};
    }

    public void putShipsOnBoard(ShipPlacement shipPlacement, Player player) {
        List<Ship> shipList = player.getPlayerShipList();
        Board board = player.getBoard();
        for (Ship ship : shipList) {
            if (shipPlacement.equals(MANUAL)) {
                display.printBoard(board.getStringBoard(), true);
                display.printGameMessage("Current player: " + player.getName());
                display.printGameMessage("Ship type:" + ship.getType() + " (length: " + ship.getType().getLength() + ")");
            }
            do {
                if (shipPlacement.equals(MANUAL)) {
                    try {
                        ship.setBody(manualPlacement(ship.getType(), board));
                    } catch (WrongSquareException | NoSuchElementException e) {
                        display.printErrorMessage("You can't place that ship there!");
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
        display.printGameMessage(player.getName() + "'s final board:");
        display.printBoard(board.getStringBoard(), true);
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
        String inputMessageForShipPlacement = "Ship starts from ('A1' - '" + (char) ('A' + BOARD_SIZE - 1) + "10'):";
        String shipStartInput = input.readInput(inputMessageForShipPlacement);
        shipStartInput = getShipStartInput(inputMessageForShipPlacement, shipStartInput);
        Square firstBodySquare = convertInputToSquare(shipStartInput, board);
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
            createShipBodyInGivenDirection(shipType, board, shipBody, firstBodySquare, directionInput);
        }
        if (board.isPlacementOk(shipBody)) {
            for (Square body : shipBody) {
                body.setStatus(SquareStatus.SHIP);
            }
            return shipBody;
        }
        throw new WrongSquareException("You can't place that ship there!");
    }

    private String getShipStartInput(String inputMessageForShipPlacement, String shipStartInput) {
        while (!input.isValidCoordinate(shipStartInput))
            try {
                display.printErrorMessage("Please type a valid coordinate!");
                shipStartInput = input.readInput(inputMessageForShipPlacement);
            } catch (NoSuchElementException ignored) {
            }
        return shipStartInput;
    }


    private static void createShipBodyInGivenDirection(ShipType shipType, Board board, List<Square> shipBody, Square firstBodySquare, String directionInput) {
        Square nextBodySquare;
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
        if (board.isPlacementOk(shipBody)) {
            for (Square body : shipBody) {
                body.setStatus(SquareStatus.SHIP);
            }
            return shipBody;
        } else {
            throw new WrongSquareException("You can't place that ship there!");
        }
    }

}
