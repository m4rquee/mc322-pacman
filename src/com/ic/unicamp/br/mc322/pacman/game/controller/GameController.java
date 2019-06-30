package com.ic.unicamp.br.mc322.pacman.game.controller;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.ghost.Ghost;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.ghost.GhostType;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Pacman;
import com.ic.unicamp.br.mc322.pacman.game.utilities.Direction;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.utilities.ObstacleBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameController extends BoardController implements ActionListener {

    public static final int DOT_SIZE = 1;
    private static final int DELAY = 5;
    private static final int POINTS_PER_LEVEL = 1740;
    private static final int POINTS_TO_GAIN_LIFE = 5000;

    private Pacman pacman = new Pacman();
    private ArrayList<Ghost> ghosts;

    static boolean inGame;
    static boolean waiting;
    static boolean shouldRestart;
    private int levelNumber;

    public GameController() {
        ghosts = new ArrayList<>();
        inGame = true;
        waiting = false;
        shouldRestart = false;
        levelNumber = 1;
        initGame();
        initGhosts();
    }

    private void initGhosts() {
        for (GhostType type : GhostType.values())
            ghosts.add(new Ghost(new Point(ObstacleBuilder.spawn.getX(), ObstacleBuilder.spawn.getY()), type));
    }

    private void initGame() {
        Timer timer = new Timer(DELAY, this); // At every DELAY ms triggers this listener
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
                initGhosts();
                if (shouldRestart) {
                    pacman = new Pacman();
                    levelNumber = 1;
                    shouldRestart = false;
                }
                // Resets game control variables
                GameController.inGame = true;
                GameController.waiting = false;
            } else {
                // While waiting, draw level up or game over screen
                if (pacman.getLife() == 0) {
                    super.gameOver(g);
                    super.doDrawing(g, levelNumber, pacman.getPoints(), null, null);
                } else {
                    super.nextLevel(g);
                    super.doDrawing(g, levelNumber, pacman.getPoints(), null, null);
                }
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
                if (pacman.getPoints() == POINTS_TO_GAIN_LIFE) {
                    pacman.addLife();
                }
                obstacleController.setPontuate(false);
                if (pacman.getPoints() >= POINTS_PER_LEVEL * levelNumber) {
                    inGame = false;
                    levelNumber++;
                    pacman.respawn();
                    obstacleController.removeObstacles();
                }
            }
        }
    }

    private void moveGhosts() {
        for (Ghost ghost : ghosts) {
            Point pos = null;
            if (ghost.getType() == GhostType.CHASER) {
                pos = pacman.getPos();
            } else if (ghost.getType() == GhostType.EVASIVE) {
                int xMedio = 0;
                int yMedio = 0;
                for (Ghost ghostAux : ghosts) {
                    xMedio += ghostAux.getPos().getX();
                    yMedio += ghostAux.getPos().getY();
                }
                xMedio = xMedio / 4;
                yMedio = yMedio / 4;
                pos = new Point(xMedio, yMedio);
            }
            ghost.setNextDirection(pos, null);
            if (!obstacleController.collisionDetected(ghost.withFuturePosition())) {
                ghost.move();
            } else {
                ghost.setNextDirection(pos, ghost.getDirection());
                if (!obstacleController.collisionDetected(ghost.withFuturePosition())) {
                    ghost.move();
                }
            }
            if (ghost.getType() == GhostType.WIZARD) {
                ghost.setNextPos(obstacleController);
            }
        }
    }

    private void restartGame() {
        obstacleController.removeObstacles();
        ghosts.clear();
        inGame = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            movePacman();
            moveGhosts();
            if (obstacleController.shouldTakeHit(ghosts, pacman)) {
                pacman.takeHit();
                for (Ghost ghost : ghosts) {
                    ghost.setPos(new Point(ObstacleBuilder.spawn.getX(), ObstacleBuilder.spawn.getY()));
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            if (pacman.getLife() == 0)
                restartGame();
        }
        repaint();
    }
}
