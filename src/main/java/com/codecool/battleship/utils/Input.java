package com.codecool.battleship.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.codecool.battleship.utils.Constants.*;

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
