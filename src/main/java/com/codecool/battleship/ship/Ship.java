package com.codecool.battleship.ship;

import com.codecool.battleship.board.Square;
import com.codecool.battleship.board.SquareStatus;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final ShipType type;
    private List<Square> body = new ArrayList<>();

    public Ship(ShipType type) {
        this.type = type;
    }

    public List<Square> getBody() {
        return body;
    }

    public void setBody(List<Square> body) {
        this.body = body;
    }

    public ShipType getType() {
        return type;
    }

    public boolean isSunk() {
        for (Square bodyPiece: body) {
            if (bodyPiece.getStatus().equals(SquareStatus.SHIP)) {
                return false;
            }
        }
        return true;
    }
}
