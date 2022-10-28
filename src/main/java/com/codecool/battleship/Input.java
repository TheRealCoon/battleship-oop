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



}

