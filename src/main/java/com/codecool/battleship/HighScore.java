package com.codecool.battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighScore extends JLayeredPane {
    JLabel highScoreLabel;

    HighScore(BoardPane display) {
        setBounds(0, 0, 1600, 800);
        setBackground(Color.YELLOW);
        setVisible(false);
        setLayout(null);

        highScoreLabel= new JLabel();
        highScoreLabel.setBounds(200,-150,1600,800);
        highScoreLabel.setFont(new Font("Lucida", Font.BOLD, 30));
        highScoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setText("<html><table>" +
                "<tr><td style='width:50px'>N. </td><td style='width:150px'>Name</td><td style='width:50px'>   " +
                "Points</td></tr>" +
                "<tr><td>1.</td><td> Juci</td><td>   56</td></tr>" +
                "<tr><td>2.</td><td> Sanyi </td><td> 45</td></tr>" +
                "<tr><td>3.</td><td> Dani </td><td> 33</td></tr>" +
                "<tr><td>4.</td><td> Attila </td><td>25</td></tr> </table></html>");
        add(highScoreLabel, Integer.valueOf(2));
        highScoreLabel.setVisible(true);

        ImageIcon highScoreBackgroundIMG= new ImageIcon("src/main/java/org/example/iconsOfComponents/HSBackground.png");
        JLabel backGround= new JLabel(highScoreBackgroundIMG);
        backGround.setBounds(0,0,1600,800);
        backGround.setLayout(null);
        backGround.setVisible(true);
        add(backGround, Integer.valueOf(1));

        JButton buttonMainMenu = new JButton("Main menu");
        buttonMainMenu.setBounds(260, 670, 300, 42);
        buttonMainMenu.setFont(new Font("Lucida", Font.BOLD, 30));
        buttonMainMenu.setMargin(new Insets(2,2,2,2));
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
