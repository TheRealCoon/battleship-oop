package com.codecool.battleship.utils;

import com.codecool.battleship.board.Square;
import com.codecool.battleship.game.InterfaceMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.codecool.battleship.utils.Constans.BOARD_SIZE;


public class Input {
    private Scanner scanner;
    private final InterfaceMode interfaceMode;
    private final Display display;

    public Input(InterfaceMode interfaceMode, Display display) {
        this.interfaceMode = interfaceMode;
        this.display = display;
        if (this.interfaceMode.equals(InterfaceMode.Console)) {
            scanner = new Scanner(System.in);
        }
    }
//TODO need to create methods for GUI input
    public boolean isValidInput(Square square) {
        //TODO
        throw new RuntimeException("Not implemented yet!");
//        return false;
    }

    public boolean isValidCoordinate(String input) {
        String X = input.substring(0, 1);
        int XintValue = X.toUpperCase().charAt(0);
        if (XintValue < 'A' || XintValue > 'A' + BOARD_SIZE) return false;
        String Y = input.substring(1);
        return Y.chars().allMatch(Character::isDigit) && Integer.parseInt(Y) >= 1 && Integer.parseInt(Y) <= BOARD_SIZE;
    }

    public boolean isValidDirectionInput(String input){
        List<String> directions = new ArrayList<>(){{
            add("right");
            add("left");
            add("up");
            add("down");
        }};
        return directions.contains(input);
    }

    public String readInput(String msg) {
        display.printGameMessage(msg);
        return scanner.nextLine();
    }

    public boolean isPositionFormatValid(String inputPos) {
        //TODO
        throw new RuntimeException("Not implemented yet!");
//        return false;
    }
}
