package com.codecool.battleship.utils.input;

import com.codecool.battleship.board.Square;
import com.codecool.battleship.board.SquareStatus;

public interface Input {

    public boolean isValidCoordinate(String input);

    public Square turnInputIntoSquare(String coordinate, SquareStatus status);

    public int[] guiInputToPositionInPixel(String coordinate);

    public boolean isValidDirectionInput(String input);

    public String readInput(String msg);
}
