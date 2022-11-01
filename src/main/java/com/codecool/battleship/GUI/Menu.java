package com.codecool.battleship.GUI;

import javax.swing.*;

import static com.codecool.battleship.utils.Constants.*;

public class Menu extends JLayeredPane {
    JPanel mainMenu;
    JPanel gameModeMenu;
    JPanel shipPlacementMenu;
    JLabel lblbackground;

    public Menu() {
//        mainMenu = new JPanel();
//        //TODO find out best layout
//        mainMenu.setLayout(null);
////        mainMenu.setBounds(0,0,1600,800);
////        mainMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
////        mainMenu.setVisible(true);
//        gameModeMenu = new JPanel();
//        //TODO find out best layout
//        gameModeMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
//        shipPlacementMenu = new JPanel();
//        //TODO find out best layout
//        shipPlacementMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));

        ImageIcon backgroundImage = new ImageIcon(ICONS_DIRECTORY + "menuBackground.png");
        lblbackground = new JLabel(backgroundImage);
        lblbackground.setBounds(0,0, GUI_WIDTH, GUI_HEIGHT);


        this.add(lblbackground, Integer.valueOf(0));
//        this.add(shipPlacementMenu, Integer.valueOf(1));
//        this.add(gameModeMenu, Integer.valueOf(2));
//        this.add(mainMenu, Integer.valueOf(3));
        this.setOpaque(true);
        this.setVisible(true);

    }
}
