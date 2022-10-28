package com.codecool.battleship.exception;

public class WrongSquareException extends RuntimeException {
    public WrongSquareException(String msg) {
        super(msg);
    }
}
