package com.codecool.battleship.player;

import com.codecool.battleship.board.Square;
import com.codecool.battleship.board.SquareStatus;
import com.codecool.battleship.exception.GameMessage;
import com.codecool.battleship.ship.Ship;
import com.codecool.battleship.board.Board;
import com.codecool.battleship.ship.ShipType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        return playerShipList.size() > 0;
    }

    public void handlingShots(Square targetedSquare) throws GameMessage{
        switch (targetedSquare.getStatus()) {
            case HIT -> throw new GameMessage("You already hit a ship on this square!");
            case MISS -> throw new GameMessage("You already tried this square, and missed!");
            case SHIP -> {
                StringBuilder msg = new StringBuilder();
                targetedSquare.setStatus(SquareStatus.HIT);
                for (Ship ship : shipList) {
                    if (ship.getBody().contains(targetedSquare)) {
                        msg.append("You hit a ship!");
                        if (ship.isSunk()) {
                            shipList.remove(ship);
                            msg.append(" You sank a ")
                                    .append(ship.getType().toString().toLowerCase())
                                    .append("!");
                        }
                        throw new GameMessage(msg.toString());
                    }
                }
            }
            case EMPTY, NEIGHBOUR -> targetedSquare.setStatus(SquareStatus.MISS);
        }
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

    public Board getBoard() {
        return board;
    }

    public int getPoints() {
        return points;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
}
