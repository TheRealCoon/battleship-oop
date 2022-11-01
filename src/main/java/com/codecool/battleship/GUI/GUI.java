package com.codecool.battleship.GUI;

import static com.codecool.battleship.utils.Constants.*;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private JLayeredPane layeredPane;

    public GUI() {

//        layeredPane = initLayeredPane();

        ImageIcon icon = new ImageIcon(ICONS_DIRECTORY + "gameIcon.png");
        ImageIcon icon2 = new ImageIcon(ICONS_DIRECTORY + "menuBackGround.png");

        JLabel label = new JLabel();
        label.setSize(GUI_WIDTH, GUI_HEIGHT);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setIcon(icon2);


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
//        this.setBounds(GUI_STARTING_X, GUI_STARTING_Y, GUI_WIDTH, GUI_HEIGHT);
//        this.setResizable(false);
//        this.setUndecorated(true);
//        this.setOpacity(0.8f);

        this.setTitle(GAME_TITLE);
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(0, 130, 130));

        this.add(label);
//        this.add(layeredPane);
        this.setVisible(true);
    }

    private JLayeredPane initLayeredPane() {
        JLayeredPane layeredPane = new JLayeredPane();

        Menu menuPanel = new Menu();
//        GamePanel gamePanel = new GamePanel();
//        HighScorePanel highScorePanel = new HighScorePanel();

        layeredPane.setBounds(0, 0, GUI_WIDTH, GUI_HEIGHT);
        layeredPane.add(menuPanel, Integer.valueOf(2));
//        layeredPane.add(gamePanel, Integer.valueOf(1));
//        layeredPane.add(highScorePanel, Integer.valueOf(0));;
        layeredPane.setVisible(true);
        return layeredPane;
    }

    @Override
    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

}
