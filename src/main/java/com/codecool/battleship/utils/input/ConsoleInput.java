package com.codecool.battleship.utils.input;

import com.codecool.battleship.board.Square;
import com.codecool.battleship.board.SquareStatus;
import com.codecool.battleship.utils.display.ConsoleDisplay;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.codecool.battleship.utils.Constans.*;
import static com.codecool.battleship.utils.Constans.FIELD_SIZE_IN_PIXEL;

public class ConsoleInput implements Input {
    private Scanner scanner;

    public ConsoleInput() {
        scanner = new Scanner(System.in);
    }

    @Override
    public boolean isValidCoordinate(String input) {
        if (input.length() < 2) return false;
        String X = input.substring(0, 1);
        int XIntValue = X.toUpperCase().charAt(0);
        if (XIntValue < 'A' || XIntValue > 'A' + BOARD_SIZE) return false;
        String Y = input.substring(1);
        return Y.chars().allMatch(Character::isDigit) &&
                Integer.parseInt(Y) >= 1 && Integer.parseInt(Y) <= BOARD_SIZE;
    }


    @Override
    public Square turnInputIntoSquare(String coordinate, SquareStatus status) {
        int x = (coordinate.substring(0, 1).toUpperCase().charAt(0) - CHARACTER_TO_BOARD_CORRECTION) * FIELD_SIZE_IN_PIXEL;
        int y = Integer.parseInt(coordinate.substring(1)) * FIELD_SIZE_IN_PIXEL;
        return new Square(y, x, status);
    }

    @Override
    public int[] guiInputToPositionInPixel(String coordinate) {
        int x = (coordinate.substring(0, 1).toUpperCase().charAt(0) - CHARACTER_TO_BOARD_CORRECTION) * FIELD_SIZE_IN_PIXEL;
        int y = Integer.parseInt(coordinate.substring(1)) * FIELD_SIZE_IN_PIXEL;
        return new int[]{x, y};
    }

    @Override
    public boolean isValidDirectionInput(String input) {
        List<String> directions = new ArrayList<>() {{
            add("right");
            add("left");
            add("up");
            add("down");
        }};
        return directions.contains(input);
    }

    @Override
    public String readInput(String msg) {
        new ConsoleDisplay().printGameMessage(msg);
        return scanner.nextLine();
    }

}
