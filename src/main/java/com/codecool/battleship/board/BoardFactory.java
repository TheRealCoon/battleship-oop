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

public class BoardFactory {
    private Board board;

    public BoardFactory(int size) {
        board = new Board(size);
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
        return shipPosition;
    }

    public void putShipsOnBoard(List<Ship> ships) {
        for (Ship ship : ships) {
            putOneShipOnBoard(ship, ship.getBody());
        }
    }

    private void putOneShipOnBoard(Ship ship, List<Square> body) {
        for (Square square : body) {
            board.getOcean()[square.getY()][square.getX()] = square;
            //fill neighbors
            if(body.get(0).getX()==body.get(1).getX()){ /*horizontal ship*/
                if(body.get(0).getX()!=0){
                    board.getOcean()[body.get(0).getY()][body.get(0).getX()].setStatus(SquareStatus.NEIGHBOUR);/*first square of ship*/
                }
            }
            //TODO under, over and after ship set SquareStatus NEIGHBOUR
            //TODO same for vertical ship
        }

    }

    public void putShipsOnBoard(ShipPlacement shipPlacement, List<Ship> shipList) {
        for (Ship ship : shipList) {
            if (shipPlacement.equals(MANUAL)) {
                //TODO get a Square and direction as Input
            } else {
                //TODO get a random Square and direction
            }
            //putShipOnBoard(square, direction);
        }

    }

    public void putShipsOnBoardManually(List<Ship> shipList) {
        //TODO
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

    public Board getBoard() {
        return board;
    }
}
