package com.codecool.battleship;

import com.codecool.battleship.board.Board;
import com.codecool.battleship.board.BoardFactory;
import com.codecool.battleship.board.Square;
import com.codecool.battleship.board.SquareStatus;
import com.codecool.battleship.dao.BattleshipDAO;
import com.codecool.battleship.exception.GameMessage;
import com.codecool.battleship.player.Player;
import com.codecool.battleship.player.PlayerType;
import com.codecool.battleship.utils.Constans;
import com.codecool.battleship.utils.Display;
import com.codecool.battleship.utils.Input;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static com.codecool.battleship.GameMode.PvAI;
import static com.codecool.battleship.player.PlayerType.AI;
import static com.codecool.battleship.player.PlayerType.HUMAN;
import static com.codecool.battleship.utils.Constans.*;

public class Game {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Input input;
    private Display display;
    private GameMode gameMode;
    private ShipPlacement shipPlacement;

    public Game(Input input, Display display, GameMode gameMode, ShipPlacement shipPlacement) {
        this.input = input;
        this.display = display;
        this.gameMode = gameMode;
        this.shipPlacement = shipPlacement;
    }


    public void play() {
        BoardFactory boardFactory = new BoardFactory(input, display);
        setUpPlayers();
        currentPlayer = player1;

        if (shipPlacement.equals(ShipPlacement.MANUAL)) {
            display.printGameMessage(player1.getName() + ", place your ships on Board!");
            boardFactory.putShipsOnBoard(shipPlacement, player1);
            display.printGameMessage(player2.getName() + ", place your ships on Board!");
            boardFactory.putShipsOnBoard(shipPlacement, player2);
        }
        display.printGameMessage("Ships have been placed! The game begins!");

        while (!hasWon(switchPlayer())) {
            display.printBoard(switchPlayer().getBoard().getCharBoard(), false); //Shows the enemy board without ships, we will mark shots on this
            Square targetedSquare = getMove(currentPlayer.getBoard());
            currentPlayer = switchPlayer();
            try {
                currentPlayer.handlingShots(targetedSquare);
            } catch (GameMessage e) {
                display.printGameMessage(e.getMessage());
            }
        }
        currentPlayer = switchPlayer();
        display.printTheOutcomeOfTheGame(currentPlayer);
    }


    private Player switchPlayer() {
        if (currentPlayer.equals(player1)) {
            return player2;
        }
        return player1;
    }

    private Square getMove(Board board) {
        String inputPos;
        Square targetedSquare = null;
        do {
            inputPos = input.readInput("Aim at: ");
            if (input.isValidCoordinate(inputPos)) {
                targetedSquare = board.getSquareByPosition(convertToSquare(inputPos));
            }
        } while (!input.isValidCoordinate(inputPos));
        return targetedSquare;
    }

    private Square convertToSquare(String inputPos) {
        return new Square(Integer.parseInt(inputPos.substring(1)) - INDEX_CORRECTION, Character.toUpperCase(inputPos.charAt(0)) - ASCII_DEC_CODE_UPPERCASE_LETTER_A, null);
    }

    private void setUpPlayers() {
        player1 = new Player(new Board(BOARD_SIZE), HUMAN, "Player1");
        if (gameMode.equals(PvAI)) {
            player2 = new Player(new Board(BOARD_SIZE), AI, "Computer");
        } else {
            player2 = new Player(new Board(BOARD_SIZE), HUMAN, "Player2");
        }
    }

    private boolean hasWon(Player player) {
        return !player.isAlive();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
