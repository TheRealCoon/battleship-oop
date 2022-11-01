package com.codecool.battleship.GUI;

import com.codecool.battleship.utils.Constans;
import com.codecool.battleship.utils.input.ConsoleInput;

import javax.swing.*;
import java.awt.*;

import static com.codecool.battleship.utils.Constans.ICONS_DIRECTORY;

public class Menu extends JLayeredPane {
    JPanel mainMenu;
    JPanel gameModeMenu;
    JPanel shipPlacementMenu;
    JLabel backGround;

    public Menu() {
        mainMenu = new JPanel();
        //TODO find out best layout
        mainMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        gameModeMenu = new JPanel();
        //TODO find out best layout
        gameModeMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        shipPlacementMenu = new JPanel();
        //TODO find out best layout
        shipPlacementMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        ImageIcon backGroundImage = new ImageIcon(ICONS_DIRECTORY + "");
        backGround = new JLabel(backGroundImage);

    }
}
