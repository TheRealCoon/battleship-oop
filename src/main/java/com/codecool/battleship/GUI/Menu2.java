package com.codecool.battleship.GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.codecool.battleship.utils.Constans.ICONS_DIRECTORY;


public class Menu2 extends JLayeredPane {

    private String menuTitle;
    BoardPane boardPane;
    JLabel lblMenuContainer;

    Menu2(BoardPane boardPane) {
        this.boardPane = boardPane;
        this.setBounds(0, 0, 1600, 800);

        lblMenuContainer = new JLabel();
        lblMenuContainer.setBounds(280, 200, 800, 400);
        lblMenuContainer.setVisible(true);
        lblMenuContainer.setOpaque(false);
        //this is the backGround
        ImageIcon stripeIMG = new ImageIcon(ICONS_DIRECTORY + "BattleshipStripe.png");
        JLabel lblBattleshipImg = new JLabel(stripeIMG);
        lblBattleshipImg.setBounds(0, 0, 800, 140);
        lblBattleshipImg.setVisible(true);
        lblMenuContainer.add(lblBattleshipImg, Integer.valueOf(1));

        JLabel lblMenuTitle = new JLabel(menuTitle);
        lblMenuTitle.setBounds(100, 135, 567, 40);
        lblMenuTitle.setLayout(null);
        lblMenuTitle.setFont(new Font("Lucida", Font.BOLD, 37));
        lblMenuTitle.setForeground(Color.WHITE);
        lblMenuTitle.setBackground(Color.BLACK);
        lblMenuTitle.setOpaque(true);

        int buttonsXPos = 255;
        //TODO these need to go to Display.printmenu()
        ImageIcon gameStartIcon = new ImageIcon(ICONS_DIRECTORY + "gameStart.png");
        JButton btnStartGame = new JButton("New Game", gameStartIcon);
        btnStartGame.setBounds(buttonsXPos, 160, 300, 42);
        btnStartGame.setFont(new Font("Lucida", Font.BOLD, 30));
        btnStartGame.setMargin(new Insets(2, 2, 2, 2));

        ImageIcon trophyIcon = new ImageIcon(ICONS_DIRECTORY + "trophy.png");
        JButton btnHighScore = new JButton("High Score", trophyIcon);
        btnHighScore.setBounds(buttonsXPos, 220, 300, 42);
        btnHighScore.setFont(new Font("Lucida", Font.BOLD, 30));
        btnHighScore.setMargin(new Insets(2, 2, 2, 2));

        ImageIcon exitIcon = new ImageIcon(ICONS_DIRECTORY + "exit.png");
        JButton btnExit = new JButton("Exit", exitIcon);
        btnExit.setBounds(buttonsXPos, 280, 300, 42);
        btnExit.setFont(new Font("Lucida", Font.BOLD, 30));
        btnExit.setMargin(new Insets(2, 2, 2, 2));

        lblMenuContainer.add(btnStartGame, Integer.valueOf(1));
        lblMenuContainer.add(btnHighScore, Integer.valueOf(1));
        lblMenuContainer.add(btnExit, Integer.valueOf(1));

        this.add(lblMenuContainer);
        //TODO idáig

        //TODO ezeket is a display kiaratásakor kell beállítani, vagy külön létrehozni ezeket a gombokat valahol tuti meg kell változtatni
        btnStartGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                boardPane.getGameModeMenu().setVisible(true);
//                display.getGameModeMenu().buttonContainer.setVisible(true);
                boardPane.getMainMenu().setVisible(false);
            }
        });

        btnHighScore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boardPane.getMainMenu().setVisible(false);
                boardPane.getHighScore().setVisible(true);
            }
        });

        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

//        ImageIcon MainMenuIMG= new ImageIcon(ICONS_DIRECTORY + "highcScoreBackground2.png");
//        JLabel MainMenuBackground= new JLabel(MainMenuIMG);
//        MainMenuBackground.setBounds(0,0,1600,800);
//        MainMenuBackground.setLayout(null);
//        MainMenuBackground.setVisible(true);
//        add(MainMenuBackground, Integer.valueOf(0));

        ImageIcon GameMenuIMG = new ImageIcon(ICONS_DIRECTORY + "GamemodeMenu.png");
        JLabel GameMenuBackground = new JLabel(GameMenuIMG);
        GameMenuBackground.setBounds(0, 0, 1600, 800);
        this.add(GameMenuBackground, Integer.valueOf(0));


    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }
}
