package com.codecool.battleship;

import com.codecool.battleship.board.Board;
import com.codecool.battleship.board.BoardFactory;
import com.codecool.battleship.board.Square;
import com.codecool.battleship.board.SquareStatus;
import com.codecool.battleship.dao.BattleshipDAO;
import com.codecool.battleship.player.Player;
import com.codecool.battleship.player.PlayerType;
import com.codecool.battleship.utils.Constans;
import com.codecool.battleship.utils.Display;
import com.codecool.battleship.utils.Input;

import java.util.NoSuchElementException;

import static com.codecool.battleship.GameMode.PvAI;
import static com.codecool.battleship.GameMode.PvP;
import static com.codecool.battleship.ShipPlacement.RANDOMIZED;
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
        BoardFactory boardFactory = new BoardFactory(BOARD_SIZE);
        setUpPlayers(boardFactory.getBoard());
        currentPlayer = player1;
        boardFactory.putShipsOnBoard(shipPlacement, player1.getPlayerShipList());
        boardFactory.putShipsOnBoard(shipPlacement, player2.getPlayerShipList());
        display.printGameMessage("Ships have been placed! The game begins!");
        while(hasWon()){
            Square targetedSquare = getMove(boardFactory.getBoard());
            currentPlayer = switchPlayer();
            currentPlayer.handlingShots(targetedSquare);
        }
        currentPlayer = switchPlayer();
        display.printTheOutcomeOfTheGame();


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
            if (input.isPositionFormatValid(inputPos)) {
                try {
                    targetedSquare = board.getSquareByPosition(convertToSquare(inputPos));
                } catch (NoSuchElementException e) {
                    display.printErrorMessage(e.getMessage());
                }
            }
        } while (!input.isValidInput(targetedSquare));
        return targetedSquare;
    }

    private Square convertToSquare(String inputPos) {
        return new Square(Integer.parseInt(inputPos.substring(1)) - INDEX_CORRECTION, inputPos.charAt(0) - ASCII_DEC_CODE_UPPERCASE_LETTER_A, null);
    }

    private void setUpPlayers(Board board) {
        player1 = new Player(board, HUMAN, "Player1");
        if (gameMode.equals(PvAI)) {
            player2 = new Player(board, HUMAN, "Player2");
        } else {
            player2 = new Player(board, AI, "Computer");
        }
    }

    private boolean hasWon() {
        //TODO did currentplayer win?
        return false;
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
