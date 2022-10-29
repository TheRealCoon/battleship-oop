package com.codecool.battleship.guiMenu;
import com.codecool.battleship.guiMenu.BoardPane;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainMenu extends JLayeredPane {

    BoardPane display;
    JLabel mainMenuContainer;

    MainMenu(BoardPane display) {
        this.display = display;
        this.setBounds(0, 0, 1600, 800);

        mainMenuContainer = new JLabel();
        mainMenuContainer.setBounds(280, 200, 800, 400);
        mainMenuContainer.setVisible(true);
        mainMenuContainer.setOpaque(false);


        ImageIcon stripeIMG = new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents/BattleshipStripe.png");
        JLabel stripeBattleship = new JLabel(stripeIMG);
        stripeBattleship.setBounds(0, 0, 800, 140);
        stripeBattleship.setVisible(true);
        mainMenuContainer.add(stripeBattleship, Integer.valueOf(1));
        int buttonsXPos = 255;
        ImageIcon gameStart = new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents/gameStart.png");
        JButton buttonStartGame = new JButton("New Game", gameStart);
        buttonStartGame.setBounds(buttonsXPos, 160, 300, 42);
        buttonStartGame.setFont(new Font("Lucida", Font.BOLD, 30));
        buttonStartGame.setMargin(new Insets(2, 2, 2, 2));

        ImageIcon trophy = new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents/trophy.png");
        JButton buttonHighScore = new JButton("High Score", trophy);
        buttonHighScore.setBounds(buttonsXPos, 220, 300, 42);
        buttonHighScore.setFont(new Font("Lucida", Font.BOLD, 30));
        buttonHighScore.setMargin(new Insets(2, 2, 2, 2));

        ImageIcon exit = new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents/exit.png");
        JButton buttonExit = new JButton("Exit", exit);
        buttonExit.setBounds(buttonsXPos, 280, 300, 42);
        buttonExit.setFont(new Font("Lucida", Font.BOLD, 30));
        buttonExit.setMargin(new Insets(2, 2, 2, 2));

        mainMenuContainer.add(buttonStartGame, Integer.valueOf(1));
        mainMenuContainer.add(buttonHighScore, Integer.valueOf(1));
        mainMenuContainer.add(buttonExit, Integer.valueOf(1));

        this.add(mainMenuContainer);

        buttonStartGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.getGameModeMenu().setVisible(true);
                display.getGameModeMenu().buttonContainer.setVisible(true);
                display.getMainMenu().setVisible(false);
            }
        });

        buttonHighScore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.getMainMenu().setVisible(false);
                display.getHighScore().setVisible(true);
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

//        ImageIcon MainMenuIMG= new ImageIcon("src/main/java/org/example/iconsOfComponents/highcScoreBackground2.png");
//        JLabel MainMenuBackground= new JLabel(MainMenuIMG);
//        MainMenuBackground.setBounds(0,0,1600,800);
//        MainMenuBackground.setLayout(null);
//        MainMenuBackground.setVisible(true);
//        add(MainMenuBackground, Integer.valueOf(0));

        ImageIcon GameMenuIMG = new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents/GamemodeMenu.png");
        JLabel GameMenuBackground = new JLabel(GameMenuIMG);
        GameMenuBackground.setBounds(0, 0, 1600, 800);
        this.add(GameMenuBackground, Integer.valueOf(0));


    }

}
