package com.ic.unicamp.br.mc322.pacman.game.controller;

import com.ic.unicamp.br.mc322.pacman.game.character.Character;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Rectangle;

import javax.swing.*;
import java.awt.*;

public class BoardController extends JPanel {

    static final int B_WIDTH = 300;
    static final int B_HEIGHT = 300;

    protected ObstacleController obstacleController = new ObstacleController();

    BoardController() {
        initBoard();
    }

    void initBoard() {
        addKeyListener(new KeyController());

        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        buildObstacles();
    }

    private void buildObstacles() {
        for (int y = 20; y < B_HEIGHT; y += 35) {
            for (int x = 20; x < B_WIDTH - 20; x += 70) {
                obstacleController.add(new Rectangle(new Point(x, y), 50, 10));
            }
        }
    }

    void doDrawing(Graphics g, Character... characters) {
        g.setColor(Color.RED);
        g.drawRect(1, 1, 298, 298);
        g.drawRect(0, 0, 298, 298);
        g.setColor(Color.BLUE);
        obstacleController.drawAllObstacles(g);
        for (Character character : characters) {
            character.drawMe(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }
}
