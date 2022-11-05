package com.codecool.battleship.GUI;

import static com.codecool.battleship.utils.Constants.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GUI extends JFrame {
    private JLayeredPane layeredPane;

    public GUI() {

        layeredPane = initLayeredPane();

        ImageIcon icon = new ImageIcon(ICONS_DIRECTORY + "gameIcon.png");
//        ImageIcon icon2 = new ImageIcon(ICONS_DIRECTORY + "menuBackGround.png");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(GUI_WIDTH, GUI_HEIGHT);
//      setResizable(false);
        setTitle(GAME_TITLE);
        setIconImage(icon.getImage());
        getContentPane().setBackground(new Color(0, 130, 130));


        getContentPane().add(layeredPane, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLayeredPane initLayeredPane() {


        Menu menuPanel = new Menu();
//        GamePanel gamePanel = new GamePanel();
//        HighScorePanel highScorePanel = new HighScorePanel();

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(new BorderLayout());
//        layeredPane.setPreferredSize(this.getSize());
//        layeredPane.setLayout(null);
//        layeredPane.setBounds(0, 0, GUI_WIDTH, GUI_HEIGHT);
//        layeredPane.setOpaque(true);
//        layeredPane.setVisible(true);

        layeredPane.add(menuPanel, BorderLayout.CENTER);
        layeredPane.setLayer(menuPanel, JLayeredPane.DRAG_LAYER);

//        layeredPane.add(gamePanel, Integer.valueOf(1));
//        layeredPane.add(highScorePanel, Integer.valueOf(0));;
//        layeredPane.setVisible(true);
        Border border = BorderFactory.createLineBorder(Color.red, 3);
        layeredPane.setBorder(border);
        return layeredPane;
    }

    @Override
    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

}
