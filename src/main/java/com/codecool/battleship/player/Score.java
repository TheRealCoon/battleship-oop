package com.codecool.battleship.player;

public class Score {
    private int value;
    private String playerName;

    public Score(String playerName, int value) {
        this.playerName = playerName;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String[] toTableRow() {
        return new String[]{playerName, String.valueOf(value)};
    }
}
