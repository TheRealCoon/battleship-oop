package com.codecool.battleship.GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static com.codecool.battleship.utils.Constants.BOARD_SIZE;
import static com.codecool.battleship.utils.Constants.ICONS_DIRECTORY;

public class GameBoard extends JLayeredPane {
    public GameBoard() {
        initBoard();
    }

    private void initBoard() {
        setBounds(0, 0, 1600, 800);

        JLabel backGround = new JLabel(new ImageIcon(ICONS_DIRECTORY + "ocean.jpg"));
        backGround.setBounds(0, 0, 1600, 800);
        backGround.setLayout(null);
        backGround.setVisible(true);

        JTextField fieldMove = new JTextField(10);
        fieldMove.setBounds(360, 700, 100, 50);
        fieldMove.setFont(new Font("Arial", Font.BOLD, 40));
        fieldMove.setHorizontalAlignment(JTextField.CENTER);

        JLayeredPane player1Board = new JLayeredPane();
        player1Board.setBounds(0, 0, 660, 660);
        player1Board.setVisible(true);
        player1Board.setLayout(null);

        JLayeredPane player2Board = new JLayeredPane();
        player2Board.setBounds(870, 0, 660, 660);
        player2Board.setVisible(true);
        player2Board.setLayout(null);

        Border whiteBorder = BorderFactory.createLineBorder(Color.white, 1);
        JLabel label, label1;
        for (int i = 0; i <= BOARD_SIZE; i++) {
            for (int j = 0; j <= BOARD_SIZE; j++) {
                if (i == 0 && j != 0) {
                    label = new JLabel(Character.toString(j + 64), SwingConstants.CENTER); //64 = 'A'-1
                    label.setFont(new Font("Arial", Font.BOLD, 40));
                    label1 = new JLabel(Character.toString(j + 64), SwingConstants.CENTER);
                    label1.setFont(new Font("Arial", Font.BOLD, 40));
                } else {
                    if (j == 0 && i != 0) {
                        label = new JLabel(String.valueOf(i), SwingConstants.CENTER);
                        label.setFont(new Font("Arial", Font.BOLD, 40));
                        label1 = new JLabel(String.valueOf(i), SwingConstants.CENTER);
                        label1.setFont(new Font("Arial", Font.BOLD, 40));
                    } else {
                        label = new JLabel();
                        label1 = new JLabel();
                    }
                    label.setBounds(j * 60, i * 60, 60, 60);
                    label.setBackground(Color.lightGray);
                    label.setBorder(whiteBorder);
                    label.setOpaque(true);
                    label1.setBounds(j * 60, i * 60, 60, 60);
                    label1.setBackground(Color.lightGray);
                    label1.setBorder(whiteBorder);
                    label1.setOpaque(true);
                    drawSquaresOnPlayersBoard(label, label1, player1Board, player2Board);
                }
            }
        }



        this.add(backGround, Integer.valueOf(0));
        this.add(fieldMove, Integer.valueOf(1));
        this.add(player1Board, Integer.valueOf(1));
        this.add(player2Board, Integer.valueOf(1));

    }


    public void drawSquaresOnPlayersBoard(JLabel label, JLabel label1, JLayeredPane player1Board, JLayeredPane player2Board) {
        player1Board.add(label, Integer.valueOf(1));
        player2Board.add(label1, Integer.valueOf(1));
    }
}
