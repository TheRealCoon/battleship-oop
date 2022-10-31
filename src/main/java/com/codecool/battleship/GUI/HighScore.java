package com.codecool.battleship.GUI;

import com.codecool.battleship.dao.BattleshipDAO;
import com.codecool.battleship.player.Score;
import com.codecool.battleship.utils.display.Display;
import com.codecool.battleship.utils.display.GraphicDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static com.codecool.battleship.utils.Constans.ICONS_DIRECTORY;

public class HighScore extends JLayeredPane {
    JLabel highScoreLabel;
    Score[] highScore;
    Display errorMsg = new GraphicDisplay(); //TODO try to pass this to the constructor

    HighScore(BoardPane display) {
        setBounds(0, 0, 1600, 800);
        setBackground(Color.YELLOW);
        setVisible(false);
        setLayout(null);

        highScoreLabel = new JLabel();
        highScoreLabel.setBounds(200, 0, 1600, 800);
        highScoreLabel.setFont(new Font("Lucida", Font.BOLD, 30));
        highScoreLabel.setForeground(Color.WHITE);
        try {
            highScore = BattleshipDAO.readHighScoreFromFile();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (NullPointerException e) {
            errorMsg.printErrorMessage(e.getMessage());
            return;
        }

        int row = 0;
        StringBuilder highScoreTable = new StringBuilder();
        highScoreTable.append("<html><table><tr><td style='width:50px'>Place</td><td " +
                "style='width:210px'>Name</td><td " +
                "style='width:50px'>Points</td></tr>");
        for (Score field : highScore
        ) {
            row++;
            highScoreTable.append("<tr><td>");
            highScoreTable.append(row);
            highScoreTable.append("</td><td>");
            highScoreTable.append(field.getPlayerName().replaceAll("([^A-z ])", ""));
            highScoreTable.append("</td><td>");
            highScoreTable.append(field.getValue());
            highScoreTable.append("</td></tr>");
        }
        highScoreTable.append("</table></html>");
        highScoreLabel.setText(highScoreTable.toString());
        add(highScoreLabel, Integer.valueOf(2));
        highScoreLabel.setVisible(true);

        ImageIcon highScoreBackgroundIMG = new ImageIcon(ICONS_DIRECTORY + "HSBackground.png");
        JLabel backGround = new JLabel(highScoreBackgroundIMG);
        backGround.setBounds(0, 0, 1600, 800);
        backGround.setLayout(null);
        backGround.setVisible(true);
        add(backGround, Integer.valueOf(1));

        JButton buttonMainMenu = new JButton("Main menu");
        buttonMainMenu.setBounds(260, 670, 300, 42);
        buttonMainMenu.setFont(new Font("Lucida", Font.BOLD, 30));
        buttonMainMenu.setMargin(new Insets(2, 2, 2, 2));
        buttonMainMenu.setVisible(true);
        this.add(buttonMainMenu, Integer.valueOf(2));

        buttonMainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.getHighScore().setVisible(false);
                display.getMainMenu().setVisible(true);
            }
        });
    }
}
