package com.codecool.battleship;

import java.util.Scanner;

public class Input {

    private String printMessage;

    public Input(String printMessage) {
        this.printMessage = printMessage;
    }

    public Input() {
    }



    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public boolean isValidCoordiate(String input) {
        String X = input.substring(0, 1);
        int XintValue = (int) X.toUpperCase().charAt(0);
        if(XintValue<65 || XintValue>74) return false;
        String Y = input.substring(1);

        return Y.chars().allMatch(Character::isDigit) && Integer.parseInt(Y)>=0 && Integer.parseInt(Y)<=10;
    }



}

