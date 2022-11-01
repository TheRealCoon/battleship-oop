package com.codecool.battleship.GUI;

import static com.codecool.battleship.utils.Constans.*;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private JLayeredPane layeredPane;

    public GUI() {

        layeredPane = initLayeredPane();


        ImageIcon icon = new ImageIcon(ICONS_DIRECTORY + "gameIcon.png");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(GUI_STARTING_X, GUI_STARTING_Y, GUI_WIDTH, GUI_HEIGHT);
        this.setResizable(false);
        this.setTitle(GAME_TITLE);
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(0, 130, 130));

        this.add(layeredPane);
        this.setVisible(true);
    }

    private JLayeredPane initLayeredPane() {
        JLayeredPane layeredPane = new JLayeredPane();

        Menu menuPanel = new Menu();
        GamePanel gamePanel = new GamePanel();
        HighScorePanel highScorePanel = new HighScorePanel();

        layeredPane.setBounds(0, 0, GUI_WIDTH, GUI_HEIGHT);
        layeredPane.add(menuPanel, Integer.valueOf(2));
        layeredPane.add(gamePanel, Integer.valueOf(1));
        layeredPane.add(highScorePanel, Integer.valueOf(0));
        return layeredPane;
    }

    @Override
    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

}
