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

    public void setValue(int value) {
        this.value = value;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
