package com.codecool.battleship.board;

import com.codecool.battleship.board.Board;
import com.codecool.battleship.ship.Ship;
import com.codecool.battleship.ship.ShipType;
import com.codecool.battleship.utils.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BoardFactory {
    private Board board;

    //TODO decide which constructor to use: - Create board here,
    // or create board somewhere else, and just pass it to the constructor
    public BoardFactory(int size) {
        board = new Board(size);
    }


    public BoardFactory(Board board) {
        this.board = board;
    }


    public List<Square> randomPlacement(ShipType shipType) {

        Random random = new Random();
        List<Square> shipPosition = new ArrayList<>();
        Square firstSquareOfPosition = new Square(random.nextInt(10), random.nextInt(10), SquareStatus.SHIP);
        shipPosition.add(firstSquareOfPosition);
        switch (createRandomDirection()) {
            case "horizontal":
                for (int i = 1; i < shipType.getLength(); i++) {
                    shipPosition.add(new Square(firstSquareOfPosition.getY(), firstSquareOfPosition.getX() + i, SquareStatus.SHIP));
                }
            case "vertical":
                for (int i = 1; i < shipType.getLength(); i++) {
                    shipPosition.add(new Square(firstSquareOfPosition.getY() - i, firstSquareOfPosition.getX(), SquareStatus.SHIP));
                }

        }
//        for (Square square : shipPosition) {
//            if (square.getX() <= 10 && square.getY() <= 10) return shipPosition;
//            else randomPlacement(shipType);
//        }
        return shipPosition;
    }

    public void putRandomShipsOnBoard(List<Ship> ships) {
        for (Ship ship : ships) {
            putOneShipRandomlyOnBoard(ship);
        }
    }

    private void putOneShipRandomlyOnBoard(Ship ship) {

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

    public List<Square> manualPlacement(Input input, ShipType shipType) {
        List<Square> shipPosition = new ArrayList<>();
        int coordinateY = Integer.parseInt(input.readInput("first coordinate"));
        int coordinateX = Integer.parseInt(input.readInput("second coordinate"));
        Square firstSquareOfPosition = new Square(coordinateY, coordinateX, SquareStatus.SHIP);
        shipPosition.add(firstSquareOfPosition);
        switch (input.readInput("ship direction")) {
            case "right":
                for (int i = 1; i < shipType.getLength(); i++) {
                    shipPosition.add(new Square(firstSquareOfPosition.getY(), firstSquareOfPosition.getX() + i, SquareStatus.SHIP));
                }
            case "down":
                for (int i = 1; i < shipType.getLength(); i++) {
                    shipPosition.add(new Square(firstSquareOfPosition.getY() - i, firstSquareOfPosition.getX(), SquareStatus.SHIP));
                }
            case "left":
                for (int i = 1; i < shipType.getLength(); i++) {
                    shipPosition.add(new Square(firstSquareOfPosition.getY(), firstSquareOfPosition.getX() - i, SquareStatus.SHIP));
                }
            case "up":
                for (int i = 1; i < shipType.getLength(); i++) {
                    shipPosition.add(new Square(firstSquareOfPosition.getY() + i, firstSquareOfPosition.getX(), SquareStatus.SHIP));
                }
        }
        return shipPosition;
    }
}
