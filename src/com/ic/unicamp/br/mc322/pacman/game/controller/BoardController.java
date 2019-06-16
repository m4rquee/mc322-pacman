package com.ic.unicamp.br.mc322.pacman.game.controller;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Circle;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Character;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Rectangle;
import com.ic.unicamp.br.mc322.pacman.game.utilities.MapGenerator;
import com.ic.unicamp.br.mc322.pacman.game.utilities.ObstacleBuilder;

import javax.swing.*;
import java.awt.*;

public class BoardController extends JPanel {

    static final int B_WIDTH = 900;
    static final int B_HEIGHT = 900;
    private static final int N = 3;
    private static final int SQUARE_SIZE = 30;

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
        for (int y = 200; y < 860; y += 50)
            for (int x = 200; x < 860; x += 50)
                obstacleController.add(new Circle(new Point(x - 15, y - 15)));
        obstacleController.add(ObstacleBuilder.buildObstacles(MapGenerator.generateMap(N), SQUARE_SIZE));
    }

    void doDrawing(Graphics g, int levelNumber, int points, Character... characters) {
        g.setColor(Color.WHITE);
        g.setFont(new Font(null, Font.PLAIN, 20));
        g.drawString("Level: " + levelNumber, 5, 18);
        g.drawString("Points: 0", B_WIDTH - 100, 18);
        drawBoundaries(g);
        obstacleController.drawAllObstacles(g);
        for (Character character : characters) {
            character.drawMe(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawBoundaries(Graphics g) {
        obstacleController.add(new Rectangle(new Point(0, 20), B_WIDTH - 20, 20));
        obstacleController.add(new Rectangle(new Point(0, 20), 20, B_HEIGHT - 20));
        obstacleController.add(new Rectangle(new Point(0, B_HEIGHT - 20), B_WIDTH - 20, 20));
        obstacleController.add(new Rectangle(new Point(B_WIDTH - 20, 20), 20, B_HEIGHT - 20));
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
