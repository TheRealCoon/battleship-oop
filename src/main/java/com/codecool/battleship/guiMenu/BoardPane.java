
package com.codecool.battleship.guiMenu;


import com.codecool.battleship.utils.input.GraphicInput;
import com.codecool.battleship.utils.input.Input;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;

import static com.codecool.battleship.utils.Constans.*;

public class BoardPane extends JFrame {
    private JLayeredPane gameBoard;
    private JTextField fieldMove;
    private MainMenu mainMenu;
    private HighScore highScore;
    private JLayeredPane player1Board, player2Board;
    private GameModeMenu gameModeMenu;
    private JLabel labelShip2, labelMissileToLeft;
    private Input input;

    public BoardPane(int dimX, int dimY) {

        input = new GraphicInput();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setBounds(0, 0, 1600, 800);
        this.setLayout(null);
        this.setVisible(true);
        gameBoard = new JLayeredPane();
        gameBoard.setBounds(0, 0, 1600, 800);
        gameBoard.setVisible(false);


        highScore = new HighScore(this);
        this.add(highScore);

        mainMenu = new MainMenu(this);
        this.add(mainMenu);

        gameModeMenu = new GameModeMenu(this);
        this.add(gameModeMenu);

        Border whiteBorder = BorderFactory.createLineBorder(Color.white, 1);

        player1Board = new JLayeredPane();
        player1Board.setBounds(0, 0, 660, 660);
        player1Board.setVisible(true);
        player1Board.setLayout(null);

        player2Board = new JLayeredPane();
        player2Board.setBounds(870, 0, 660, 660);
        player2Board.setVisible(true);
        player2Board.setLayout(null);

        JLabel label, label1;
        for (int i = 0; i <= dimX; i++) {
            for (int j = 0; j <= dimY; j++) {
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

        JLabel backGround = new JLabel(new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents/ocean.jpg"));
        backGround.setBounds(0, 0, 1600, 800);
        backGround.setLayout(null);
        backGround.setVisible(true);

        fieldMove = new JTextField(10);
        fieldMove.setBounds(360, 700, 100, 50);
        fieldMove.setFont(new Font("Arial", Font.BOLD, 40));
        fieldMove.setHorizontalAlignment(JTextField.CENTER);

        gameBoard.add(fieldMove, Integer.valueOf(1));
        gameBoard.add(player1Board, Integer.valueOf(1));
        gameBoard.add(player2Board, Integer.valueOf(1));
        this.add(gameBoard);

        gameBoard.add(backGround, Integer.valueOf(0));

        this.update(this.getGraphics());

        JButton buttonMainMenu = new JButton("Main menu");
        buttonMainMenu.setBounds(620, 710, 300, 42);
        buttonMainMenu.setFont(new Font("Lucida", Font.BOLD, 30));
        buttonMainMenu.setMargin(new Insets(2, 2, 2, 2));
        buttonMainMenu.setVisible(true);
        gameBoard.add(buttonMainMenu, Integer.valueOf(1));

        buttonMainMenu.addActionListener(e -> {
            gameBoard.setVisible(false);
            mainMenu.setVisible(true);
        });

//        JLabel labelX = new JLabel(new ImageIcon("src/main/java/org/example/iconsOfComponents/x.png"));
//        labelX.setBounds(300, 300, 60, 60);
//        labelX.setVisible(true);
//        player1Board.add(labelX, Integer.valueOf(2));
//
//        JLabel label2X = new JLabel(new ImageIcon("src/main/java/org/example/iconsOfComponents/x.png"));
//        label2X.setBounds(300, 300, 60, 60);
//        label2X.setVisible(true);
//        label2X.setOpaque(true);
//        label2X.setBackground(Color.GREEN);
//        player2Board.add(label2X, Integer.valueOf(2));

        labelMissileToLeft = new JLabel(new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents/missile.png"));
        labelMissileToLeft.setBounds(820, 300, 80, 60);
        labelMissileToLeft.setVisible(false);
        labelMissileToLeft.setOpaque(false);
        this.gameBoard.add(labelMissileToLeft, Integer.valueOf(2));

        ImageIcon shipImg = new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents/cruiserSmallVertical.png");
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

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                fieldMove.requestFocus();
            }
        });
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                makeMove(fieldMove.getX());
                fieldMove.setText("");
                if (fieldMove.getX() < GUI_PLAYER_INPUT_SWITCH) {
                    fieldMove.setLocation(1240, 700);
                    player1.setVisible(false);
                    player2.setVisible(true);
                } else {
                    fieldMove.setLocation(360, 700);
                    player2.setVisible(false);
                    player1.setVisible(true);
                }
            }

            private void makeMove(int side) {
                int[] putPosition = input.guiInputToPositionInPixel(getFieldMove().getText());
                ImageIcon missedHitIMG = new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents/blown-up.png");
                JLabel missedHit = new JLabel(missedHitIMG);
                missedHit.setBounds(putPosition[0], putPosition[1], FIELD_SIZE_IN_PIXEL, FIELD_SIZE_IN_PIXEL);
                missedHit.setVisible(true);
                if (side < GUI_PLAYER_INPUT_SWITCH) {
                    getPlayer1Board().add(missedHit);
                    getPlayer1Board().add(missedHit, Integer.valueOf(5));
                } else {
                    getPlayer2Board().add(missedHit);
                    getPlayer2Board().add(missedHit, Integer.valueOf(5));
                }
            }
        };
        fieldMove.addActionListener(action);

        this.update(this.getGraphics());
        System.out.println("after graphics");
    }

    public void drawSquaresOnPlayersBoard(JLabel label, JLabel label1, JLayeredPane player1Board,
                                          JLayeredPane player2Board) {
        player1Board.add(label, Integer.valueOf(1));
        player2Board.add(label1, Integer.valueOf(1));
    }


    public JTextField getFieldMove() {
        return fieldMove;
    }

    public JLayeredPane getGameBoard() {
        return gameBoard;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public HighScore getHighScore() {
        return highScore;
    }

    public JLayeredPane getPlayer1Board() {
        return player1Board;
    }

    public JLayeredPane getPlayer2Board() {
        return player2Board;
    }

    public GameModeMenu getGameModeMenu() {
        return gameModeMenu;
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