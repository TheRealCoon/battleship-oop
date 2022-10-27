package com.codecool.battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameModeMenu extends JLayeredPane {

    BoardPane display;
    JLabel buttonContainer;
    JRadioButton PvsP, PvsAI, randomPlacement, manualPlacement;


    GameModeMenu(BoardPane display) {
        this.display = display;
        this.setBounds(0, 0, 1600, 800);

        buttonContainer = new JLabel();
        buttonContainer.setBounds(280, 200, 800, 400);
        buttonContainer.setVisible(false);
        buttonContainer.setOpaque(false);

        int width = 310;
        int fontSize = 24;

        ImageIcon stripeIMG = new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents/BattleshipStripe.png");
        JLabel stripeBattleship = new JLabel(stripeIMG);
        stripeBattleship.setBounds(0, 0, 800, 140);
        stripeBattleship.setVisible(true);
        JLabel gameModeStripe = new JLabel(" C H O O S E    G A M E M O D E");
        gameModeStripe.setBounds(100, 135, 567, 40);
        gameModeStripe.setLayout(null);
        gameModeStripe.setFont(new Font("Lucida", Font.BOLD, 37));
        gameModeStripe.setForeground(Color.WHITE);
        gameModeStripe.setBackground(Color.BLACK);
        gameModeStripe.setOpaque(true);
        PvsP = new JRadioButton("Player vs Player");
        PvsP.setBounds(100, 200, width, 30);
        PvsP.setFont(new Font("Lucida", Font.BOLD, fontSize));
        PvsP.setOpaque(false);
        PvsP.setForeground(Color.WHITE);
        PvsAI = new JRadioButton("Player vs AI");
        PvsAI.setBounds(410, 200, width, 30);
        PvsAI.setFont(new Font("Lucida", Font.BOLD, fontSize));
        PvsAI.setOpaque(false);
        PvsAI.setForeground(Color.WHITE);
        randomPlacement = new JRadioButton("Random Placement");
        randomPlacement.setBounds(100, 250, width, 30);
        randomPlacement.setFont(new Font("Lucida", Font.BOLD, fontSize));
        randomPlacement.setOpaque(false);
        randomPlacement.setForeground(Color.WHITE);
        manualPlacement = new JRadioButton("Manual Placement");
        manualPlacement.setBounds(410, 250, width, 30);
        manualPlacement.setFont(new Font("Lucida", Font.BOLD, fontSize));
        manualPlacement.setOpaque(false);
        manualPlacement.setForeground(Color.WHITE);
        JButton chooseGameMod = new JButton("Start Game");
        chooseGameMod.setBounds(110, 300, 150, 30);
        chooseGameMod.setFont(new Font("Lucida", Font.BOLD, 20));
        chooseGameMod.setMargin(new Insets(2, 2, 2, 2));

        buttonContainer.add(stripeBattleship,Integer.valueOf(1));
        buttonContainer.add(gameModeStripe, Integer.valueOf(1));
        buttonContainer.add(PvsP, Integer.valueOf(1));
        buttonContainer.add(PvsAI, Integer.valueOf(1));
        buttonContainer.add(randomPlacement, Integer.valueOf(1));
        buttonContainer.add(manualPlacement, Integer.valueOf(1));
        buttonContainer.add(chooseGameMod, Integer.valueOf(1));
        add(buttonContainer);

        chooseGameMod.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if((getPvsP().isSelected() ^ getPvsAI().isSelected()) && (getManualPlacement().isSelected() ^ getRandomPlacement().isSelected()))
                {
                    display.getGameModeMenu().setVisible(false);
                    display.getGameBoard().setVisible(true);
                    display.getFieldMove().requestFocus();
                }else{
                    System.out.println("Bad selection !!");
                }
                display.getGameModeMenu().getPvsP().setSelected(false);
                display.getGameModeMenu().getPvsAI().setSelected(false);
                display.getGameModeMenu().getRandomPlacement().setSelected(false);
                display.getGameModeMenu().getManualPlacement().setSelected(false);
            }
        });

        ImageIcon GameMenuIMG = new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents/GamemodeMenu.png");
        JLabel GameMenuBackground = new JLabel(GameMenuIMG);
        GameMenuBackground.setBounds(0, 0, 1600, 800);
        this.add(GameMenuBackground, Integer.valueOf(0));

    }

    public JLabel getButtonContainer() {
        return buttonContainer;
    }

    public JRadioButton getPvsP() {
        return PvsP;
    }

    public JRadioButton getPvsAI() {
        return PvsAI;
    }

    public JRadioButton getRandomPlacement() {
        return randomPlacement;
    }

    public JRadioButton getManualPlacement() {
        return manualPlacement;
    }

}
