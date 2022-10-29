package com.codecool.battleship;


import com.codecool.battleship.guiMenu.BoardPane;

public class GUI {
    public static void main(String[] args) {
        new DrawLayers().play();
    }
}

class DrawLayers {

    public void play() {
        BoardPane boardPane = new BoardPane(10, 10);
    }
}
