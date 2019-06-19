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
    private static final int DELAY = 16;
    public static int MAX_POINTS = 40;

    private Pacman pacman = new Pacman();
    private ArrayList<Ghost> ghosts = new ArrayList<>();

    private boolean inGame = true;

    private Timer timer;
    private int levelNumber;

    public GameController() {
        initGame();
    }

    private void initGame() {
        timer = new Timer(DELAY, this);
        timer.start();
        levelNumber = 1;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            super.doDrawing(g, this.levelNumber, pacman.getPoints(), pacman);
        } else {
            super.nextLevel(g);
            levelNumber++;
            inGame = true;
            pacman.setPos(new Point(Pacman.DEFAULT_START_POINT));
        }
    }

    private void move() {
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
            if(!obstacleController.collisionDetected(pacman.withFuturePosition())) {
                pacman.move();
            }
            if(obstacleController.getPontuate()) {
                pacman.pontuate(10);
                obstacleController.setPontuate(false);
                if(pacman.getPoints() >= MAX_POINTS) {
                    inGame = false;
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            move();
        }
        repaint();
    }
}
