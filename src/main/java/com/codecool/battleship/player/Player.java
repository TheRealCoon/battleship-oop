package com.codecool.battleship.player;

import com.codecool.battleship.ship.Ship;
import com.codecool.battleship.board.Board;
import com.codecool.battleship.ship.ShipType;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Board board;
    private List<Ship> playerShipList;
    private static List<Ship> shipList= new ArrayList<>(){{
        add(new Ship(ShipType.CARRIER));
        add(new Ship (ShipType.BATTLESHIP));
        add(new Ship (ShipType.CRUISER));
        add(new Ship (ShipType.CRUISER));
        add(new Ship (ShipType.SUBMARINE));
        add(new Ship (ShipType.SUBMARINE));
        add(new Ship (ShipType.SUBMARINE));
        add(new Ship (ShipType.DESTROYER));
        add(new Ship (ShipType.DESTROYER));
        add(new Ship (ShipType.DESTROYER));
        add(new Ship (ShipType.DESTROYER));
    }};

    public Player(Board board) {
        this.board = board;
        playerShipList = shipList;
    }

    public boolean isAlive() {
        //TODO
        return false;
    }

    public void handlingShots() {
        //TODO probably we need to separate this to multiple functions
    }
}
