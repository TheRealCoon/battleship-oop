package com.codecool.battleship.GUI;


import com.codecool.battleship.utils.display.Display;
import com.codecool.battleship.utils.input.Input;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

import static com.codecool.battleship.utils.Constans.*;

public class BoardPane extends JFrame {
    private Menu2 menu2;
    private GameBoard gameBoard;
    private HighScore highScore;

    private JLabel labelShip2, labelMissileToLeft;
    private Input input;
    private Display display;

    public BoardPane(Input input, Display display) {
        this.input = input;
        this.display = display;
        setTitle(GAME_TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0, 0, 1600, 800);
        setLayout(null);
        setVisible(true);
        gameBoard = new GameBoard();

        highScore = new HighScore(this);
        this.add(highScore);

        menu2 = new Menu2(this);
        this.add(menu2);

        highScore.setVisible(false);
        menu2.setVisible(true);

        this.add(gameBoard);
        this.update(this.getGraphics());

        labelMissileToLeft = new JLabel(new ImageIcon(ICONS_DIRECTORY + "missile.png"));
        labelMissileToLeft.setBounds(820, 300, 80, 60);
        labelMissileToLeft.setVisible(false);
        labelMissileToLeft.setOpaque(false);
        this.gameBoard.add(labelMissileToLeft, Integer.valueOf(2));

        ImageIcon shipImg = new ImageIcon(ICONS_DIRECTORY + "cruiserSmallVertical.png");
        JLabel labelShip = new JLabel(shipImg);
        labelShip.setBounds(810, 300, shipImg.getIconWidth(), shipImg.getIconHeight());
        labelShip.setVisible(true);
        labelShip.setOpaque(false);
        this.gameBoard.add(labelShip, Integer.valueOf(2));

        labelShip2 = new JLabel(shipImg);
        labelShip2.setBounds(645, 300, shipImg.getIconWidth(), shipImg.getIconHeight());
        labelShip2.setVisible(true);
        labelShip2.setOpaque(false);
        this.gameBoard.add(labelShip2, Integer.valueOf(2));

        JLabel player1 = new JLabel("  Player 1");
        player1.setBounds(210, 695, 255, 60);
        player1.setFont(new Font("Lucida", Font.BOLD, 26));
        player1.setLayout(null);
        player1.setBackground(Color.cyan);
        player1.setForeground(Color.WHITE);
        player1.setOpaque(true);
        player1.setVisible(true);
        this.gameBoard.add(player1, Integer.valueOf(1));

        JLabel player2 = new JLabel("  Player 2");
        player2.setBounds(1090, 695, 255, 60);
        player2.setFont(new Font("Lucida", Font.BOLD, 26));
        player2.setLayout(null);
        player2.setBackground(Color.cyan);
        player2.setForeground(Color.WHITE);
        player2.setOpaque(true);
        player2.setVisible(false);
        this.gameBoard.add(player2, Integer.valueOf(1));
//
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                fieldMove.requestFocus();
//            }
//        });
//        Action action = new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                makeMove(fieldMove.getX());
//                fieldMove.setText("");
//                if (fieldMove.getX() < GUI_PLAYER_INPUT_SWITCH) {
//                    fieldMove.setLocation(1240, 700);
//                    player1.setVisible(false);
//                    player2.setVisible(true);
//                } else {
//                    fieldMove.setLocation(360, 700);
//                    player2.setVisible(false);
//                    player1.setVisible(true);
//                }
//            }
//
//            private void makeMove(int side) {
//                int[] putPosition = input.guiInputToPositionInPixel(getFieldMove().getText());
//                ImageIcon missedHitIMG = new ImageIcon(ICONS_DIRECTORY + "blown-up.png");
//                JLabel missedHit = new JLabel(missedHitIMG);
//                missedHit.setBounds(putPosition[0], putPosition[1], FIELD_SIZE_IN_PIXEL, FIELD_SIZE_IN_PIXEL);
//                missedHit.setVisible(true);
//                if (side < GUI_PLAYER_INPUT_SWITCH) {
//                    getPlayer1Board().add(missedHit);
//                    getPlayer1Board().add(missedHit, Integer.valueOf(5));
//                } else {
//                    getPlayer2Board().add(missedHit);
//                    getPlayer2Board().add(missedHit, Integer.valueOf(5));
//                }
//            }
//        };
//        fieldMove.addActionListener(action);
//
//        this.update(this.getGraphics());
//        System.out.println("after graphics");
    }


    public JLayeredPane getGameBoard() {
        return gameBoard;
    }

    public Menu2 getMainMenu() {
        return menu2;
    }

    public HighScore getHighScore() {
        return highScore;
    }


    public JLabel getLabelShip2() {
        return labelShip2;
    }

    public JLabel getLabelMissileToLeft() {
        return labelMissileToLeft;
    }

}

class Shelling extends JFrame implements Runnable {
    BoardPane boardPane;

    Shelling(BoardPane boardPane) {
        this.boardPane = boardPane;
    }

    static void delayExec(int howLong) {
        try {
            TimeUnit.MILLISECONDS.sleep(howLong);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int destinationX = boardPane.getLabelShip2().getX() + 35;

        for (int i = boardPane.getLabelMissileToLeft().getX(); i >= destinationX; i--) {
            delayExec(100);
            boardPane.getLabelMissileToLeft().setLocation(i, 450);
            boardPane.getLabelMissileToLeft().setVisible(true);
        }
        boardPane.getLabelMissileToLeft().setVisible(false);
    }
}
