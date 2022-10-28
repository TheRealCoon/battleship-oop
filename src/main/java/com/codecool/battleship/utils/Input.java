package com.codecool.battleship.utils;

import com.codecool.battleship.board.Square;

import java.util.Scanner;


public class Input {
    private Scanner scanner;

    public Input() {
        scanner = new Scanner(System.in);
    }

    public boolean isValidInput(Square square) {
        //TODO
        throw new RuntimeException("Not implemented yet!");
//        return false;
    }

    public boolean isValidCoordinate(String input) {
        String X = input.substring(0, 1);
        int XintValue = (int) X.toUpperCase().charAt(0);
        if(XintValue<65 || XintValue>74) return false;
        String Y = input.substring(1);

        return Y.chars().allMatch(Character::isDigit) && Integer.parseInt(Y)>=1 && Integer.parseInt(Y)<=10;
    }

    public String readInput(String msg) {
        new Display().printGameMessage(msg);
        return scanner.nextLine();
    }

    public boolean isPositionFormatValid(String inputPos) {
        //TODO
        throw new RuntimeException("Not implemented yet!");
//        return false;
    }
}
