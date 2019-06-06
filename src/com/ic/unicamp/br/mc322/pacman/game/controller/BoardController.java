package com.ic.unicamp.br.mc322.pacman.game.controller;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Character;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Rectangle;

import javax.swing.*;
import java.awt.*;

public class BoardController extends JPanel {

    static final int B_WIDTH = 900;
    static final int B_HEIGHT = 900;

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
        for (int y = 40; y < 860; y += 50) {
            for (int x = 40; x < 860; x += 50) {
                obstacleController.add(new Rectangle(new Point(x, y)));
            }
        }
    }

    void doDrawing(Graphics g, Character... characters) {
        drawBoundaries(g);
        obstacleController.drawAllObstacles(g);
        for (Character character : characters) {
            character.drawMe(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawBoundaries(Graphics g) {
        obstacleController.add(new Rectangle(new Point(0, 0), 900, 20));
        obstacleController.add(new Rectangle(new Point(0, 0), 20, 900));
        obstacleController.add(new Rectangle(new Point(0, 880), 900, 20));
        obstacleController.add(new Rectangle(new Point(880, 0), 20, 900));
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
