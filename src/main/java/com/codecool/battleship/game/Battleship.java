package com.codecool.battleship.game;

import com.codecool.battleship.board.ShipPlacement;
import com.codecool.battleship.dao.BattleshipDAO;
import com.codecool.battleship.game.Game;
import com.codecool.battleship.game.GameMode;
import com.codecool.battleship.player.Player;
import com.codecool.battleship.player.Score;
import com.codecool.battleship.utils.Display;
import com.codecool.battleship.utils.Input;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.codecool.battleship.game.GameMode.PvAI;
import static com.codecool.battleship.game.GameMode.PvP;
import static com.codecool.battleship.board.ShipPlacement.MANUAL;
import static com.codecool.battleship.board.ShipPlacement.RANDOMIZED;
import static com.codecool.battleship.player.PlayerType.HUMAN;
import static com.codecool.battleship.utils.Constans.HIGH_SCORE;
import static com.codecool.battleship.utils.Constans.HIGH_SCORE_LENGTH;

public class Battleship {
    private Display display;
    private Input input;
    private Game game;

    private Score[] highScore = new Score[HIGH_SCORE_LENGTH];


    public Battleship() {
        display = new Display();
        input = new Input();
    }

    public void printTitle(String title) {
        display.printTitle(title);
    }

    public void mainMenu() {
        int menuIndex = -1;
        while (menuIndex != 0) {
            displayMainMenu();
            String userInput = input.readInput("Select!");
            try {
                menuIndex = Integer.parseInt(userInput);
                loadModule(menuIndex);
            } catch (NumberFormatException e) {
                display.printErrorMessage("'" + userInput + "' is not a number!");
            } catch (IllegalArgumentException e) {
                display.printErrorMessage(e.getMessage());
            }
        }
        display.printGameMessage("Goodbye!");
    }

    private void displayMainMenu() {
        String[] menuElements = {
                "Exit",
                "Start Game",
                "High Score"
        };
        display.printMenu("Main menu", menuElements);
    }

    public void loadModule(int menuPoint) {
        switch (menuPoint) {
            case 1: {
                startGame();
                break;
            }
            case 2: {
                displayHighScore();
                break;
            }
            case 0:
                return;
            default:
                throw new IllegalArgumentException(menuPoint + " is not a valid menu point!");
        }
    }

    public GameMode gameModeMenu() {
        int menuPoint = -1;
        GameMode gameMode = null;
        while (gameMode == null) {
            displayGameModeMenu();
            String userInput = input.readInput("Select!");
            try {
                menuPoint = Integer.parseInt(userInput);
                gameMode = getGameMode(menuPoint);
            } catch (NumberFormatException e) {
                display.printErrorMessage("'" + userInput + "' is not a number!");
            } catch (IllegalArgumentException e) {
                display.printErrorMessage(e.getMessage());
            }
            if (menuPoint == 0) {
                display.printGameMessage("Returning to Main menu...");
                break;
            }
        }
        return gameMode;
    }

    private void displayGameModeMenu() {
        String[] menuElements = {
                "Back to Main Menu",
                "Player vs Player",
                "Player vs AI"
        };
        display.printMenu("game mode", menuElements);
    }

    private GameMode getGameMode(int menuPoint) {
        GameMode gameMode;
        switch (menuPoint) {
            case 1: {
                gameMode = PvP;
                break;
            }
            case 2: {
                gameMode = PvAI;
                break;
            }
            case 0:
                return null;
            default:
                throw new IllegalArgumentException(menuPoint + " is not a valid menu point!");
        }
        return gameMode;
    }

    private ShipPlacement shipPlacementMenu() {
        int menuPoint = -1;
        ShipPlacement shipPlacement = null;
        while (shipPlacement == null) {
            displayShipPlacementMenu();
            String userInput = input.readInput("Select!");
            try {
                menuPoint = Integer.parseInt(userInput);
                shipPlacement = getShipPlacementMode(menuPoint);
            } catch (NumberFormatException e) {
                display.printErrorMessage("'" + userInput + "' is not a number!");
            } catch (IllegalArgumentException e) {
                display.printErrorMessage(e.getMessage());
            }
            if (menuPoint == 0) {
                display.printGameMessage("Returning to Main menu...");
                break;
            }
        }
        return shipPlacement;
    }

    private ShipPlacement getShipPlacementMode(int menuPoint) {
        ShipPlacement shipPlacement;
        switch (menuPoint) {
            case 1: {
                shipPlacement = MANUAL;
                break;
            }
            case 2: {
                shipPlacement = RANDOMIZED;
                break;
            }
            case 0:
                shipPlacement = null;
                break;
            default:
                throw new IllegalArgumentException(menuPoint + " is not a valid menu point!");
        }
        return shipPlacement;
    }

    private void displayShipPlacementMenu() {
        String[] menuElements = {
                "Back to Main menu",
                "Manual",
                "Randomized"
        };
        display.printMenu("Ship placement", menuElements);
    }

    public void startGame() {
        GameMode gameMode = gameModeMenu();
        if (gameMode == null) return;
        ShipPlacement shipPlacement = shipPlacementMenu();
        if (shipPlacement == null) return;
        if (game == null) this.game = new Game(input, display, gameMode, shipPlacement);
        game.play();
        Player winner = game.getCurrentPlayer();
        if (winner.getPlayerType().equals(HUMAN)) {
            if (hasPlayerBeatenTheHighScore(winner)) {
                game.getCurrentPlayer().setName(input.readInput("You beat the Highscore! What's your name?"));
                addScoreToHighScore(new Score(winner.getName(), winner.getPoints()));
                try {
                    BattleshipDAO.writeHighScoreToFile(highScore);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addScoreToHighScore(Score score) {
        highScore = Stream
                .concat(Arrays.stream(highScore), Stream.of(score))
                .sorted((score1, score2) -> ((score2.getValue() - (score1.getValue()))))
                .limit(10)
                .toList()
                .toArray(new Score[0]);

    }

    private boolean hasPlayerBeatenTheHighScore(Player player) {
        return player.getPoints() > highScore[highScore.length - 1].getValue();
    }

    public void displayHighScore() {
        try {
            highScore = BattleshipDAO.readHighScoreFromFile();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (NullPointerException e) {
            display.printErrorMessage(e.getMessage());
            return;
        }
        display.printHighScore(HIGH_SCORE, highScore);
    }

    public Score[] getHighScore() {
        return highScore;
    }

    public void setHighScore(Score[] highScore) {
        this.highScore = highScore;
    }


}
