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
        //1 carrier, 1 battleship, 2 cruiser, 3 submarine, 4 destroyer
        Random random = new Random();
        List<Square> shipPosition = new ArrayList<>();
        Square firstSquareOfPosition = new Square(random.nextInt(10), random.nextInt(10), SquareStatus.SHIP);
        shipPosition.add(firstSquareOfPosition);
        switch (createRandomDirection()){
            case "right":
                for (int i = 1; i < shipType.getLength(); i++) {
                    shipPosition.add(new Square(firstSquareOfPosition.getY(), firstSquareOfPosition.getX()+i, SquareStatus.SHIP));
                }
            case "down":
                for (int i = 1; i < shipType.getLength(); i++) {
                    shipPosition.add(new Square(firstSquareOfPosition.getY()-i, firstSquareOfPosition.getX(), SquareStatus.SHIP));
                }
            case "left":
                for (int i = 1; i < shipType.getLength(); i++) {
                    shipPosition.add(new Square(firstSquareOfPosition.getY(), firstSquareOfPosition.getX()-i, SquareStatus.SHIP));
                }
            case "up":
                for (int i = 1; i < shipType.getLength(); i++) {
                    shipPosition.add(new Square(firstSquareOfPosition.getY()+i, firstSquareOfPosition.getX(), SquareStatus.SHIP));
                }
        }

        return shipPosition;
    }


    private static String createRandomDirection() {
        Random random = new Random();
        int randomDirection = random.nextInt(4);
        String direction = "";
        switch (randomDirection){
            case 0 -> direction = "right";
            case 1 -> direction = "down";
            case 2 -> direction = "left";
            case 3 -> direction = "up";
        }
        return direction;
    }


    public List<Square>  manualPlacement(Input input, ShipType shipType) {
        List<Square> shipPosition = new ArrayList<>();
        int coordinateY = Integer.parseInt(input.readInput("first coordinate"));
        int coordinateX = Integer.parseInt(input.readInput("second coordinate"));
        Square firstSquareOfPosition = new Square(coordinateY, coordinateX, SquareStatus.SHIP);
        shipPosition.add(firstSquareOfPosition);
        switch (input.readInput("ship direction")){
            case "right":
                for (int i = 1; i < shipType.getLength(); i++) {
                    shipPosition.add(new Square(firstSquareOfPosition.getY(), firstSquareOfPosition.getX()+i, SquareStatus.SHIP));
                }
            case "down":
                for (int i = 1; i < shipType.getLength(); i++) {
                    shipPosition.add(new Square(firstSquareOfPosition.getY()-i, firstSquareOfPosition.getX(), SquareStatus.SHIP));
                }
            case "left":
                for (int i = 1; i < shipType.getLength(); i++) {
                    shipPosition.add(new Square(firstSquareOfPosition.getY(), firstSquareOfPosition.getX()-i, SquareStatus.SHIP));
                }
            case "up":
                for (int i = 1; i < shipType.getLength(); i++) {
                    shipPosition.add(new Square(firstSquareOfPosition.getY()+i, firstSquareOfPosition.getX(), SquareStatus.SHIP));
                }
        }

        return shipPosition;
    }
}
