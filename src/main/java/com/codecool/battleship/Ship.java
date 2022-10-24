package com.codecool.battleship;

import java.util.List;

public class Ship {
    private final ShipType type;
    private final List<Square> body;

    public Ship(ShipType type, List<Square> body) {
        this.type = type;
        this.body = body;
    }
}
