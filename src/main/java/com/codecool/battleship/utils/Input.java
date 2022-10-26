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
