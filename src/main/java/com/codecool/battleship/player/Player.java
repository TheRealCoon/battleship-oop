package com.codecool.battleship.player;

import com.codecool.battleship.board.Square;
import com.codecool.battleship.board.SquareStatus;
import com.codecool.battleship.exception.GameMessage;
import com.codecool.battleship.ship.Ship;
import com.codecool.battleship.board.Board;
import com.codecool.battleship.ship.ShipType;
import com.codecool.battleship.utils.Constans;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.codecool.battleship.utils.Constans.*;

public class Player {
    private Board board;
    private List<Ship> playerShipList;
    private PlayerType playerType;
    private String name;
    private int points;

    public Player(Board board, PlayerType playerType, String name) {
        this.board = board;
        this.playerType = playerType;
        this.name = name;
        playerShipList = new ArrayList<>() {{
//            add(new Ship(ShipType.CARRIER));
//            add(new Ship(ShipType.BATTLESHIP));
//            add(new Ship(ShipType.CRUISER));
//            add(new Ship(ShipType.CRUISER));
//            add(new Ship(ShipType.SUBMARINE));
//            add(new Ship(ShipType.SUBMARINE));
            add(new Ship(ShipType.SUBMARINE));
            add(new Ship(ShipType.DESTROYER));
//            add(new Ship(ShipType.DESTROYER));
//            add(new Ship(ShipType.DESTROYER));
//            add(new Ship(ShipType.DESTROYER));
        }};
    }

    public boolean isAlive() {
        return playerShipList.size() > 0;
    }

    public String handlingShots(Square targetedSquare, Player player) {
        StringBuilder results = new StringBuilder();
        switch (targetedSquare.getStatus()) {
            case HIT -> {
                player.addToPoints(PENALTY_FOR_MISSING);
                results.append("You already hit a ship on this square! " + PENALTY_FOR_MISSING + " pts");
            }
            case MISS -> {
                player.addToPoints(PENALTY_FOR_MISSING);
                results.append("You already tried this square, and missed! " + PENALTY_FOR_MISSING + " pts");
            }
            case SHIP -> {
                targetedSquare.setStatus(SquareStatus.HIT);
                for (Ship ship : playerShipList) {
                    if (ship.getBody().contains(targetedSquare)) {
                        player.addToPoints(REWARD_FOR_HITTING_SHIP);
                        results.append("You hit a ship! +" + REWARD_FOR_HITTING_SHIP + " pts")
                                .append(System.lineSeparator());
                        if (ship.isSunk()) {
                            player.addToPoints(REWARD_FOR_SINKING_SHIP);
                            playerShipList.remove(ship);
                            results.append(" ".repeat(INDENT_SIZE))
                                    .append("You sank a ")
                                    .append(ship.getType().toString().toLowerCase())
                                    .append("! +" + REWARD_FOR_SINKING_SHIP + " pts");
                        }
                        break;
                    }
                }
            }
            case EMPTY, NEIGHBOUR -> {
                player.addToPoints(PENALTY_FOR_MISSING);
                targetedSquare.setStatus(SquareStatus.MISS);
                results.append("You missed! " + PENALTY_FOR_MISSING + " pts");
            }
        }
        return results.toString();
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

    public void addToPoints(int reward) {
        points += reward;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
}
