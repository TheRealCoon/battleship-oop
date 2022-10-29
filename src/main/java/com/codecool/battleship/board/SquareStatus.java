package com.codecool.battleship.board;

import static com.codecool.battleship.utils.ConsoleColors.*;

public enum SquareStatus {
    EMPTY(BLUE_BACKGROUND + "   " + RESET),
    NEIGHBOUR(CYAN_BACKGROUND + PURPLE_BOLD_BRIGHT + " + " + RESET),
    SHIP(BLACK_BACKGROUND_BRIGHT + "   " + RESET),
    HIT(BLACK_BACKGROUND_BRIGHT + RED_BOLD_BRIGHT + " ¤ " + RESET),
    MISS(BLUE_BACKGROUND + RED_BOLD_BRIGHT + " × " + RESET);
    private final String color;

    SquareStatus(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }


}
