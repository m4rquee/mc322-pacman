package com.ic.unicamp.br.mc322.pacman.game.controller;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Circle;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Line;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Character;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Rectangle;
import com.ic.unicamp.br.mc322.pacman.game.utilities.MapGenerator;
import com.ic.unicamp.br.mc322.pacman.game.utilities.ObstacleBuilder;

import javax.swing.*;
import java.awt.*;

class BoardController extends JPanel {

    private static final int B_WIDTH = 550;
    private static final int B_HEIGHT = 550;
    private static final int N = 2;

    ObstacleController obstacleController = new ObstacleController();

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

    public void buildObstacles() {
        int[][] map = null;
        boolean hasSpawn = false;
        while (!hasSpawn) {
            map = MapGenerator.generateMap(1);
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] == -1)
                        hasSpawn = true;
                }
            }
        }
        drawBoundaries();
        obstacleController.add(ObstacleBuilder.buildObstacles(map, Rectangle.DEFAULT_SIZE));
    }

    void doDrawing(Graphics g, int levelNumber, int points, Character... characters) {
        g.setColor(Color.WHITE);
        g.setFont(new Font(null, Font.PLAIN, 20));
        g.drawString("Level: " + levelNumber, 5, 18);
        if(points < 100)
            g.drawString("Points: " + points, B_WIDTH - 100, 18);
        else if (points < 1000)
            g.drawString("Points: " + points, B_WIDTH - 115, 18);
        else if (points < 10000)
            g.drawString("Points: " + points, B_WIDTH - 130, 18);
        else
            g.drawString("Points: " + points, B_WIDTH - 145, 18);
        obstacleController.drawAllObstacles(g);
        for (Character character : characters) {
            character.drawMe(g);
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
        FontMetrics metr = getFontMetrics(new Font("Helvetica", Font.BOLD, 30));
        g.setColor(Color.white);
        g.setFont(new Font("Helvetica", Font.BOLD, 30));
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }
}
