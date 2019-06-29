package com.ic.unicamp.br.mc322.pacman.game.controller;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Ghost;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Pacman;
import com.ic.unicamp.br.mc322.pacman.game.utilities.Direction;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameController extends BoardController implements ActionListener {

    public static final int DOT_SIZE = 1;
    private static final int DELAY = 5;
    static int POINTS_PER_LEVEL = 460;
    static int MAX_POINTS = POINTS_PER_LEVEL;

    private Pacman pacman = new Pacman();
    private ArrayList<Ghost> ghosts = new ArrayList<>();

    static boolean inGame = true;
    static boolean waiting = false;
    private int levelNumber = 1;

    public GameController() {
        ghosts.add(new Ghost());
        ghosts.add(new Ghost());
        ghosts.add(new Ghost());
        ghosts.add(new Ghost());
        initGame();
    }

    private void initGame() {
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            super.doDrawing(g, levelNumber, pacman.getPoints(), pacman);
        } else {
            if(waiting) {
                buildObstacles();
                GameController.inGame = true;
                GameController.waiting = false;
            } else {
                super.nextLevel(g);
                super.doDrawing(g, levelNumber, pacman.getPoints());
            }
        }
    }

    private void movePacman() {
        Direction oldDirection = pacman.getDirection();
        if (KeyController.leftDirection) {
            pacman.setDirection(Direction.LEFT);
        } else if (KeyController.rightDirection) {
            pacman.setDirection(Direction.RIGHT);
        } else if (KeyController.upDirection) {
            pacman.setDirection(Direction.UP);
        } else if (KeyController.downDirection) {
            pacman.setDirection(Direction.DOWN);
        }
        if (!obstacleController.collisionDetected(pacman.withFuturePosition())) {
            pacman.move();
        } else {
            pacman.setDirection(oldDirection);
            if (!obstacleController.collisionDetected(pacman.withFuturePosition())) {
                pacman.move();
            }
            if (obstacleController.getPontuate()) {
                pacman.pontuate(10);
                obstacleController.setPontuate(false);
                if (pacman.getPoints() >= MAX_POINTS) {
                    inGame = false;
                    levelNumber++;
                    pacman.setPos(new Point(Pacman.DEFAULT_START_POINT));
                    pacman.setDirection(Direction.RIGHT);
                    obstacleController.removeObstacles();
                    GameController.MAX_POINTS += GameController.POINTS_PER_LEVEL;
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            movePacman();
        }
        repaint();
    }
}
