package com.codecool.battleship.utils;

import java.util.Scanner;


public class Input {
    private Scanner scanner;

    public Input() {
        scanner = new Scanner(System.in);
    }

    private boolean isValidInput() {
        //TODO
        return false;
    }

    public String readInput(String msg) {
        new Display().printGameMessage(msg);
        return scanner.nextLine();
    }
}
