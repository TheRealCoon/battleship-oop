package com.codecool.battleship;

import com.codecool.battleship.utils.Display;
import com.codecool.battleship.utils.Input;

public class Battleship {
    private Display display;
    private Input input;
    //TODO List<Score> for high score (Score is a PlayerName - Integer pair)

    public Battleship() {
        display = new Display();
        input = new Input();
    }

    public void mainMenu(){
        int menuIndex = -1;
        while (menuIndex != 0) {
            displayMainMenu();
            String userInput = input.readInput("Select!");
            try {
                menuIndex = Integer.parseInt(userInput);
                loadModule(menuIndex);
            }catch(NumberFormatException e){
                System.out.println("'" + userInput + "' is not a number!");
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        display.printGameMessage("Goodbye!");
    }

    private void displayMainMenu(){
        String[] menuElements = {
                "Exit",
                "Start Game",
                "High Score"
        };
        display.printMenu("Main menu: ", menuElements);
    }

    public void startGame(){
        //TODO
    }

    public void displayHighScore(){
        //TODO we need to save scores in a file, and read it from there
    }

    public void printTitle(String title){
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

}
