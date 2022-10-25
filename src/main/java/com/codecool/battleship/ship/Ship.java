package com.codecool.battleship.ship;

import com.codecool.battleship.board.Square;

import java.util.List;

public class Ship {
    private final ShipType type;
    private List<Square> body;

    public Ship(ShipType type) {
        this.type = type;
    }

    public List<Square> getBody() {
        return body;
    }

    public void setBody(List<Square> body) {
        this.body = body;
    }
}
