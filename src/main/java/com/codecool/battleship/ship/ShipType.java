package com.codecool.battleship.ship;

public enum ShipType {
    CARRIER(5), BATTLESHIP(4), CRUISER(3), SUBMARINE(2), DESTROYER(1);
    //1 carrier, 1 battleship, 2 cruiser, 3 submarine, 4 destroyer
    private int length;

    ShipType(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
