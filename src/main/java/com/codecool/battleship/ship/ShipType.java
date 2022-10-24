package com.codecool.battleship.ship;

public enum ShipType {
    CARRIER(5), BATTLESHIP(4), CRUISER(3), SUBMARINE(2), DESTROYER(1);
    private int length;

    ShipType(int length) {
        this.length = length;
    }
}
