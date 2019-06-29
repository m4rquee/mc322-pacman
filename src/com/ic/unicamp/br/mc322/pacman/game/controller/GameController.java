package com.ic.unicamp.br.mc322.pacman.game.controller;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Ghost;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.GhostType;
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
    private static final int POINTS_PER_LEVEL = 460;
    private static final int POINTS_TO_GAIN_LIFE = 5000;
    private int maxPoints = POINTS_PER_LEVEL;

    private Pacman pacman = new Pacman();
    private ArrayList<Ghost> ghosts = new ArrayList<>();

    static boolean inGame = true;
    static boolean waiting = false;
    private int levelNumber = 1;

    public GameController() {
        ghosts.add(new Ghost(GhostType.CHASER));
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
            super.doDrawing(g, levelNumber, pacman.getPoints(), pacman, ghosts);
        } else {
            if (waiting) {
                buildObstacles();
                // Resets game control variables
                GameController.inGame = true;
                GameController.waiting = false;
            } else {
                // While waiting, draw level up screen
                super.nextLevel(g);
                super.doDrawing(g, levelNumber, pacman.getPoints(), null, null);
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
                if(pacman.getPoints() == POINTS_TO_GAIN_LIFE) {
                    pacman.setLife(pacman.getLife()+1);
                }
                obstacleController.setPontuate(false);
                if (pacman.getPoints() >= maxPoints) {
                    inGame = false;
                    levelNumber++;
                    pacman.setPos(new Point(Pacman.DEFAULT_START_POINT));
                    pacman.setDirection(Direction.RIGHT);
                    obstacleController.removeObstacles();
                    maxPoints += POINTS_PER_LEVEL;
                }
            }
        }
    }

    private void moveGhosts() {
        for (Ghost ghost : ghosts) {
            Direction oldDirection = ghost.getDirection();
            ghost.setNextDirection(pacman.getPos());
            if (!obstacleController.collisionDetected(ghost.withFuturePosition())) {
                ghost.move();
            } else {
                ghost.setDirection(oldDirection);
                if (!obstacleController.collisionDetected(ghost.withFuturePosition())) {
                    ghost.move();
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            movePacman();
            moveGhosts();
        }
        repaint();
    }
}
