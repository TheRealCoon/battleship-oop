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
        //TODO
    }

    public void printTitle(String title){
        display.printTitle(title);
    }
}
