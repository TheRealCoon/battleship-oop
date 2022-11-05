package com.codecool.battleship.utils;

import com.codecool.battleship.board.Square;
import com.codecool.battleship.board.SquareStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.codecool.battleship.utils.Constans.*;
import static com.codecool.battleship.utils.Constans.FIELD_SIZE_IN_PIXEL;


public class Input {
    private Scanner scanner;

    public Input() {
        scanner = new Scanner(System.in);
    }


    public boolean isValidCoordinate(String input) {
        if (input.length() < 2) return false;
        String X = input.substring(0, 1);
        int XIntValue = X.toUpperCase().charAt(0);
        if (XIntValue < 'A' || XIntValue > 'A' + BOARD_SIZE - 1) return false;
        String Y = input.substring(1);
        return Y.chars().allMatch(Character::isDigit) &&
                Integer.parseInt(Y) >= 1 && Integer.parseInt(Y) <= BOARD_SIZE;
    }


    public int[] guiInputToPositionInPixel(String coordinate) {
        int x = (coordinate.substring(0, 1).toUpperCase().charAt(0) - CHARACTER_TO_BOARD_CORRECTION) * FIELD_SIZE_IN_PIXEL;
        int y = Integer.parseInt(coordinate.substring(1)) * FIELD_SIZE_IN_PIXEL;
        return new int[]{x, y};
    }

    public boolean isValidDirectionInput(String input) {
        List<String> directions = new ArrayList<>() {{
            add("right");
            add("left");
            add("up");
            add("down");
        }};
        return directions.contains(input);

    }

    public String readInput(String msg) {
        new Display().printGameMessage(msg);
        return scanner.nextLine();
    }

}
