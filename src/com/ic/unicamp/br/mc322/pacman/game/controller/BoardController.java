package com.ic.unicamp.br.mc322.pacman.game.controller;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Line;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Rectangle;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Ghost;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Pacman;
import com.ic.unicamp.br.mc322.pacman.game.utilities.MapGenerator;
import com.ic.unicamp.br.mc322.pacman.game.utilities.ObstacleBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardController extends JPanel {

    private static final int B_WIDTH = 550;
    private static final int B_HEIGHT = 550;
    private static final int N = 2; // Used to calculate map size
    public static int MAP_STYLE;

    ObstacleController obstacleController = new ObstacleController();

    BoardController() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new KeyController());

        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        buildObstacles();
    }

    void buildObstacles() {
        int[][] map = null;
        if (MAP_STYLE == 1) {
            Point spawn = new Point();
            map = MapGenerator.generateMap(N, spawn);
            obstacleController.add(ObstacleBuilder.buildObstacles(map, spawn));
        } else {
            obstacleController.add(ObstacleBuilder.buildObstacles());
        }
        drawBoundaries();
    }

    void doDrawing(Graphics g, int levelNumber, int points, Pacman pacman, ArrayList<Ghost> ghosts) {
        g.setColor(Color.WHITE);
        g.setFont(new Font(null, Font.PLAIN, 20));
        g.drawString("Level: " + levelNumber, 5, 18);
        g.drawString("Points: " + String.format("%06d", points), B_WIDTH - 165, 18);

        obstacleController.drawAllObstacles(g);
        if (ghosts != null) {
            for (Ghost ghost : ghosts) {
                ghost.drawMe(g);
            }
        }
        if (pacman != null) {
            pacman.drawMe(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawBoundaries() {
        obstacleController.add(new Line(new Point(0, 20), new Point(B_WIDTH - 20, 20)));
        obstacleController.add(new Rectangle(new Point(0, 20), 20, B_HEIGHT - 20));
        obstacleController.add(new Rectangle(new Point(20, B_HEIGHT - 20), B_WIDTH - 40, 20));
        obstacleController.add(new Rectangle(new Point(B_WIDTH - 20, 20), 20, B_HEIGHT - 20));
    }

    void nextLevel(Graphics g) {
        String msg = "LEVEL UP!";
        String msgMotivacional = "[ PRESS ENTER TO GO NEXT LEVEL]";
        FontMetrics metr = getFontMetrics(new Font("Helvetica", Font.BOLD, 30));
        g.setColor(Color.white);
        g.setFont(new Font("Helvetica", Font.BOLD, 30));
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
        g.setFont(new Font("Helvetica", Font.BOLD, 15));
        metr = getFontMetrics(new Font("Helvetica", Font.BOLD, 15));
        g.drawString(msgMotivacional, (B_WIDTH - metr.stringWidth(msgMotivacional)) / 2, B_HEIGHT / 2 + 30);
    }

    void gameOver(Graphics g) {
        String msg = "GAME OVER!!!";
        String msgMotivacional = "[ PRESS R TO RESTART ]";
        FontMetrics metr = getFontMetrics(new Font("Helvetica", Font.BOLD, 30));
        g.setColor(Color.white);
        g.setFont(new Font("Helvetica", Font.BOLD, 30));
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
        g.setFont(new Font("Helvetica", Font.BOLD, 15));
        metr = getFontMetrics(new Font("Helvetica", Font.BOLD, 15));
        g.drawString(msgMotivacional, (B_WIDTH - metr.stringWidth(msgMotivacional)) / 2, B_HEIGHT / 2 + 30);
    }
}
