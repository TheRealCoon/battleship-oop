package com.codecool.battleship;

import com.codecool.battleship.dao.BattleshipDAO;
import com.codecool.battleship.player.Score;
import com.codecool.battleship.utils.Display;
import com.codecool.battleship.utils.Input;

import java.io.IOException;

import static com.codecool.battleship.utils.Constans.HIGH_SCORE;
import static com.codecool.battleship.utils.Constans.HIGH_SCORE_LENGTH;

public class Battleship {
    private Display display;
    private Input input;

    private Score[] highScore = new Score[HIGH_SCORE_LENGTH];


    public Battleship() {
        display = new Display();
        input = new Input();
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

    public void startGame() {
        //TODO
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

    public void printTitle(String title) {
        display.printTitle(title);
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

    public Score[] getHighScore() {
        return highScore;
    }

    public void setHighScore(Score[] highScore) {
        this.highScore = highScore;
    }
}
