package com.codecool.battleship.GUI;

import com.codecool.battleship.utils.Constans;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private JLayeredPane layeredPane;

    public GUI() {

        layeredPane = initLayeredPane();


        ImageIcon icon = new ImageIcon(Constans.ICONS_DIRECTORY + "gameIcon.png");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(100, 100, 1600, 800);
        this.setResizable(false);
        this.setTitle(Constans.GAME_TITLE);
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(0,130,130));

        this.add(layeredPane);
        this.setVisible(true);
    }

    private JLayeredPane initLayeredPane() {
        MainMenu mainMainMenuPanel = new MainMenu();
        GamePanel gamePanel = new GamePanel();
        HighScorePanel highScorePanel = new HighScorePanel();

        layeredPane.add(mainMainMenuPanel, Integer.valueOf(2));
        layeredPane.add(gamePanel, Integer.valueOf(1));
        layeredPane.add(highScorePanel, Integer.valueOf(0));

        return layeredPane;
    }

    @Override
    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

}
