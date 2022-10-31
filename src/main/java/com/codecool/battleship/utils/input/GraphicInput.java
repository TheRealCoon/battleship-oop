package com.codecool.battleship.utils.input;

import com.codecool.battleship.board.Square;
import com.codecool.battleship.board.SquareStatus;

public class GraphicInput implements Input{
public GraphicInput(){

}
    @Override
    public boolean isValidCoordinate(String input) {
        return false;
    }

    @Override
    public Square turnInputIntoSquare(String coordinate, SquareStatus status) {
        return null;
    }

    @Override
    public int[] guiInputToPositionInPixel(String coordinate) {
        return new int[0];
    }

    @Override
    public boolean isValidDirectionInput(String input) {
        return false;
    }

    @Override
    public String readInput(String msg) {
        return null;
    }
}
