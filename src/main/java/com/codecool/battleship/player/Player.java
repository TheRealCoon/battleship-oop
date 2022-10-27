package com.codecool.battleship.player;

import com.codecool.battleship.board.Square;
import com.codecool.battleship.ship.Ship;
import com.codecool.battleship.board.Board;
import com.codecool.battleship.ship.ShipType;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Board board;
    private List<Ship> playerShipList;
    private PlayerType playerType;
    private String name;
    private int points;
    private static final List<Ship> shipList = new ArrayList<>() {{
        add(new Ship(ShipType.CARRIER));
        add(new Ship(ShipType.BATTLESHIP));
        add(new Ship(ShipType.CRUISER));
        add(new Ship(ShipType.CRUISER));
        add(new Ship(ShipType.SUBMARINE));
        add(new Ship(ShipType.SUBMARINE));
        add(new Ship(ShipType.SUBMARINE));
        add(new Ship(ShipType.DESTROYER));
        add(new Ship(ShipType.DESTROYER));
        add(new Ship(ShipType.DESTROYER));
        add(new Ship(ShipType.DESTROYER));
    }};

    public Player(Board board, PlayerType playerType, String name) {
        this.board = board;
        this.playerType = playerType;
        this.name = name;
        playerShipList = shipList;
    }

    public boolean isAlive() {
        //TODO
        return false;
    }

    public void handlingShots(Square targetedSquare) {
        //TODO probably we need to separate this to multiple functions
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ship> getPlayerShipList() {
        return playerShipList;
    }
    public Board getBoard(){
        return board;
    }

    public int getPoints() {
        return points;
    }

    public PlayerType getPlayerType() {
    return playerType;
    }
}
