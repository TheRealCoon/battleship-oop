package com.codecool.battleship.GUI;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

import static com.codecool.battleship.utils.Constants.*;

public class Menu extends JLayeredPane {
    JPanel mainMenu;
    JPanel gameModeMenu;
    JPanel shipPlacementMenu;
    JLabel lblBackground;

    public Menu() {
        mainMenu = new JPanel();
        //TODO find out best layout
        mainMenu.setLayout(new GridLayout(1, 3, 10, 0));
//        mainMenu.setLayout(null);
//        mainMenu.setBounds(500,0,600,800);
//       mainMenu.setSize(600,800);
//        mainMenu.setOpaque(false);
//        mainMenu.setVisible(true);
        JLabel fillerLabel = new JLabel();
        fillerLabel.setSize(490, 800);
        mainMenu.add(fillerLabel);
        JLabel sampleLabel = new JLabel("valami");
        sampleLabel.setFont(new Font("Arial", Font.BOLD, 70));
        sampleLabel.setForeground(Color.YELLOW);
        sampleLabel.setOpaque(false);
        mainMenu.add(sampleLabel);
        mainMenu.add(fillerLabel);
//        gameModeMenu = new JPanel();
//        //TODO find out best layout
//        gameModeMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
//        shipPlacementMenu = new JPanel();
//        //TODO find out best layout
//        shipPlacementMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));

        ImageIcon backgroundImage = new ImageIcon(ICONS_DIRECTORY + "menuBackground.png");
        lblBackground = new JLabel(backgroundImage);
        lblBackground.setLayout(new BorderLayout());
//        lblBackground.setSize(GUI_WIDTH, GUI_HEIGHT);
        lblBackground.setVerticalAlignment(JLabel.CENTER);
        lblBackground.setHorizontalAlignment(JLabel.CENTER);


        this.setLayout(new BorderLayout());
//        this.setPreferredSize(new Dimension(GUI_WIDTH, GUI_HEIGHT));
        this.add(lblBackground, BorderLayout.CENTER);
        this.setLayer(lblBackground, JLayeredPane.DEFAULT_LAYER);
//        this.add(shipPlacementMenu, Integer.valueOf(1));
//        this.add(gameModeMenu, Integer.valueOf(2));
        this.add(mainMenu);
        this.setLayer(mainMenu, JLayeredPane.DRAG_LAYER);


    }
}
