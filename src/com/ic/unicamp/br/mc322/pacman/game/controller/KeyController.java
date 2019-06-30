package com.ic.unicamp.br.mc322.pacman.game.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyController extends KeyAdapter {
    static Boolean leftDirection = false;
    static Boolean rightDirection = true;
    static Boolean upDirection = false;
    static Boolean downDirection = false;

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == 27)
            System.exit(0);

        leftDirection = key == KeyEvent.VK_LEFT;
        upDirection = key == KeyEvent.VK_UP;
        downDirection = key == KeyEvent.VK_DOWN;
        rightDirection = key == KeyEvent.VK_RIGHT;
        if (!GameController.inGame) {
            if (key == 10) {
                GameController.waiting = true;
            }
            if(key == 82) {
                GameController.waiting = true;
                GameController.shouldRestart = true;
            }
        }
    }
}
